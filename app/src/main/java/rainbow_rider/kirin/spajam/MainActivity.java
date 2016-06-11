package rainbow_rider.kirin.spajam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import rainbow_rider.kirin.spajam.Data.User;

public class MainActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setTitle(getString(R.string.app_name));

       // loadUserData(MainActivity.this);

        Button detailButton = (Button) findViewById(R.id.main_detail_button);
        Button postButton = (Button) findViewById(R.id.main_post_button);
        Button loginButton = (Button) findViewById(R.id.main_login_button);
        Button topButton = (Button) findViewById(R.id.main_top_button);

        topButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callintent = new Intent(MainActivity.this, TopActivity.class);
                User user = new User();
                callintent.putExtra( "user", user );
                startActivity(callintent);
            }
        });

        detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callintent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(callintent);
            }
        });

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callintent = new Intent(MainActivity.this, PostActivity.class);
                startActivity(callintent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callintent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(callintent);
            }
        });

    }

    private boolean loadUserData(Context context) {
        // アプリ標準の Preferences を取得する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        String name =  sp.getString("name", "");
        String u_id = sp.getString("u_id", "");
        String f_id = sp.getString("f_id", "");
        boolean sex = sp.getBoolean("sex", true);
        boolean adult = sp.getBoolean("adult", false);
        boolean admin = sp.getBoolean("admin", false);


        user.setU_name(name);
        user.setU_id(u_id);
        user.setF_id(f_id);
        user.setSex(sex);
        user.setAdult(adult);
        user.setAdmin(admin);

        boolean ans = true;

        return ans;

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
