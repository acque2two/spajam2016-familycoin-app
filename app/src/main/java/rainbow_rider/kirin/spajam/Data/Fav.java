package rainbow_rider.kirin.spajam.Data;

/**
 * Created by acq on 16/06/02.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */

public class Fav {
    private Genre genre;
    private User user;
    private Long count;
    public Long getCount( ) {
        return count;
    }


    public Genre getGenre( ) {
        return genre;
    }

    public void setGenre( Genre genre ) {
        this.genre = genre;
    }

    public User getUser( ) {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }
}
