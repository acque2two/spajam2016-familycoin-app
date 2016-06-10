package rainbow_rider.kirin.spajam.Data;

import java.io.Serializable;

public class User  implements Serializable {
    private long user_id;
    private String user_name;
    private Long count;
    public Long getCount( ) {
        return count;
    }


    public long getUser_id( ) {
        return user_id;
    }

    public void setUser_id( long user_id ) {
        this.user_id = user_id;
    }

    public String getUser_name( ) {
        return user_name;
    }

    public void setUser_name( String user_name ) {
        this.user_name = user_name;
    }
}
