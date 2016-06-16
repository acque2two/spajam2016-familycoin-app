package rainbow_rider.kirin.spajam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import net.arnx.jsonic.JSON;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Family;
import rainbow_rider.kirin.spajam.Data.User;
import rainbow_rider.kirin.spajam.Data.Work;
import rainbow_rider.kirin.spajam.transfer.async.achievement.unapproved.AsyncUnapprovedAdd;
import rainbow_rider.kirin.spajam.transfer.async.family.AsyncAllData;

public class DetailActivity extends AppCompatActivity {

    public Data allData = new Data();
    public String my_id;
    int w_id;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        requestWindowFeature( Window.FEATURE_ACTION_BAR );
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detail );


        Intent intent = getIntent();
        w_id = intent.getIntExtra( "w_id", -1 );
        //TopActivityからQuestionを受け取る。

        Family f = new Family();
        f.setF_id( "rainbow" );
        loadData( DetailActivity.this.getApplicationContext() );

        new AsyncAllData( f ) {
            @Override
            protected void onPostExecute( Data data ) {
                super.onPostExecute( data );
                final Data allData = getReply();
                this.allData = allData;

                Work work = new Work();
                User user = new User();
                Family f = new Family();
                f.setF_id( "rainbow" );
                work = allData.getFamily().get( 0 ).getWork().get( w_id );
/*
        for (Work w : allData.getFamily().get(0).getWork()) {
            if (w.getW_id() == w_id) {
                work = w;

            }
            break;
        }
*/
                for ( User u : allData.getFamily().get( 0 ).getUser() ) {
                    if ( my_id.equals( u.u_name ) ) {
                        user = u;
                        break;
                    }
                }

                final Button send_button = ( Button ) findViewById( R.id.detail_send_button );
                ImageView imageView = ( ImageView ) findViewById( R.id.imageView );

                TextView title_text = ( TextView ) findViewById( R.id.detail_title_text );
                TextView user_text = ( TextView ) findViewById( R.id.detail_user_text );
                TextView main_text = ( TextView ) findViewById( R.id.detail_main_text );

                assert main_text != null;
                assert title_text != null;
                assert user_text != null;

                main_text.setText( work.getW_text() );
                title_text.setText( work.getW_name() );
                user_text.setText( user.getU_name() );

                //image
                final boolean[] send = new boolean[] { false };
                //send button
                assert send_button != null;
                send_button.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick( View v ) {
                        new AsyncUnapprovedAdd( allData ) {
                            @Override
                            protected void onPostExecute( Data data ) {
                                super.onPostExecute( data );
                                finish();
                            }
                        }.execute();
                    }
                } );
            }
        }.execute();
    }

    private boolean loadData( Context context ) {
        // アプリ標準の Preferences を取得する
        SharedPreferences sp = context.getSharedPreferences("allData",Context.MODE_PRIVATE);

        allData = JSON.decode( sp.getString( "DATA_JSON", "{}" ), Data.class );

        boolean ans;
        ans = allData.getFamily() != null;
        my_id = sp.getString( "my_id", "" );

        return ans;
    }
    @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            finish();
            return true;
        }
        return false;
    }
}
/*
    //option
    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        //menu.add( 1, 0, Menu.NONE, "設定" );

        //menu.findItem(R.id.menu_move_to_add_friend_button);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu_detail, menu );
        MenuItem a = menu.findItem( R.id.menu_detail_favourite_button );
        a.setTitle( "★" );

        a.setCheckable( true );
        a.setOnMenuItemClickListener( new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick( MenuItem item ) {
            //    Intent callIntent = new Intent( DetailActivity.this, AddFriendActivity.class );
            //    startActivity( callIntent );
                Toast.makeText(DetailActivity.this,"okini",Toast.LENGTH_SHORT).show();

                //お気に入り処理

                return false;
            }
        } );

        return super.onCreateOptionsMenu( menu );

    }


    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {

        if ( item.getItemId() == 0 ) {
        //    Intent callIntent = new Intent( TopActivity.this, SettingMainActivity.class );
        //    startActivity( callIntent );
        } else if ( item.getItemId() == 1 ) {
            Toast.makeText( this, "Menu2", Toast.LENGTH_SHORT ).show();
        }

        return true;
    }

}
*/
