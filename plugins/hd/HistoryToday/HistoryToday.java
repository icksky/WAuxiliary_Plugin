
import java.io.File;

import me.hd.wauxv.plugin.api.callback.PluginCallBack;

void sendToday(String talker) {
    String api = "https://xiaoapi.cn/API/lssdjt_pic.php";
    String path = cacheDir + "/image.png";
    download(api, path, null, new PluginCallBack.DownloadCallback() {
        public void onSuccess(File file) {
            sendImage(talker, file.getAbsolutePath());
            file.delete();
        }

        public void onError(Exception e) {
            sendText(talker, "[慕名API]下载异常:" + e.getMessage());
        }
    });
}

void onHandleMsg(Object msgInfoBean) {
    if (msgInfoBean.isText()) {
        String content = msgInfoBean.getContent();
        String talker = msgInfoBean.getTalker();
        if (content.equals("/历史今天")) {
            sendToday(talker);
        }
    }
}
