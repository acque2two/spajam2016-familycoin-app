package rainbow_rider.kirin.a0606;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature( Window.FEATURE_ACTION_BAR );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        menu.add( 1, 0, Menu.NONE, "設定" );

        //menu.findItem(R.id.menu_move_to_add_friend_button);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu_detail, menu );
        MenuItem a = menu.findItem( R.id.menu_detail_favourite_button );
        a.setTitle( "お気に入り" );

        a.setCheckable( true );
        a.setOnMenuItemClickListener( new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick( MenuItem item ) {
            //    Intent callIntent = new Intent( DetailActivity.this, AddFriendActivity.class );
            //    startActivity( callIntent );
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
