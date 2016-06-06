package rainbow_rider.kirin.a0606;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        Intent callintent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(callintent);

        Button login_button = (Button) findViewById(R.id.main_login_button);

        Button top_button = (Button) findViewById(R.id.main_top_button);

        Button detail_button = (Button) findViewById(R.id.main_detail_button);

        Button post_button = (Button) findViewById(R.id.main_post_button);

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
}
