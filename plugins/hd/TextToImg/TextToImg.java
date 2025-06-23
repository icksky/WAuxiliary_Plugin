
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;

import java.io.FileOutputStream;
import java.io.IOException;

void sendTextImg(String title, String subTitle) {
    float textSize = 50.0f;
    float padding = 40.0f;

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    paint.setTypeface(Typeface.DEFAULT_BOLD);
    paint.setTextSize(textSize);

    float titleWidth = paint.measureText(title);
    float subTitleWidth = paint.measureText(subTitle);

    int width = (int) (titleWidth + subTitleWidth + padding * 2.5f);
    int height = (int) (textSize + padding * 2f);

    Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    canvas.drawColor(Color.BLACK);

    float yOffset = height - padding * 2 + textSize * 2 / 3;
    paint.setColor(Color.WHITE);
    canvas.drawText(title, padding, yOffset, paint);

    paint.setColor(Color.parseColor("#F79817"));
    RectF rect = new RectF(
            padding + titleWidth * 1.1f,
            yOffset - textSize,
            padding * 1.5f + titleWidth + subTitleWidth,
            yOffset + textSize * 0.3f
    );
    canvas.drawRoundRect(rect, 5, 5, paint);

    paint.setColor(Color.BLACK);
    canvas.drawText(subTitle, padding + titleWidth * 1.15f, yOffset, paint);
    try {
        String path = cacheDir + "/image.png";
        FileOutputStream out = new FileOutputStream(path);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        sendImage(getTargetTalker(), path, "wxe3ad19e142df87b3");
        new File(path).delete();
        bitmap.recycle();
    } catch (IOException e) {
    }
}

boolean onLongClickSendBtn(String text) {
    if (text.startsWith("/作图 ")) {
        String str = text.substring(4);
        int index = str.indexOf(" ");
        if (index == -1) return false;
        String title = str.substring(0, index);
        String subTitle = str.substring(index + 1);
        sendTextImg(title, subTitle);
        return true;
    }
    return false;
}
