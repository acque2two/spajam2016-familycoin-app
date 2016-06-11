package rainbow_rider.kirin.spajam;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.User;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //TopActivityからQuestionを受け取る。

        Intent intent = getIntent();

        final Button send_button = (Button) findViewById(R.id.detail_send_button);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        TextView title_text = (TextView) findViewById(R.id.detail_title_text);
        TextView user_text = (TextView) findViewById(R.id.detail_user_text);
        TextView main_text = (TextView) findViewById(R.id.detail_main_text);

        assert main_text != null;
        assert title_text != null;
        assert user_text != null;

        //image
        final boolean[] send = new boolean[]{false};
        //send button
        assert send_button != null;
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (send[0]) {
//                  Toast.makeText(DetailActivity.this,"ans",Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder alert01 = new AlertDialog.Builder(DetailActivity.this);
                    //ダイアログタイトルをセット
                    alert01.setTitle("正解は" + "A");
                    //ダイアログメッセージをセット
                    alert01.setMessage("解説");
                    //ダイアログ表示
                    alert01.show();
                } else {
                    Toast.makeText(DetailActivity.this, "send", Toast.LENGTH_SHORT).show();

                    //解答送信

                    send_button.setText("正解を見る");
                    send[0] = true;
                }
            }
        });
    }

    //ここ
    private User setUserData(Intent intent) {
        User u = new User();
        return u;
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
