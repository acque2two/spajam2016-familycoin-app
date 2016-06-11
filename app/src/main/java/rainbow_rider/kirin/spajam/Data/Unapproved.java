package rainbow_rider.kirin.spajam.Data;

import java.io.Serializable;

/**
 * Created by acq on 16/06/11.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */
public class Unapproved extends Work implements Serializable {
    public int date;

    public int getDate( ) {
        return date;
    }

    public void setDate( int date ) {
        this.date = date;
    }
}
