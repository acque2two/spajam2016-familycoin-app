package rainbow_rider.kirin.spajam.Data;

import java.io.Serializable;

public class Genre implements Serializable {
    private long g_id;
    private String g_name;

    public long getG_id( ) {
        return g_id;
    }

    public void setG_id( long g_id ) {
        this.g_id = g_id;
    }

    public String getG_name( ) {
        return g_name;
    }

    public void setG_name( String g_name ) {
        this.g_name = g_name;
    }

}
