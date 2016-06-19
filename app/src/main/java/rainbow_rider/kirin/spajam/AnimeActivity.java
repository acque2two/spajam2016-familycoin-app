package rainbow_rider.kirin.spajam;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.reflect.Method;

public class AnimeActivity extends AppCompatActivity {
    private boolean TEXT_VISIBILITY = true;
    private TextView mTextView;

    private Animation bottom_to_top;
    private Animation top_to_bottom;
    private Animation right_to_left;
    private Animation left_to_right;

    private ImageView bottom;
    private ImageView top;
    private ImageView right;
    private ImageView left;

    private static int width;
    private static int height;

    // Canvas 中心点
    private float xc = 0.0f;
    private float yc = 0.0f;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_anime);

        // ステータスバー、ナビゲーションバーを含めた実画素数を取得する
//        TextView w = (TextView) findViewById(R.id.ani_width);
//        TextView h = (TextView) findViewById(R.id.ani_height);
        Point real = getRealSize();

        width = real.x;
        height = real.y;
/*
        w.setText("width: " + real.x);
        h.setText("height: " + real.y);*/


        TestView testView = new TestView(this);
        setContentView(testView);
/*
        mTextView = (TextView) findViewById(R.id.text_view);
        Button button = (Button) findViewById(R.id.button);

        bottom_to_top = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);
        top_to_bottom = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom);
        right_to_left = AnimationUtils.loadAnimation(this, R.anim.right_to_left);
        left_to_right = AnimationUtils.loadAnimation(this, R.anim.left_to_right);

        bottom = (ImageView) findViewById(R.id.ani_bottom);
        top = (ImageView) findViewById(R.id.ani_top);
        right = (ImageView) findViewById(R.id.ani_right);
        left = (ImageView) findViewById(R.id.ani_left);

        final ArrayList<ImageView> images = new ArrayList<ImageView>() {{
            add(bottom);
            add(top);
            add(right);
            add(left);
        }};

        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TEXT_VISIBILITY) {
                    TEXT_VISIBILITY = false;
                    mTextView.startAnimation(bottom_to_top);
                    mTextView.setVisibility(View.VISIBLE);
                    bottom.startAnimation(bottom_to_top);
                    bottom.setVisibility(View.VISIBLE);

                    right.startAnimation(right_to_left);
                    right.setVisibility(View.VISIBLE);

                    left.startAnimation(left_to_right);
                    left.setVisibility(View.VISIBLE);

                    top.startAnimation(top_to_bottom);
                    top.setVisibility(View.VISIBLE);

                } else {
                    TEXT_VISIBILITY = true;
                    mTextView.startAnimation(top_to_bottom);
                    mTextView.setVisibility(View.INVISIBLE);
                    for (ImageView i : images) {
                        i.startAnimation(top_to_bottom);
                        i.setVisibility(View.INVISIBLE);
                    }
                }
            }

        });*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Anime Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://rainbow_rider.kirin.spajam/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Anime Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://rainbow_rider.kirin.spajam/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
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
            Path pathshadow = new Path();
            // 背景
            canvas.drawColor(Color.rgb(1,87,155));
            Paint paintshadow = new Paint();
            paintshadow.setStyle(Paint.Style.FILL);
            paintshadow.setColor(Color.argb(112, 0, 0, 0));
            BlurMaskFilter blur = new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL);
            paintshadow.setMaskFilter(blur);
            paintshadow.setFlags(Paint.ANTI_ALIAS_FLAG);
            paint.setFlags(Paint.ANTI_ALIAS_FLAG);

            paintshadow.setShadowLayer(20f,0f, 0f, Color.BLACK);
            paint.setColor(Color.rgb(21,101,192)); //Bottom
            // (x1,y1,x2,y2,paint) 左上の座標(x1,y1), 右下の座標(x2,y2)
            //canvas.drawRect(0-diff, (height * (7f / 10f))-diff, width+diff, height+diff,
            //        paintshadow);
            path.moveTo(0,(height * (7f / 10f)) );
            path.lineTo(width,(height * (7f / 10f)));
            path.lineTo(width,height);
            path.lineTo(0,height);
            canvas.drawPath(path, paintshadow);
            canvas.drawPath(path, paint);


            paint.setColor(Color.rgb(25,118,210)); //Left
            // (x1,y1,x2,y2,paint) 左上の座標(x1,y1), 右下の座標(x2,y2)
            path = new Path();
            path.moveTo(0,0);
            path.lineTo((width * (1f / 6f)),0);
            path.lineTo((width * (1f / 6f)),height);
            path.lineTo(0,height);
            path.moveTo((width * (1f / 6f))+20f,height);


            canvas.drawPath(path,paintshadow);
            canvas.drawPath(path,paint);


            paint.setColor(Color.rgb(25,118,210)); //Right
            // (x1,y1,x2,y2,paint) 左上の座標(x1,y1), 右下の座標(x2,y2)
            path = new Path();
            path.moveTo(width * (5f / 6f),0);
            path.lineTo(width,0);
            path.lineTo(width,height);
            path.lineTo(width * (5f / 6f),height);
            path.moveTo((width*(5f/6f)-20f),height);


            canvas.drawPath(path,paintshadow);
            canvas.drawPath(path,paint);

            // 三角形を書く
            paint.setStrokeWidth(10); //Left
            paint.setColor(Color.rgb(30,136,229));

            Path path4shadow = new Path();
            path4shadow.moveTo((width - (width / 12))-diff, 0-diff);

            path4shadow.lineTo(width / 12 + diff, 0); // 右上のてん max w : 右下のてん
            path4shadow.lineTo(0, 0); // 下左のてん  1/4 w: 下右のてん
            path4shadow.lineTo(0, (height / 2)+diff); // 左上のてん max w: 左下のてん 1/4

            Path path4 = new Path();
            path4.moveTo((width - (width / 12)), 0);

            path4.lineTo(width / 12, 0); // 右上のてん max w : 右下のてん
            path4.lineTo(0, 0); // 下左のてん  1/4 w: 下右のてん
            path4.lineTo(0, (height / 2)); // 左上のてん max w: 左下のてん 1/4

            canvas.drawPath(path4shadow, paintshadow);
            canvas.drawPath(path4, paint);

            // 三角形を書く
            paint.setStrokeWidth(10); //Right
            paint.setColor(Color.rgb(33,150,243));
            Path path5 = new Path();
            path5.moveTo(width, 0);

            path5.lineTo(width, 0); // 右上のてん max w : 右下のてん
            path5.lineTo((width / 12), 0); // 下左のてん  1/4 w: 下右のてん
            path5.lineTo(width, (height / 2)); // 左上のてん max w: 左下のてん 1/4

            Path path5shadow = new Path();
            path5shadow.moveTo(width+diff, 0-diff);

            path5shadow.lineTo(width+diff, 0-diff); // 右上のてん max w : 右下のてん
            path5shadow.lineTo((width / 12)-diff, 0-diff); // 下左のてん  1/4 w: 下右のてん
            path5shadow.lineTo(width-diff, (height / 2)-diff); // 左上のてん max w: 左下のてん 1/4

            canvas.drawPath(path5shadow, paintshadow);
            canvas.drawPath(path5, paint);

        }
    }
}
