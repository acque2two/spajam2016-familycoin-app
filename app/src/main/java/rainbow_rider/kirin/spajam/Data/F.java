package rainbow_rider.kirin.spajam.Data;

import android.os.Environment;
import android.util.Log;

import net.arnx.jsonic.JSON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by nozomi on 16/06/17.
 */
public class F {


    public F(){


    }

    public static boolean Save(Data allData){
        Log.d("F/Save/Start", "Save start...");
        String filePath = Environment.getExternalStorageDirectory() + "/json.json";
        Log.v("F/Save/Path", filePath);
        File file = new File(filePath);
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(JSON.encode(allData));
            bw.flush();
            bw.close();
            Log.d("F/Save/Result", "True - Success");

            return true;
        } catch (Exception e) {
            Log.e("F/Save/Result", "False - Failed!");
            return  false;
        }
    }

    public static Data Load(){
        Log.d("F/Load/Start", "Load start...");
        String filePath = Environment.getExternalStorageDirectory() + "/json.json";
        File file = new File(filePath);

        Log.v("F/Load/Path", filePath);
        FileInputStream fis;
        Data out;
        try {
            fis = new FileInputStream(file);
            InputStreamReader osw = new InputStreamReader(fis, "UTF-8");
            BufferedReader bw = new BufferedReader(osw);
            out = JSON.decode(bw,Data.class);

            Log.d("F/Load/Result", bw.readLine());
            return out;
        } catch (Exception e) {
            return null;
        }
    }
}