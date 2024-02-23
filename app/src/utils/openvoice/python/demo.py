import os
import torch
import se_extractor
def rundemo(output_dir):
    from api import BaseSpeakerTTS, ToneColorConverter
    ckpt_converter = 'checkpoints/converter'
    # 使用CPU进行计算
    device = 'cpu'
    # 加载基础模型
    tone_color_converter = ToneColorConverter(f'{ckpt_converter}/config.json', device=device)
    tone_color_converter.load_ckpt(f'{ckpt_converter}/checkpoint.pth')
    # 创建输出目录
    os.makedirs(output_dir, exist_ok=True)
    # 训练音频
    reference_speaker = 'resources/example_reference.mp3'
    target_se, audio_name = se_extractor.get_se(reference_speaker, tone_color_converter, target_dir='processed',
                                                vad=True)
    # TTS配置
    ckpt_base = 'checkpoints/base_speakers/ZH'
    base_speaker_tts = BaseSpeakerTTS(f'{ckpt_base}/config.json', device=device)
    base_speaker_tts.load_ckpt(f'{ckpt_base}/checkpoint.pth')
    source_se = torch.load(f'{ckpt_base}/zh_default_se.pth').to(device)
    save_path = f'{output_dir}/output_chinese.wav'
    text = "这个世界是由谁创造的，应该不是我"
    src_path = f'{output_dir}/tmp.wav'
    # TTS转换，speed为语速
    base_speaker_tts.tts(text, src_path, speaker='default', language='Chinese', speed=0.9)
    # 数字水印内容
    encode_message = "@Print_Lin"
    # 运行转换
    return tone_color_converter.convert(
        audio_src_path=src_path,
        src_se=source_se,
        tgt_se=target_se,
        output_path=None,
        message=encode_message)
if __name__ == '__main__':
    rundemo('outputs')