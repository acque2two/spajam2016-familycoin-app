package rainbow_rider.kirin.spajam.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by acq on 16/06/06.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */
public class Data implements Serializable {
    private boolean status;
    private ArrayList<Family> family;
    private ArrayList<Genre> genre;

    public boolean isStatus( ) {
        return status;
    }

    public void setStatus( boolean status ) {
        this.status = status;
    }

    public ArrayList<Family> getFamily( ) {
        return family;
    }

    public void setFamily( ArrayList<Family> family ) {
        this.family = family;
    }

    public ArrayList<Genre> getGenre( ) {
        return genre;
    }

    public void setGenre( ArrayList<Genre> genre ) {
        this.genre = genre;
    }
}
