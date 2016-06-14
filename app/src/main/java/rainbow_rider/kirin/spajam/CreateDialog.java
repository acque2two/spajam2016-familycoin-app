package rainbow_rider.kirin.spajam;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by nozomi on 16/06/13.
 */
public class CreateDialog extends AlertDialog.Builder{
    public CreateDialog(Context context){
        super(context);
    }

    public AlertDialog.Builder alertTitle(String title){
        super.setTitle(title);
        return this;
    }

    public AlertDialog.Builder alertMessage(String title, String message){
        alertTitle(title);
        super.setMessage(message);
        return this;
    }

    public AlertDialog.Builder alertButton(String title, String massage, String positiveButton){
        alertMessage(title, massage);
        super.setPositiveButton(positiveButton, null);
        return this;
    }

    public AlertDialog.Builder alertButton(String title, String massage,String positiveButton, String negativaButton) {
        alertButton(title, massage, negativaButton);
        super.setNegativeButton(positiveButton, null);
        return this;
    }

    public AlertDialog.Builder alertButton(String title, String massage, String positiveButton, String negativaButton, String neutralButton) {
        alertButton(title, massage, positiveButton, negativaButton);
        super.setNeutralButton(neutralButton, null);
        return this;
    }

    public AlertDialog.Builder items(String title, String[] items){
        alertTitle(title);
        super.setItems(items, null);
        return this;
    }
}
