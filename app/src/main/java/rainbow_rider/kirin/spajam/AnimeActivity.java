package rainbow_rider.kirin.spajam;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Method;

public class AnimeActivity extends AppCompatActivity {
    private static int width;
    private static int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_anime);

        Point real = getRealSize();
        width = real.x;
        height = real.y;
        TestView testView = new TestView(this);
        setContentView(testView);

    }

    @SuppressLint("NewApi")
    private Point getRealSize() {

        Display display = getWindowManager().getDefaultDisplay();
        Point real = new Point(0, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            // Android 4.2以上
            display.getRealSize(real);
            return real;

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            // Android 3.2以上
            try {
                Method getRawWidth = Display.class.getMethod("getRawWidth");
                Method getRawHeight = Display.class.getMethod("getRawHeight");
                int width = (Integer) getRawWidth.invoke(display);
                int height = (Integer) getRawHeight.invoke(display);
                real.set(width, height);
                return real;

            } catch (Exception e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            }
        }

        return real;
    }



    class TestView extends View {
        Paint paint;

        public TestView(Context context) {
            super(context);
            paint = new Paint();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            float diff = 0f;
            Path path = new Path();
            canvas.drawColor(Color.rgb(1, 87, 155));
            float f[] = {0,(width * (1f / 6f)),0,height};

            paint = new Paint();
            paint.setColor(Color.rgb(21,101,192)); //Bottom
            // (x1,y1,x2,y2,paint) 左上の座標(x1,y1), 右下の座標(x2,y2)
            //canvas.drawRect(0-diff, (height * (7f / 10f))-diff, width+diff, height+diff,
            //        paintshadow);
            path.moveTo(0,(height * (7f / 10f)) );
            path.lineTo(width,(height * (7f / 10f)));
            path.lineTo(width,height);
            path.lineTo(0,height);
            myDraw bottom = new myDraw(canvas,paint,path);

            paint = new Paint();
            paint.setColor(Color.rgb(25, 118, 210));
            path = new Path();
            path.moveTo(0, 0);
            path.lineTo((width * (1f / 6f)), 0);
            path.lineTo((width * (1f / 6f)), height);
            path.lineTo(0, height);
            path.moveTo((width * (1f / 6f)) + 20f, height);
            myDraw left = new myDraw(canvas, paint, path);



            paint = new Paint();
            paint.setColor(Color.rgb(25, 118, 210)); //Right
            // (x1,y1,x2,y2,paint) 左上の座標(x1,y1), 右下の座標(x2,y2)
            path = new Path();
            path.moveTo(width * (5f / 6f), 0);
            path.lineTo(width, 0);
            path.lineTo(width, height);
            path.lineTo(width * (5f / 6f), height);
            path.moveTo((width * (5f / 6f) - 20f), height);
            myDraw right = new myDraw(canvas, paint, path);


            // 三角形を書く
            paint = new Paint();
            paint.setStrokeWidth(10); //Left
            paint.setColor(Color.rgb(30, 136, 229));

            path = new Path();
            path.moveTo((width - (width / 12)), 0);

            path.lineTo(width / 12, 0); // 右上のてん max w : 右下のてん
            path.lineTo(0, 0); // 下左のてん  1/4 w: 下右のてん
            path.lineTo(0, (height / 2)); // 左上のてん max w: 左下のてん 1/4
            myDraw topleft = new myDraw(canvas, paint, path);

            // 三角形を書く
            paint = new Paint();
            paint.setStrokeWidth(10); //Right
            paint.setColor(Color.rgb(33, 150, 243));
            path = new Path();
            path.moveTo(width, 0);

            path.lineTo(width, 0); // 右上のてん max w : 右下のてん
            path.lineTo((width / 12), 0); // 下左のてん  1/4 w: 下右のてん
            path.lineTo(width, (height / 2)); // 左上のてん max w: 左下のてん 1/4
            myDraw topright = new myDraw(canvas, paint, path);

            bottom.Draw();
            left.Draw();
            right.Draw();
            topleft.Draw();
            topright.Draw();
        }
    }

    public class myDraw {
        public Path path = new Path();
        public Paint paint = new Paint();
        public final Path defaultpath = new Path();
        public final Paint paintshadow = new Paint();
        public float[] defaultfloat = {0, 0, 0, 0};
        public Canvas canvas;
        public myDraw(Canvas canvas,Paint paint, Path path){
            this.canvas = canvas;
            this.paint = paint;
            setDefaultpath(path);
            paintshadow.setShadowLayer(20f, 0f, 0f, Color.BLACK);
            paintshadow.setStyle(Paint.Style.FILL);
            paintshadow.setColor(Color.argb(112, 0, 0, 0));
            BlurMaskFilter blur = new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL);
            paintshadow.setMaskFilter(blur);
            paintshadow.setFlags(Paint.ANTI_ALIAS_FLAG);
        }

        public void Draw() {
            canvas.drawPath(path, paintshadow);
            canvas.drawPath(path, paint);
            path.set(defaultpath);
        }

        public void Move(float diffx, float diffy) {
            path = defaultpath;
            path.offset(diffx, diffy);
        }

        public void setDefaultpath(float[] fl) {
            defaultfloat[0] = fl[0];
            defaultfloat[1] = fl[1];
            defaultfloat[2] = fl[2];
            defaultfloat[3] = fl[3];
            defaultpath.moveTo(defaultfloat[0], defaultfloat[2]);
            defaultpath.lineTo(defaultfloat[1], defaultfloat[2]);
            defaultpath.lineTo(defaultfloat[1], defaultfloat[3]);
            defaultpath.lineTo(defaultfloat[0], defaultfloat[3]);
        }

        public void setDefaultpath(Path path){
            this.defaultpath.set(path);
            this.path.set(path);
        }
    }
}
