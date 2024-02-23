import com.chaquo.python.pythonVersionInfo

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.chaquo.python")
}

android {
    namespace = "com.example.rnpluginfg"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.rnpluginfg"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
            ndk {
                // On Apple silicon, you can omit x86_64.
                abiFilters += listOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
            }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }
    buildFeatures {
        viewBinding = true
    }
}
chaquopy {
    defaultConfig{
        buildPython("C:\\ProgramData\\anaconda3\\python.exe")
    }
}
for (path in listOf(
    "src/utils"
)) {
    android.sourceSets.getByName("main") {
        java { srcDir("$path/openvoice/java") }
        res { srcDir("$path/openvoice/res") }
    }
    chaquopy.sourceSets.getByName("main") {
        srcDir("$path/openvoice/python")
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(files("libs\\AMap2DMap_6.0.0_AMapSearch_9.7.0_AMapLocation_6.4.0_20230808.jar"))
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    // define a BOM and its version
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.12.0"))

    // define any required OkHttp artifacts without version
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")
    // OpenCV基础库（*必须）
    implementation ("com.github.jenly1314.WeChatQRCode:opencv:2.1.0")
    implementation ("com.github.jenly1314.WeChatQRCode:opencv-armv7a:2.1.0")

// OpenCV的其他ABI（可选），根据你的需要选择想要支持的SO库架构
    implementation ("com.github.jenly1314.WeChatQRCode:opencv-armv64:2.1.0")
    implementation ("com.github.jenly1314.WeChatQRCode:opencv-x86:2.1.0")
    implementation ("com.github.jenly1314.WeChatQRCode:opencv-x86_64:2.1.0")
     // 微信二维码扫码功能（可选）
    // 微信二维码识别功能（可选）
    implementation ("com.github.jenly1314.WeChatQRCode:wechat-qrcode:2.1.0")
    implementation ("com.github.jenly1314.WeChatQRCode:wechat-qrcode-scanning:2.1.0")
    implementation ("com.squareup.retrofit2:retrofit:2.6.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.5.0") //配置使用Gson解析响应数据 可选
    implementation ("com.squareup.retrofit2:adapter-rxjava:2.4.0") //配置支持RxJava 可选
    implementation(project(":openvoicepn"))
    implementation(project(":pnbase:asBaselibrary"))
}