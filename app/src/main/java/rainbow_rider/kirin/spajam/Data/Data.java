package rainbow_rider.kirin.spajam.Data;

import java.io.Serializable;

import rainbow_rider.kirin.spajam.Data.Multiple.Anss;
import rainbow_rider.kirin.spajam.Data.Multiple.Favs;
import rainbow_rider.kirin.spajam.Data.Multiple.Genres;
import rainbow_rider.kirin.spajam.Data.Multiple.Questions;
import rainbow_rider.kirin.spajam.Data.Multiple.UserAnss;
import rainbow_rider.kirin.spajam.Data.Multiple.Users;

/**
 * Created by acq on 16/06/06.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */
public class Data implements Serializable {
    private Anss ans;
    private Users user;
    private Questions question;
    private Genres genre;
    private UserAnss userans;
    private Favs fav;
    private Boolean status;

    public Boolean isStatus( ) {
        return status;
    }

    public Anss getAns( ) {
        return ans;
    }

    public void setAns( Anss ans ) {
        this.ans = ans;
    }

    public Users getUser( ) {
        return user;
    }

    public void setUser( Users user ) {
        this.user = user;
    }

    public Questions getQuestion( ) {
        return question;
    }

    public void setQuestion( Questions question ) {
        this.question = question;
    }

    public Genres getGenre( ) {
        return genre;
    }

    public void setGenre( Genres genre ) {
        this.genre = genre;
    }

    public UserAnss getUserans( ) {
        return userans;
    }

    public void setUserans( UserAnss userans ) {
        this.userans = userans;
    }

    public Favs getFav( ) {
        return fav;
    }

    public void setFav( Favs fav ) {
        this.fav = fav;
    }
}
