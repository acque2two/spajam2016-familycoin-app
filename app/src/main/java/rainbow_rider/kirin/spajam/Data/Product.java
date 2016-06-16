package rainbow_rider.kirin.spajam.Data;

import java.io.Serializable;

/**
 * Created by acq on 16/06/11.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */
public class Product implements Serializable {
    public Integer p_id ;
    public String p_name;
    public String f_id;
    public Integer p_point;

    public Integer getP_point( ) {
        return p_point;
    }

    public void setP_point( Integer p_point ) {
        this.p_point = p_point;
    }

    public String getF_id( ) {
        return f_id;
    }

    public void setF_id( String f_id ) {
        this.f_id = f_id;
    }

    public String getP_name( ) {
        return p_name;
    }

    public void setP_name( String p_name ) {
        this.p_name = p_name;
    }

    public Integer getP_id( ) {
        return p_id;
    }

    public void setP_id( Integer p_id ) {
        this.p_id = p_id;
    }
}
