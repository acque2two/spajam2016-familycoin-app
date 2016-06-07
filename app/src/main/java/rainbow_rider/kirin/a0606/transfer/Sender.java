package rainbow_rider.kirin.a0606.transfer;

import android.os.AsyncTask;
import android.util.Log;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rainbow_rider.kirin.a0606.Data.Ans;
import rainbow_rider.kirin.a0606.Data.Data;
import rainbow_rider.kirin.a0606.Data.Fav;
import rainbow_rider.kirin.a0606.Data.Genre;
import rainbow_rider.kirin.a0606.Data.Multiple.Anss;
import rainbow_rider.kirin.a0606.Data.Multiple.Favs;
import rainbow_rider.kirin.a0606.Data.Multiple.Genres;
import rainbow_rider.kirin.a0606.Data.Multiple.Questions;
import rainbow_rider.kirin.a0606.Data.Multiple.UserAnss;
import rainbow_rider.kirin.a0606.Data.Multiple.Users;
import rainbow_rider.kirin.a0606.Data.Question;
import rainbow_rider.kirin.a0606.Data.User;
import rainbow_rider.kirin.a0606.Data.UserAns;

/**
 * Created by acq on 16/06/02.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */
public class Sender extends AsyncTask<Data, Data, Data> {
    public static final MediaType jsontype = MediaType.parse( "application/json; charset=utf-8" );
    public String urlPath = "/index.html";
    private final String urlHosts = "http://" + ServerInfomation
            .getServerHostName() + ":" + ServerInfomation
            .getServerPort();

    // 入力データ群
    // 全データ
    public Data allData;
    // 受信したデータ
    Data reply;
    private OkHttpClient client = new OkHttpClient();
    private String url;

    public Sender( ) {
        allData = new Data();
    }

    public Sender( User user ) {
        Users userList = new Users();
        userList.set( user );
        allData = new Data();
        allData.setUser( userList );
    }

    public Sender( Question question ) {
        Questions questionList = new Questions();
        questionList.set( question );
        allData = new Data();
        allData.setQuestion( questionList );
    }

    public Sender( Genre genre ) {
        Genres genreList = new Genres();
        genreList.set( genre );
        allData = new Data();
        allData.setGenre( genreList );
    }
    public Sender( Fav fav ) {
        Favs favList = new Favs();
        favList.set( fav );
        allData = new Data();
        allData.setFav( favList );
    }
    public Sender( UserAns userans ) {
        UserAnss userAnsList = new UserAnss();
        userAnsList.set( userans );
        allData = new Data();
        allData.setUserans( userAnsList );
    }

    public Sender( Ans ans ) {
        Anss ansList = new Anss();
        ansList.set( ans );
        allData = new Data();
        allData.setAns( ansList );
    }

    public Sender( Users users ) {
        allData = new Data();
        allData.setUser( users );
    }

    public Sender( Genres genres ) {
        allData = new Data();
        allData.setGenre( genres );
    }


    public Sender( Questions questions ) {
        allData = new Data();
        allData.setQuestion( questions );
    }

    public Sender( Favs favs ) {
        allData = new Data();
        allData.setFav( favs );
    }
    public Sender( UserAnss useranss ) {
        allData = new Data();
        allData.setUserans( useranss );
    }

    public Sender( Anss anss ) {
        allData = new Data();
        allData.setAns( anss );
    }

    public Sender( Data data ) {
        allData = data;
    }

    public void setPath( String path ) {
        this.url = this.urlHosts + path;
    }

    public Data getReply( ) {
        return reply;
    }

    public boolean isSuccess( ) {return reply.isStatus();}


    private String Post( String json ) throws IOException {
        Log.d( "TRANS/POST/START", "Post function start." );
        RequestBody body = RequestBody.create( jsontype, json );
        Request request = new Request.Builder()
                .url( url )
                .post( body )
                .build();
        Response response = client.newCall( request ).execute();
        if( response.code() != 200 ) {
            Log.e( "TRANS/POST/ERROR", "Responce code is " + response.code() + "!" );
            return "{\"status\":false}";
        }
        Log.d( "TRANS/POST/SUCCESS", "POST Successful! " + response.code() + "! " + response.body().contentLength() );
        return response.body().string();
    }

    @Override
    protected Data doInBackground( Data... params ) {
        try {
            Log.d( "TRANSFER/START", "Download start..." );
            Log.d( "TRANSFER/START/URL", url );
            Log.d( "TRANSFER/START/DATA", JSON.encode( allData ) );

            reply = JSON.decode( Post( "q=" + JSON.encode( allData ) ), Data.class );
        } catch ( IOException e ) {
            Log.e( "TRANSFER/ERROR/EXCEPT", "IOException Error" );
            e.printStackTrace();
        } catch ( JSONException e){
            Log.e( "TRANSFER/ERROR/EXCEPT", "JSONException Error" );
        }

        Log.d( "TRANSFER/FINISH", "TRANSFER FINISHED." );
        return null;
    }
}
