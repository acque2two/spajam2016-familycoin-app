package rainbow_rider.kirin.a0606.Data;

import java.io.Serializable;

public class Genre  implements Serializable {
    private long genre_id;
    private String genre_name;
    private Long count;

    public Long getCount( ) {
        return count;
    }


    public long getGenre_id( ) {
        return genre_id;
    }

    public void setGenre_id( long genre_id ) {
        this.genre_id = genre_id;
    }

    public String getGenre_name( ) {
        return genre_name;
    }

    public void setGenre_name( String genre_name ) {
        this.genre_name = genre_name;
    }
}
