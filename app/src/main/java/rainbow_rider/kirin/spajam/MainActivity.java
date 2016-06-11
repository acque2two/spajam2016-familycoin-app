package rainbow_rider.kirin.spajam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;

import net.arnx.jsonic.JSON;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.User;

public class MainActivity extends AppCompatActivity {

    private User user = new User();
    private Data allData = new Data();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setTitle(getString(R.string.app_name));
        Intent callIntent;
        if ( !loadData( MainActivity.this ) ) {
            callIntent = new Intent( MainActivity.this, LoginActivity.class );
            startActivityForResult( callIntent , 1);
        }else{
            callIntent = new Intent( MainActivity.this, TopActivity.class );
            startActivity( callIntent );
        }

        ImageView activity_main_imageView = (ImageView) findViewById(R.id.activity_main_imageView);
        ImageView activity_main2_imageView = (ImageView) findViewById(R.id.activity_main2_imageView);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setFillAfter(true);

        //アニメーション動作
        activity_main_imageView.startAnimation(alphaAnimation);

        activity_main2_imageView.startAnimation(alphaAnimation);

        Button detailButton = (Button) findViewById(R.id.main_detail_button);
        Button postButton = (Button) findViewById(R.id.main_post_button);
        Button loginButton = (Button) findViewById(R.id.main_login_button);
        Button topButton = (Button) findViewById(R.id.main_top_button);

        //MyThread myThread = new MyThread();
        //myThread.start();

        topButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callintent = new Intent(MainActivity.this, TopActivity.class);
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

    private boolean loadData(Context context) {
        // アプリ標準の Preferences を取得する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        allData = JSON.decode(sp.getString("DATA_JSON", "{}"), Data.class);

        boolean ans;
        ans = allData.getFamily() != null;

        return ans;
    }

    private boolean saveData(Context context) {
        // アプリ標準の Preferences を取得する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor spedit = sp.edit();
        spedit.putString("DATA_JSON", JSON.encode(allData));
        spedit.apply();
        return true;

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Intent callIntent = new Intent(MainActivity.this, TopActivity.class);
                    startActivity(callIntent);
                } else {

                }
                break;
            default:
                break;
        }
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

