package rainbow_rider.kirin.spajam.Data;

/**
 * Created by acq on 16/06/02.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */

public class UserAns {
    private User user;
    private Genre genre;
    private long ans_id;
    private Long count;

    public Long getCount( ) {
        return count;
    }

    public User getUser( ) {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

    public Genre getGenre( ) {
        return genre;
    }

    public void setGenre( Genre genre ) {
        this.genre = genre;
    }

    public long getAns_id( ) {
        return ans_id;
    }

    public void setAns_id( long ans_id ) {
        this.ans_id = ans_id;
    }
}
