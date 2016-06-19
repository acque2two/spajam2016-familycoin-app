package rainbow_rider.kirin.spajam;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.EnumMap;



public class TestView extends SurfaceView implements SurfaceHolder.Callback, Runnable {


    enum pos {
        TOPLEFT, TOPRIGHT, LEFT, RIGHT, BOTTOM
    }

    private static final long FPS = 30;
    private static final long FRAME_TIME = 1000 / FPS;
    private EnumMap<pos, MyDraw> drawMap;
    private int height = -1;
    private int width = -1;
    private SurfaceHolder surfaceHolder;
    private Thread thread;

    private Context context;

    public TestView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        this.context = context;
    }

    private void drawInit() {


        height = getHeight();
        Log.d("TestView/Height", String.valueOf(height));
        width = getWidth();
        Log.d("TestView/Width", String.valueOf(width));


        Path path = new Path();
        drawMap = new EnumMap<>(pos.class);

        Paint paint = new Paint();
        paint.setColor(Color.rgb(21, 101, 192)); //Bottom

        path.moveTo(0, (height * (7f / 10f)));
        path.lineTo(width, (height * (7f / 10f)));
        path.lineTo(width, height);
        path.lineTo(0, height);
        drawMap.put(pos.BOTTOM, new MyDraw(this.getContext(), paint, path));

        paint = new Paint();
        paint.setColor(Color.rgb(25, 118, 210));
        path = new Path();
        path.moveTo(0, 0);
        path.lineTo((width * (1f / 6f)), 0);
        path.lineTo((width * (1f / 6f)), height);
        path.lineTo(0, height);
        path.moveTo((width * (1f / 6f)) + 20f, height);
        drawMap.put(pos.LEFT, new MyDraw(this.getContext(), paint, path));


        paint = new Paint();
        paint.setColor(Color.rgb(25, 118, 210)); //Right
        // (x1,y1,x2,y2,paint) 左上の座標(x1,y1), 右下の座標(x2,y2)
        path = new Path();
        path.moveTo(width * (5f / 6f), 0);
        path.lineTo(width, 0);
        path.lineTo(width, height);
        path.lineTo(width * (5f / 6f), height);
        path.moveTo((width * (5f / 6f) - 20f), height);
        drawMap.put(pos.RIGHT, new MyDraw(this.getContext(), paint, path));


        // 三角形を書く
        paint = new Paint();
        paint.setStrokeWidth(10); //Left
        paint.setColor(Color.rgb(30, 136, 229));

        path = new Path();
        path.moveTo((width - (width / 12)), 0);

        path.lineTo(width / 12, 0); // 右上のてん max w : 右下のてん
        path.lineTo(0, 0); // 下左のてん  1/4 w: 下右のてん
        path.lineTo(0, (height / 2)); // 左上のてん max w: 左下のてん 1/4
        drawMap.put(pos.TOPLEFT, new MyDraw(this.getContext(), paint, path));

        // 三角形を書く
        paint = new Paint();
        paint.setStrokeWidth(10); //Right
        paint.setColor(Color.rgb(33, 150, 243));
        path = new Path();
        path.moveTo(width, 0);

        path.lineTo(width, 0); // 右上のてん max w : 右下のてん
        path.lineTo((width / 12), 0); // 下左のてん  1/4 w: 下右のてん
        path.lineTo(width, (height / 2)); // 左上のてん max w: 左下のてん 1/4
        drawMap.put(pos.TOPRIGHT, new MyDraw(this.getContext(), paint, path));



// アニメーションの時間(3秒)を設定する
/*        anim = ValueAnimator.ofFloat(0f, 1000f);
        anim.setDuration(10000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                invalidate();
            }
        });*/
    }

    @Override
    public void run() {
        Canvas canvas;
        Paint bgPaint = new Paint();
        bgPaint.setARGB(0, 0, 0, 0);
        long loopCount = 0;
        long waitTime;
        long startTime = System.currentTimeMillis();
        float animValue = 0f;
        while (thread != null) {
            try {
                loopCount++;
                animValue += 10;
                canvas = surfaceHolder.lockCanvas();

                canvas.drawColor(Color.BLACK);
                drawMap.get(pos.BOTTOM).MoveDef(0, animValue);
                drawMap.get(pos.BOTTOM).Draw(canvas);
                drawMap.get(pos.LEFT).MoveDef(-animValue, 0);
                drawMap.get(pos.LEFT).Draw(canvas);
                drawMap.get(pos.RIGHT).MoveDef(animValue, 0);
                drawMap.get(pos.RIGHT).Draw(canvas);
                drawMap.get(pos.TOPLEFT).MoveDef(-animValue, -animValue);
                drawMap.get(pos.TOPLEFT).Draw(canvas);
                drawMap.get(pos.TOPRIGHT).MoveDef(animValue, -animValue);
                drawMap.get(pos.TOPRIGHT).Draw(canvas);
                surfaceHolder.unlockCanvasAndPost(canvas);
                waitTime = (loopCount * FRAME_TIME) - (System.currentTimeMillis() - startTime);
                if (waitTime > 0) {
                    thread.sleep(waitTime);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawInit();
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread = null;
    }
/*
    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        if (width == -1) {
            this.canvas = canvas;
            drawInit();
        }
        float animValue =  (float) anim.getAnimatedValue();
        canvas.drawColor(Color.rgb(1, 87, 155));
        drawMap.get(pos.BOTTOM).MoveDef(0, animValue);
        drawMap.get(pos.BOTTOM).Draw(canvas);
        drawMap.get(pos.LEFT).MoveDef(-animValue, 0);
        drawMap.get(pos.LEFT).Draw(canvas);
        drawMap.get(pos.RIGHT).MoveDef(animValue, 0);
        drawMap.get(pos.RIGHT).Draw(canvas);
        drawMap.get(pos.TOPLEFT).MoveDef(-animValue, -animValue);
        drawMap.get(pos.TOPLEFT).Draw(canvas);
        drawMap.get(pos.TOPRIGHT).MoveDef(animValue, -animValue);
        drawMap.get(pos.TOPRIGHT).Draw(canvas);

    }
*/
}
