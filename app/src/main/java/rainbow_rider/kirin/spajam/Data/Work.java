package rainbow_rider.kirin.spajam.Data;

import java.io.Serializable;

/**
 * Created by acq on 16/06/11.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */
public class Work implements Serializable {
    public Integer w_id;
    public String w_text;
    public String u_id;
    public String f_id;
    public String w_name;
    public Integer g_id;
    public Integer point;
    public Genre genre;
    public String image;

    public String getImage( ) {
        return image;
    }

    public void setImage( String image ) {
        this.image = image;
    }

    public Integer getW_id( ) {
        return w_id;
    }

    public void setW_id( Integer w_id ) {
        this.w_id = w_id;
    }

    public String getW_text( ) {
        return w_text;
    }

    public void setW_text( String w_text ) {
        this.w_text = w_text;
    }

    public String getU_id( ) {
        return u_id;
    }

    public void setU_id( String u_id ) {
        this.u_id = u_id;
    }

    public String getF_id( ) {
        return f_id;
    }

    public void setF_id( String f_id ) {
        this.f_id = f_id;
    }

    public String getW_name( ) {
        return w_name;
    }

    public void setW_name( String w_name ) {
        this.w_name = w_name;
    }

    public Integer getG_id( ) {
        return g_id;
    }

    public void setG_id( Integer g_id ) {
        this.g_id = g_id;
    }

    public Integer getPoint( ) {
        return point;
    }

    public void setPoint( Integer point ) {
        this.point = point;
    }

    public Genre getGenre( ) {
        return genre;
    }

    public void setGenre( Genre genre ) {
        this.genre = genre;
    }
}
