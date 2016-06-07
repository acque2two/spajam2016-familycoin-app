package rainbow_rider.kirin.a0606;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import rainbow_rider.kirin.a0606.Data.Data;
import rainbow_rider.kirin.a0606.Data.User;
import rainbow_rider.kirin.a0606.transfer.user.UserAdd;

public class LoginActivity extends AppCompatActivity {

    private TwitterLoginButton loginButton;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        loginButton = ( TwitterLoginButton ) findViewById( R.id.twitter_login_button );

        loginButton.setCallback( new Callback<TwitterSession>() {
            @Override
            public void success( Result<TwitterSession> result ) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                TwitterSession session = result.data;
                // TODO: Remove toast and use the TwitterSession's userID
                // with your app's user model
                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                Toast.makeText( getApplicationContext(), msg, Toast.LENGTH_LONG ).show();
                User userData = new User();
                userData.setUser_id( session.getUserId() );
                userData.setUser_name( session.getUserName() );
                new UserAdd(userData) {
                    @Override
                    protected void onPostExecute( Data data ) {
                        super.onPostExecute( data );
                            Toast.makeText( getApplicationContext(), "Sent to server.", Toast.LENGTH_LONG ).show();
                            startActivity( new Intent( LoginActivity.this, TopActivity.class ) );
                            Toast.makeText( getApplicationContext(), "Server connection ERROR", Toast.LENGTH_LONG )
                                 .show();
                    }
                }.execute();
            }

            @Override
            public void failure( TwitterException exception ) {
                Log.d( "TwitterKit", "Login with Twitter failure", exception );
            }
        } );
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data ) {
        super.onActivityResult( requestCode, resultCode, data );
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult( requestCode, resultCode, data );
    }

}
