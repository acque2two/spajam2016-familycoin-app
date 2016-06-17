package rainbow_rider.kirin.spajam.Data;

import android.os.Environment;

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
        String filePath = Environment.getExternalStorageDirectory() + "/json.json";
        File file = new File(filePath);
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(JSON.encode(allData));
            bw.flush();
            bw.close();
            return true;
        } catch (Exception e) {
            return  false;
        }
    }

    public static Data Load(){
        String filePath = Environment.getExternalStorageDirectory() + "/json.json";
        File file = new File(filePath);
        FileInputStream fis;
        Data out;
        try {
            fis = new FileInputStream(file);
            InputStreamReader osw = new InputStreamReader(fis, "UTF-8");
            BufferedReader bw = new BufferedReader(osw);
            out = JSON.decode(bw,Data.class);

            return out;
        } catch (Exception e) {
            return null;
        }
    }
}
