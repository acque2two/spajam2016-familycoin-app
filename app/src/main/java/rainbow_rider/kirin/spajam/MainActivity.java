package rainbow_rider.kirin.spajam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setTitle(getString(R.string.app_name));

        Button detail_button = (Button) findViewById(R.id.main_detail_button);
        Button post_button = (Button) findViewById(R.id.main_post_button);
        Button login_button = (Button) findViewById(R.id.main_login_button);

        detail_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callintent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(callintent);
            }
        });

        post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callintent = new Intent(MainActivity.this, PostActivity.class);
                startActivity(callintent);
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callintent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(callintent);
            }
        });

        loadName(MainActivity.this);
    }


    private String loadName(Context context) {
        // アプリ標準の Preferences を取得する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        Toast.makeText(MainActivity.this, sp.getString("name", ""), Toast.LENGTH_LONG).show();

        return sp.getString("name", "");

    }
}
/*過去の遺物
        MyThread myThread = new MyThread();
        myThread.start();

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

        //font設定
        activity_main_title_textView.setTypeface(Typeface.SERIF);

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
*/