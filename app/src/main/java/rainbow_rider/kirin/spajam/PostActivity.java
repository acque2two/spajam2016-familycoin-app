package rainbow_rider.kirin.spajam;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Family;
import rainbow_rider.kirin.spajam.Data.Genre;
import rainbow_rider.kirin.spajam.Data.User;
import rainbow_rider.kirin.spajam.Data.Work;
import rainbow_rider.kirin.spajam.transfer.async.work.AsyncWorkAdd;

public class PostActivity extends AppCompatActivity {
    private static final int RESULT_PICK_IMAGEFILE = 1001;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature( Window.FEATURE_ACTION_BAR );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        //Intent intent = getIntent();
        //user = setUserData(intent);

        imageView = (ImageView) findViewById(R.id.imageView);

        final Spinner genre_spinner = (Spinner) findViewById(R.id.post_genre_spinner);
        EditText title = (EditText) findViewById(R.id.post_title);
        EditText mainText = (EditText) findViewById(R.id.post_mainText);
        EditText pointText = (EditText) findViewById(R.id.post_point_text);
        FrameLayout postImageLayout = (FrameLayout) findViewById(R.id.post_image_layout);


        assert postImageLayout != null;
        postImageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int p = genre_spinner.getSelectedItemPosition();
                if (p > 0) {
                    Intent callIntent = new Intent(PostActivity.this, PhotoActivity.class);
                    callIntent.putExtra("genre", p);
                    startActivityForResult(callIntent, 1);
                } else {
                    Toast.makeText(PostActivity.this, "ジャンルを先に選択してください", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Genre spinner
        ArrayAdapter<String> genre_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        genre_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        genre_adapter.add("選択してください");
        genre_adapter.add("掃除");
        genre_adapter.add("料理");
        genre_adapter.add("洗濯");
        genre_adapter.add("勉強");
        genre_adapter.add("買い物");
        genre_adapter.add("その他");

        // アダプターを設定します
        assert genre_spinner != null;
        genre_spinner.setAdapter(genre_adapter);
        // スピナーのアイテムが選択された時に呼び出されるコールバックリスナーを登録します
        genre_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(
                    AdapterView<?> parent, View view,
                    int position, long id
            ) {
                Spinner spinner = (Spinner) parent;
                // 選択されたアイテムを取得します
                spinner.getSelectedItemId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

    }

    private User setUserData(Intent intent) {
        User u = new User();
//        u.setUser_id(intent.getLongExtra("user_id", i));
//        u.setUser_name(intent.getStringExtra("user_name"));
        return u;
    }

    private String getGalleryPath() {
        return Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DCIM + "/";
    }

    //Result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        int image = resultData.getIntExtra("image", R.drawable.ic_menu_share);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    imageView.setImageResource(image);
                } else {

                }
                break;
            default:
                break;
        }
    }
}

/*
    //アクションバーの設定
    @Override
    public boolean onCreateOptionsMenu(final Menu menu ) {
        //menu.add( 1, 0, Menu.NONE, "設定" );

        //杉山追加
        final Spinner genre_spinner = (Spinner) findViewById(R.id.post_genre_spinner);
        final EditText title = (EditText) findViewById(R.id.post_title);
        final EditText mainText = (EditText) findViewById(R.id.post_mainText);
        //------

        //menu.findItem(R.id.menu_move_to_add_friend_button);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu_post, menu );
        setTitle( "仕事の追加" );

        final MenuItem a = menu.findItem( R.id.menu_detail_image_button );
        a.setTitle( "追加" );
        a.setCheckable( true );
        a.setOnMenuItemClickListener( new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick( MenuItem item ) {
                //    Intent callIntent = new Intent( DetailActivity.this, AddFriendActivity.class );
                //    startActivity( callIntent );
//                Question question = new Question();
                Genre genre = new Genre();

                String genreName = genre_spinner.getSelectedItem().toString();

                //genreをidに変換    NullPointer対策にelse
                if ( genreName.equals("国語") ){
                    genre.setG_id( 1 );
                } else if( genreName.equals("数学") ) {
                    genre.setG_id( 2 );
                } else if( genreName.equals("理科") ) {
                    genre.setG_id( 3 );
                } else if( genreName.equals("社会") ) {
                    genre.setG_id( 4 );
                } else if( genreName.equals("英語") ) {
                    genre.setG_id( 5 );
                } else if( genreName.equals("その他") ) {
                    genre.setG_id( 6 );
                } else {
                    genre.setG_id( 7 );
                }


                Work work = new Work();
                work.setGenre( genre );

                ArrayList<Work> workArrayList = new ArrayList<Work>();
                workArrayList.add( work );


                User user = new User();
                ArrayList<User> userArrayList = new ArrayList<User>();
                userArrayList.add( user );

                Family family = new Family();
                ArrayList<Family> familyArrayList = new ArrayList<>();
                familyArrayList.add( family );


                Data data = new Data();
                data.setFamily( familyArrayList );

                new AsyncWorkAdd( data ) {
                    @Override
                    protected void onPreExecute( ) {
                        super.onPreExecute();
                        ( PostActivity.this.findViewById( R.id.progressBar ) ).setVisibility( View.VISIBLE );

                    }

                    @Override
                    protected void onPostExecute( Data data ) {
                        super.onPostExecute( data );
                        Toast.makeText( getApplicationContext(), "送信が完了しました。", Toast.LENGTH_LONG ).show();
                        PostActivity.this.finish();
                    }
                }.execute();

                Toast.makeText(PostActivity.this, "oni", Toast.LENGTH_SHORT).show();
                return false;
            }
        } );

        return super.onCreateOptionsMenu( menu );

    }

    //ハンバーガー押されたときの動作
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
*/
