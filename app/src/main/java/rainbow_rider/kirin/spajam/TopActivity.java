package rainbow_rider.kirin.spajam;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.F;
import rainbow_rider.kirin.spajam.Data.User;


public class TopActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, TopFragment.OnTopFragmentListener {
    NavigationView navigationView;

    Data allData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(getApplicationContext(), "ようこそ！", Toast.LENGTH_LONG).show();
        allData = F.Load();
        Intent intent = getIntent();
        // allData = (Data)intent.getSerializableExtra("allData");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

//            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                Log.d("aaaaaaa", "bbbbbbb");
//                return false;
//            }
//        });

        //user = allData.family.get(0).users.get(0);

        /*user.setF_id("niji");
        user.setAdmin(true);
        user.setAdult(true);
        user.setScore( 300 );
        user.setSex(true);
        user.setU_id("三郎");
        user.setU_id("saburou");*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        //if ( == false) {
        //    fab.setVisibility(View.GONE);
        //}

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(TopActivity.this, PostActivity.class);
                startActivity(callIntent);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Family Coin");
        toolbar.inflateMenu(R.menu.top);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_top_drawer_layout);

        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle (
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        );

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = LayoutInflater.from(this).inflate(R.layout.nav_header_top, null);
        navigationView.addHeaderView(header);

        //TextView text = (TextView) header.findViewById(R.id.nav_header_top_textView);
        //text.setText(user.getU_name());

        String fId = allData.getFamily().get(0).getF_id();

        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.content_top_fragment, TopFragment.newInstance("1", fId))
            .addToBackStack("新規投稿")
            .commit();

        // 過去の遺物
        //ArrayAdapter<String> la = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, talk_list);
        //ListView set_listView = (ListView) findViewById( R.id.content_top_listView);
        //set_listView.setAdapter( la );

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_top_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //noinspection SimplifiableIfStatement

        switch (item.getItemId()) {
            case R.id.top_search:
                final EditText editView = new EditText(TopActivity.this);
                new AlertDialog.Builder(TopActivity.this)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setTitle("検索文字列入力")
                    //setViewにてビューを設定します。
                    .setView(editView)
                    .setPositiveButton("検索", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            //入力した文字をトースト出力する
                            Toast.makeText(TopActivity.this,
                                editView.getText().toString(),
                                Toast.LENGTH_LONG).show();
                            //listviewを更新
                        }
                    })
                    .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        }
                    }).show();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        User user = (User) getIntent().getSerializableExtra("user");
        String fId = allData.getFamily().get(0).getF_id();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        navigationView.setCheckedItem(id);

        if (id == R.id.activity_top_drawer_cleaning) {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_top_fragment, TopFragment.newInstance("1", fId))
                .addToBackStack("そうじ")
                .commit();
            toolbar.setTitle("そうじクエスト");
        } else if (id == R.id.activity_top_drawer_cuisine) {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_top_fragment, TopFragment.newInstance("2", fId))
                .addToBackStack("りょうり")
                .commit();
            toolbar.setTitle("りょうりクエスト");
        } else if (id == R.id.activity_top_drawer_washing) {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_top_fragment, TopFragment.newInstance("3", fId))
                .addToBackStack("せんたく")
                .commit();
            toolbar.setTitle("せんたくクエスト");
        } else if (id == R.id.activity_top_drawer_study) {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_top_fragment, TopFragment.newInstance("4", fId))
                .addToBackStack("べんきょう")
                .commit();
            toolbar.setTitle("べんきょうクエスト");
        } else if (id == R.id.activity_top_drawer_shopping) {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_top_fragment, TopFragment.newInstance("5", fId))
                .addToBackStack("かいもの")
                .commit();
            toolbar.setTitle("かいものクエスト");
        } else if (id == R.id.activity_top_drawer_other) {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_top_fragment, TopFragment.newInstance("6", fId))
                .addToBackStack("そのほか")
                .commit();
            toolbar.setTitle("そのほかのクエスト");
        } else if (id == R.id.activity_top_drawer_my_data) {


        } else if (id == R.id.activity_top_drawer_family_data) {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_top_fragment, FamilyDataFragment.newInstance("10", fId))
                .addToBackStack("かぞくのじょうほう")
                .commit();
            toolbar.setTitle("家族の情報");
        } else if (id == R.id.activity_top_drawer_go_to_nfc_activity) {
            Intent callIntent = new Intent(TopActivity.this, NfcActivity.class);
            startActivity(callIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_top_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public void onTopFragmentItemClick(int wId) {
        Intent callIntent = new Intent(TopActivity.this, DetailActivity.class);
        callIntent.putExtra("w_id", wId);
        startActivity(callIntent);
    }

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
                Toast.makeText(TopActivity.this,"okini",Toast.LENGTH_SHORT).show();

                //お気に入り処理

                return false;
            }
        } );

        return super.onCreateOptionsMenu( menu );

    }

}
