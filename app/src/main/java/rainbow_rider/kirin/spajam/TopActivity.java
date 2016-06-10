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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import rainbow_rider.kirin.spajam.Data.Question;
import rainbow_rider.kirin.spajam.Data.User;


public class TopActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TopFragment.OnTopFragmentListener {
    NavigationView navigationView;
///     /*3=befor_code
///    public Data listdata = new Data();
///    public Data listdata = new Data();
///    private ItemListAdapter mAdapter;
    private AbsListView mListView;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText( getApplicationContext(),"ようこそ",Toast.LENGTH_LONG ).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Crea's");
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        user = setUserData(intent);

        TextView a = (TextView) findViewById(R.id.nav_header_top_textView);
        Log.d("twitter_user_id",user.getUser_name());
        //a.setText(user.getUser_name());

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        //        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_top_fragment, TopFragment.newInstance("1",""))
                .addToBackStack("新規投稿")
                .commit();

//        mListView.setAdapter( mAdapter);

        //for ( int i=0; listdata.getRecipe().get(i).getImage_url() != null ; i++ ){
        //    mAdapter.add(listdata.getRecipe().get(i).getImage_url())
        //}

        // 過去の遺物
        //ArrayAdapter<String> la = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, talk_list);
        //ListView set_listView = (ListView) findViewById( R.id.content_top_listView);
        //set_listView.setAdapter( la );

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        if (id == R.id.search) {
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.activity_top_drawer_japanese) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_top_fragment, TopFragment.newInstance( "1","Japanese"))
                    .addToBackStack("国語")
                    .commit();
        } else if (id == R.id.activity_top_drawer_math) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_top_fragment, TopFragment.newInstance( "2","math"))
                    .addToBackStack("数学")
                    .commit();
        } else if (id == R.id.activity_top_drawer_science) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_top_fragment, TopFragment.newInstance( "3","science"))
                    .addToBackStack("理科")
                    .commit();
        } else if (id == R.id.activity_top_drawer_social_studies) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_top_fragment, TopFragment.newInstance( "4","social_studies"))
                    .addToBackStack("社会")
                    .commit();
        } else if (id == R.id.activity_top_drawer_english) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_top_fragment, TopFragment.newInstance( "5","english"))
                    .addToBackStack("英語")
                    .commit();
        } else if (id == R.id.activity_top_drawer_other) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_top_fragment, TopFragment.newInstance( "6","other"))
                    .addToBackStack("その他")
                    .commit();
        } else if (id == R.id.activity_top_drawer_rankings) {
            Intent callintent = new Intent(TopActivity.this, RankingActivity.class);
            startActivity(callintent);
        } else if (id == R.id.activity_top_drawer_mydata) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public void onTopFragmentItemClick(Question question) {
        Intent callInTent = new Intent(TopActivity.this, DetailActivity.class);
        Question q = question;
        callInTent.putExtra("question", q);
        callInTent.putExtra("myUser", user);
        startActivity(callInTent);
        //画面遷移時
        //callIntent.putExtra("user_id", user.getUser_id());
        //callIntent.putExtra("user_name",user.getUser_name());
    }

    private User setUserData(Intent intent) {
        User u = new User();
        u.setUser_id(intent.getLongExtra("user_id", -1));
        u.setUser_name(intent.getStringExtra("user_name"));
        return u;
    }



}
