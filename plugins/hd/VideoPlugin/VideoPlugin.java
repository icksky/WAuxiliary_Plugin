
import java.io.File;

import me.hd.wauxv.plugin.api.callback.PluginCallBack;

void sendMp4(String talker) {
    String api = "https://www.hhlqilongzhu.cn/api/MP4_xiaojiejie.php";
    String path = cacheDir + "/video.mp4";
    download(api, path, null, new PluginCallBack.DownloadCallback() {
        public void onSuccess(File file) {
            sendVideo(talker, file.getAbsolutePath());
            file.delete();
        }

        public void onError(Exception e) {
            sendText(talker, "下载异常:" + e.getMessage());
        }
    });
}

void onHandleMsg(Object msgInfoBean) {
    if (msgInfoBean.isText()) {
        String content = msgInfoBean.getContent();
        String talker = msgInfoBean.getTalker();
        if (content.equals("/视频")) {
            sendMp4(talker);
        }
    }
}
