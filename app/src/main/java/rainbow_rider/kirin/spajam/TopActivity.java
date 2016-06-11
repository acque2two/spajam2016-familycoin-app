package rainbow_rider.kirin.spajam;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.arnx.jsonic.JSON;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.User;


public class TopActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TopFragment.OnTopFragmentListener {
    NavigationView navigationView;
///     /*3=befor_code
///    public Data listdata = new Data();
///    public Data listdata = new Data();
///    private ItemListAdapter mAdapter;

    User user;
    Data allData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText( getApplicationContext(),"ようこそ！",Toast.LENGTH_LONG ).show();
        loadData( getApplicationContext() );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Family Coin");

        User user = (User) getIntent().getSerializableExtra("user");
        user = allData.family.get( 0 ).users.get( 0 );
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                Intent callIntent = new Intent(TopActivity.this, PostActivity.class);
                ///callIntent.putExtra("user_id", user.getUser_id());
                startActivity(callIntent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_top_drawer_layout);
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        //        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        View header = LayoutInflater.from(this).inflate(R.layout.nav_header_top, null);
        navigationView.addHeaderView(header);
        TextView text = (TextView) header.findViewById(R.id.nav_header_top_textView);
        text.setText(user.getU_name());

        String fId = user.getF_id();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_top_fragment, TopFragment.newInstance("1",fId))
                .addToBackStack("新規投稿")
                .commit();

        // 過去の遺物
        //ArrayAdapter<String> la = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, talk_list);
        //ListView set_listView = (ListView) findViewById( R.id.content_top_listView);
        //set_listView.setAdapter( la );

    }


    private boolean loadData( Context context ) {
        // アプリ標準の Preferences を取得する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences( context );

        allData = JSON.decode( sp.getString( "DATA_JSON", "{}" ), Data.class );

        boolean ans;
        ans = allData.getFamily() != null;

        return ans;
    }

    private boolean saveData( Context context ) {
        // アプリ標準の Preferences を取得する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences( context );
        SharedPreferences.Editor spedit = sp.edit();
        spedit.putString( "DATA_JSON", JSON.encode( allData ) );
        spedit.apply();
        return true;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.top, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.top_search) {
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
                    })
                    .show();

            return true;
        }
        if (id == R.id.top_end_application) {
            //アプリを終了する
            moveTaskToBack(true);
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
        String fId = user.getF_id();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (id == R.id.activity_top_drawer_cleaning) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_top_fragment, TopFragment.newInstance( "1",fId))
                    .addToBackStack("そうじ")
                    .commit();
            toolbar.setTitle("そうじクエスト");
        } else if (id == R.id.activity_top_drawer_cuisine) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_top_fragment, TopFragment.newInstance( "2",fId))
                    .addToBackStack("りょうり")
                    .commit();
            toolbar.setTitle("りょうりクエスト");
        } else if (id == R.id.activity_top_drawer_washing) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_top_fragment, TopFragment.newInstance( "3",fId))
                    .addToBackStack("せんたく")
                    .commit();
            toolbar.setTitle("せんたくクエスト");
        } else if (id == R.id.activity_top_drawer_social_study) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_top_fragment, TopFragment.newInstance( "4",fId))
                    .addToBackStack("べんきょう")
                    .commit();
            toolbar.setTitle("べんきょうクエスト");
        } else if (id == R.id.activity_top_drawer_shopping) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_top_fragment, TopFragment.newInstance( "5",fId))
                    .addToBackStack("かいもの")
                    .commit();
            toolbar.setTitle("かいものクエスト");
        } else if (id == R.id.activity_top_drawer_other) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_top_fragment, TopFragment.newInstance( "6",fId))
                    .addToBackStack("そのほか")
                    .commit();
            toolbar.setTitle("そのほかのクエスト");
        } else if (id == R.id.activity_top_drawer_my_data) {


        } else if (id == R.id.activity_top_drawer_family_data) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_top_fragment, FamilyDataFragment.newInstance( "10", fId))
                    .addToBackStack("かぞくのじょうほう")
                    .commit();
            toolbar.setTitle("家族の情報");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_top_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }


    // 安全に削除できませんでした acq
    //@Override
    //public void onTopFragmentItemClick(Void) {

/*        Intent callInTent = new Intent(TopActivity.this, DetailActivity.class);
        Question q = question;
        callInTent.putExtra("myUser", user);
        startActivity(callInTent);
        //画面遷移時*/
        //callIntent.putExtra("user_id", user.getUser_id());
        //callIntent.putExtra("user_name",user.getUser_name());
    //}

}
