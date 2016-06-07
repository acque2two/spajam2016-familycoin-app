package rainbow_rider.kirin.a0606.transfer;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import rainbow_rider.kirin.a0606.Data.Data;

/**
 * Created by acq on 16/06/08.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */
ublic class ImagePut extends AsyncTask<String, byte[], byte[]> {
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
    public OkHttpClient client = new OkHttpClient();
    public String url;
    public Bitmap bitmap;
    public ImagePut( Bitmap bmp ) {
        Bitmap = bmp;
    }

}
