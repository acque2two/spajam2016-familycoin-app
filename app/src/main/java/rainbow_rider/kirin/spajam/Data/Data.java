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
    public boolean status;
    public ArrayList<Family> family = new ArrayList<>();
    public ArrayList<Genre> genre = new ArrayList<>();

    public Data(){
        Family family_single = new Family();
        family.add(family_single);
    }
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
