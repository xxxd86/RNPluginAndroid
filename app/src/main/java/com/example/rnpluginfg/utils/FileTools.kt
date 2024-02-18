package com.example.rnpluginfg.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipFile

/**
 * @param 日常读取文件，打开文件页面，图片页面
 *
 */
object FileTools {
    fun writeToFile_OneLine(file:File,words:String){
        file.printWriter().use { out ->
            out.println(words)
        }
    }
    fun intentToFileReturn(activity: Activity, fileType:String, fileNumber:Int, callBack:()->Unit){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("*/*")//fileType
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        activity.startActivityForResult(intent,101)
    }
    private suspend fun <T> withZipFromUri(
        context: Context,
        uri: Uri, block: suspend (ZipFile) -> T
    ) : T {
        val file = File(context.filesDir, "tempzip.zip")
        try {
            return withContext(Dispatchers.IO) {
                kotlin.runCatching {
                    context.contentResolver.openInputStream(uri).use { input ->
                        if (input == null) throw FileNotFoundException("openInputStream failed")
                        file.outputStream().use { input.copyTo(it) }
                    }
                    ZipFile(file, ZipFile.OPEN_READ).use { block.invoke(it) }
                }.getOrThrow()
            }
        } finally {
            file.delete()
        }
    }
    suspend fun doStuffWithZip(context: Context, uri: Uri) {
        withZipFromUri(context, uri) { // it: ZipFile
            for (entry in it.entries()) {
                Log.v("ENTRY","entry: ${entry.name}") // or whatever
            }
        }
    }
    fun unZipFile(zipFile: String, targetFile: String) {
        var outputStream: OutputStream? = null
        var inputStream: InputStream? = null
        try {
            File(targetFile).run {
                if (!exists()) {
                    mkdirs()
                }
            }
            val zf = ZipFile(zipFile)
            val entries = zf.entries()
            while (entries.hasMoreElements()) {
                val zipEntry: ZipEntry = entries.nextElement()
                val zipEntryName = zipEntry.name
                inputStream = zf.getInputStream(zipEntry)
                if (zipEntryName.startsWith("__MACOSX")) {
                    continue
                }
                File(targetFile, zipEntryName).apply {
                    if (isDirectory) {
                        if (!exists()) {
                            mkdirs()
                        }
                    } else {
                        createNewFile()
                        writeStreamToFile(inputStream, this)
                    }
                }
                inputStream?.close()
                outputStream?.close()
            }
            File(zipFile).delete()
        } finally {
            inputStream?.close()
            outputStream?.close()
        }
    }


    private fun writeStreamToFile(stream: InputStream, file: File) {
        try {
            val fos = FileOutputStream(file)

            val buffer = ByteArray(1024)
            var len: Int
            var total = 0
            while ((stream.read(buffer).also { len = it }) != -1) {
                fos.write(buffer, 0, len)
                total += len
            }

            fos.flush()
            fos.close()
            stream.close()
        } catch (e1: Exception) {
            e1.printStackTrace()
        }
    }


    fun getMapRootPath(context: Context): String {
        return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
            context.filesDir.absolutePath + "filename"
        } else {
            context.getExternalFilesDir("filename")?.absolutePath
                ?: context.cacheDir.absolutePath
        }
    }

}