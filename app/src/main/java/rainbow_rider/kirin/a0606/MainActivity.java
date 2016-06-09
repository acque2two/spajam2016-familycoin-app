package rainbow_rider.kirin.a0606;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "hkfp2jWmBxqSqrVYvgBKmLpXP";
    private static final String TWITTER_SECRET = "9a8rJNzQwTwfmaXs2v6tJB7W54VOw4TeJAYa5qlZg77pO8pSSo";

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView( R.layout.activity_main );

        setTitle("Crea's");

        MyThread myThread = new MyThread();
        myThread.start();

        Button login_button = (Button) findViewById(R.id.main_login_button);

        Button top_button = (Button) findViewById(R.id.main_top_button);

        Button detail_button = (Button) findViewById(R.id.main_detail_button);

        Button post_button = (Button) findViewById(R.id.main_post_button);

        ImageView actiity_main_imageView = (ImageView) findViewById(R.id.activity_main_imageView);

        TextView activity_main_title_textView = (TextView) findViewById(R.id.activity_main_title_text);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setFillAfter(true);

        TranslateAnimation translateAnimation = new TranslateAnimation(-10.0f, 10.0f, -10.0f, 10.0f);
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);

        //アニメーション動作
        actiity_main_imageView.startAnimation(alphaAnimation);
        activity_main_title_textView.startAnimation(alphaAnimation);

        activity_main_title_textView.setTypeface(Typeface.SERIF);

        assert login_button != null;
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(callIntent);
            }
        });

        assert top_button != null;
        top_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(MainActivity.this, TopActivity.class);
                startActivity(callIntent);
            }
        });

        assert detail_button != null;
        detail_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(callIntent);
            }
        });

        assert post_button != null;
        post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(MainActivity.this, PostActivity.class);
                startActivity(callIntent);
            }


        });

    }

    private class MyThread extends Thread {
        public void run() {
            //時間のかかる処理実行します。今回は仮で10秒停止させています。
            try {
                //1秒停止します。
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            //画面のtextViewへ"処理が完了しました。"を表示させる。
            Log.d("処理が完了しました。", "----------------------------------------");
            Intent callIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(callIntent);
        }
    }
}
