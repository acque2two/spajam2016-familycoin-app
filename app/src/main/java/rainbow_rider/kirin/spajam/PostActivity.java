package rainbow_rider.kirin.spajam;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;

import rainbow_rider.kirin.spajam.Data.Genre;
import rainbow_rider.kirin.spajam.Data.User;

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

        Spinner genre_spinner = (Spinner) findViewById(R.id.post_genre_spinner);
        EditText title = (EditText) findViewById(R.id.post_title);
        EditText mainText = (EditText) findViewById(R.id.post_mainText);

        assert mainText != null;
        mainText.setHint("例：Ａ、Ｂ、Ｃの３種類の本があります。\n" +
                "この３つの本の中で一番評判がよく面白い本がＣだそうです。" +
                "\nでも本屋によるお客たちは必ずＡの本を買っていきます。" +
                "\nなんででしょう？"
        );

        assert imageView != null;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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
            ){
                Spinner spinner = (Spinner) parent;
                // 選択されたアイテムを取得します
                spinner.getSelectedItemId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

    }

    private User setUserData(Intent intent){
        User u = new User();
//        u.setUser_id(intent.getLongExtra("user_id", i));
//        u.setUser_name(intent.getStringExtra("user_name"));
        return u;
    }

    private String getGalleryPath() {
        return Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DCIM + "/";
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {

    }

    //Bitmap
    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        assert parcelFileDescriptor != null;
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    //アクションバーの設定
    @Override
    public boolean onCreateOptionsMenu(final Menu menu ) {
        //menu.add( 1, 0, Menu.NONE, "設定" );

        //杉山追加
        final Spinner genre_spinner = (Spinner) findViewById(R.id.post_genre_spinner);
        final Spinner answer_spinner = (Spinner) findViewById(R.id.post_selectAnswer_spinner);
        final EditText title = (EditText) findViewById(R.id.post_title);
        final EditText mainText = (EditText) findViewById(R.id.post_mainText);
        //------

        //menu.findItem(R.id.menu_move_to_add_friend_button);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu_post, menu );
        setTitle("問題投稿");

        final MenuItem a = menu.findItem( R.id.menu_detail_image_button );
        a.setTitle( "投稿" );
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
//                    genre.setGenre_id(1);
                } else if( genreName.equals("数学") ) {
//                    genre.setGenre_id(2);
                } else if( genreName.equals("理科") ) {
//                    genre.setGenre_id(3);
                } else if( genreName.equals("社会") ) {
//                    genre.setGenre_id(4);
                } else if( genreName.equals("英語") ) {
//                    genre.setGenre_id(5);
                } else if( genreName.equals("その他") ) {
//                    genre.setGenre_id(6);
                } else {
//                    genre.setGenre_id(7);
                }
                String answer = answer_spinner.getSelectedItem().toString();
                Long trueId;

                //answerのidは A = 1, B = 2, C = 3, D = 4    NullPointer対策にelse5を追加
                if ( genreName.equals("A") ){
                    trueId = 1L;
                } else if( genreName.equals("B") ) {
                    trueId = 2L;
                } else if( genreName.equals("C") ) {
                    trueId = 3L;
                } else if( genreName.equals("D") ) {
                    trueId = 4L;
                } else {
                    trueId = 5L;
                }

//                Anss anss = new Anss();

                ArrayList<String> answerText = new ArrayList<String>();
                answerText.add(answerA.getText().toString());
                answerText.add(answerB.getText().toString());
                answerText.add(answerC.getText().toString());
                answerText.add(answerD.getText().toString());

                int i = 1;
                for (String a : answerText) {
                    i ++;
                }

//                question.setQ_name( title.getText().toString() );
//                question.setGenre(genre);
//                question.setQ_text( mainText.getText().toString() );
//                question.setTrue_id(trueId);
//                question.setAnswer(anss);
//                question.setUser(user);



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
}
