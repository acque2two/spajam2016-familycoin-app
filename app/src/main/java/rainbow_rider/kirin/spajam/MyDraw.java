package rainbow_rider.kirin.spajam;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class MyDraw {
    public final Path defaultpath = new Path();
    public final Paint paintshadow = new Paint();
    public Path path = new Path();
    public Paint paint = new Paint();
    public float[] defaultfloat = {0, 0, 0, 0};
    public float movex = 0f;
    public float movey = 0f;

    public MyDraw(Context context, Paint paint, Path path) {
        this.paint = paint;
        setDefaultpath(path);
        paintshadow.setShadowLayer(15f, 0f, 0f, Color.BLACK);
        paintshadow.setStyle(Paint.Style.FILL);
        paintshadow.setColor(Color.argb(152, 0, 0, 0));
    }

    public void Draw(Canvas canvas) {
        canvas.drawPath(path, paintshadow);
        canvas.drawPath(path, paint);
        path.set(defaultpath);
    }

    public void Move(float diffx, float diffy) {
        path.offset(diffx, diffy);
        movex += diffx;
        movey += diffy;
    }

    public void MoveDef(float diffx, float diffy) {
        path.set(defaultpath);
        path.offset(diffx, diffy);
        movex = diffx;
        movey = diffy;
    }

    public void setDefaultpath(float fl[]) {
        defaultfloat[0] = fl[0];
        defaultfloat[1] = fl[1];
        defaultfloat[2] = fl[2];
        defaultfloat[3] = fl[3];
        defaultpath.moveTo(defaultfloat[0], defaultfloat[2]);
        defaultpath.lineTo(defaultfloat[1], defaultfloat[2]);
        defaultpath.lineTo(defaultfloat[1], defaultfloat[3]);
        defaultpath.lineTo(defaultfloat[0], defaultfloat[3]);
    }

    public void setDefaultpath(Path path) {
        this.defaultpath.set(path);
        this.path.set(path);
    }

}

