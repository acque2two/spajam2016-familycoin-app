package rainbow_rider.kirin.a0606;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.Toast;

import com.twitter.sdk.android.core.models.User;

public class TopActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
///     /*3=befor_code
///    public Data listdata = new Data();
///    private ItemListAdapter mAdapter;
    private AbsListView mListView;
    NavigationView navigationView;
    private final String[] talk_ilist = new String[]{"aa", "bb", "cc", "dd", "ee", "ff"};
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        //setSupportActionBar(toolbar);

        Intent intent = getIntent();
///        user = setUserData(intent);

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

///        Genre genre = new Genre();
///        genre.setGenre_id(0);
///        new RecipeGenreGet(genre) {
///            @Override
///            protected void onPostExecute(Data data) {
///                super.onPostExecute(data);
///
///                mAdapter = new ItemListAdapter(TopActivity.this.getApplicationContext(), R.layout.activity_top);
///                mListView = (AbsListView) findViewById(R.id.list_view);
///                Data reply = getReply();
///                //mListView.setAdapter( mAdapter);
///
///                //for ( int i=0; listdata.getRecipe().get(i).getImage_url() != null ; i++ ){
///                //    mAdapter.add(listdata.getRecipe().get(i).getImage_url())
///                //}
///
///                ListItem listItem = new ListItem();
///                List<ListItem> listItemList = new ArrayList<ListItem>();
///                for (int i = 0; i < reply.getRecipe().size(); i++) {
/////                        String a = listdata.getRecipe().get(i).getImage_url();
/////                        String b = listdata.getRecipe().get(i).getRecipe_name();
///                    String a = reply.getRecipe().get(i).getImage_url();
///                    String b = reply.getRecipe().get(i).getRecipe_name();
///                    listItem.setFoodIcon(a);
///                    listItem.setTitle(b);
///                    listItemList.add(listItem);
///                    Log.d("a", a + b);
///                }
///                Log.d("Comp", "Leate");
///
///                mAdapter.addAll(listItemList);
///
///            }
///        }.execute();
/////        mListView.setAdapter( mAdapter);

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

        } else if (id == R.id.activity_top_drawer_math) {

        } else if (id == R.id.activity_top_drawer_science) {

        } else if (id == R.id.activity_top_drawer_social_studies) {

        } else if (id == R.id.activity_top_drawer_english) {

        } else if (id == R.id.activity_top_drawer_other) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    ///private User setUserData(Intent intent) {
    ///    User u = new User();
    ///    u.setUser_id(intent.getLongExtra("user_id", -1));
    ///    return u;
    ///}
}
