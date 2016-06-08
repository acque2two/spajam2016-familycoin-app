package rainbow_rider.kirin.a0606;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
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

import org.w3c.dom.Text;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rainbow_rider.kirin.a0606.Data.Data;
import rainbow_rider.kirin.a0606.Data.User;
import rainbow_rider.kirin.a0606.transfer.question.QuestionGet;

public class DetailActivity extends AppCompatActivity {

    private Uri uri;
    private Data Qdata;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature( Window.FEATURE_ACTION_BAR );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        user = setUserData(intent);

        new QuestionGet(){
            @Override
            protected void onPostExecute(Data data) {
                super.onPostExecute(data);
                Qdata = data;
            }
        }.execute();

        final Button send_button = (Button) findViewById(R.id.detail_send_button);
        Spinner answer_spinner = (Spinner) findViewById(R.id.detail_answer_spinner);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        TextView genre_text = (TextView) findViewById(R.id.detail_genre_text);
        TextView title_text = (TextView) findViewById(R.id.detail_title_text);
        TextView user_text = (TextView) findViewById(R.id.detail_user_text);
        TextView main_text = (TextView) findViewById(R.id.detail_main_text);
        final TextView ansA_text = (TextView) findViewById(R.id.detail_answerA_text);
        final TextView ansB_text = (TextView) findViewById(R.id.detail_answerB_text);
        final TextView ansC_text = (TextView) findViewById(R.id.detail_answerC_text);
        final TextView ansD_text = (TextView) findViewById(R.id.detail_answerD_text);


        String strgenre = "genre";
        String strtitle = "title";
        String struser = "user";
        String strmain = "aaaa\n\n\n\naaaaa";

        List<TextView> ansList = new ArrayList<TextView>(){
            {
                add(ansA_text);
                add(ansB_text);
                add(ansC_text);
                add(ansD_text);
            }
        };

        assert main_text != null;
        assert genre_text != null;
        assert title_text != null;
        assert user_text != null;

        genre_text.setText(strgenre);
        title_text.setText(strtitle);
        user_text.setText(struser);
        main_text.setText(strmain);

        //answer
        String[] stransList = new String[]{"答えはなんだろうね", "わかんなーい", "なんでー", "の・ぞ・み"};
        for(int i = 0; i < ansList.size(); i++){
            ansList.get(i).setText(stransList[i]);
        }

        //image

        final boolean[] send = new boolean[]{false};
        //send button
        assert send_button != null;
        send_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (send[0]){
//                    Toast.makeText(DetailActivity.this,"ans",Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder alert01 = new AlertDialog.Builder(DetailActivity.this);
                    //ダイアログタイトルをセット
                    alert01.setTitle("正解は" + "A");
                    //ダイアログメッセージをセット
                    alert01.setMessage("解説");
                    //ダイアログ表示
                    alert01.show();
                }else{
                    Toast.makeText(DetailActivity.this,"send",Toast.LENGTH_SHORT).show();
                    send_button.setText("正解を見る");
                    send[0] = true;
                }
            }
        });

        //Answer spinner
        ArrayAdapter<String> answer_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        answer_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        String[] stranswers = new String[]{"選択してください","A", "B", "C", "D"};

        for(String value : stranswers){
            answer_adapter.add(value);
        }

        // アダプターを設定します
        assert answer_spinner != null;
        answer_spinner.setAdapter(answer_adapter);
        // スピナーのアイテムが選択された時に呼び出されるコールバックリスナーを登録します
        answer_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        u.setUser_id(intent.getLongExtra("user_id", -1));
        u.setUser_name(intent.getStringExtra("user_name"));
        return u;
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
