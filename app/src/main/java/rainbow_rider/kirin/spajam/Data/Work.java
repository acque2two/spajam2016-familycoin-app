package rainbow_rider.kirin.spajam.Data;

/**
 * Created by acq on 16/06/11.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */
public class Work {
    private Integer w_id;
    private String w_text;
    private String u_id;
    private String f_id;
    private String w_name;
    private Integer g_id;
    private Integer point;
    private Genre genre;
    private Integer image;

    public Integer getImage( ) {
        return image;
    }

    public void setImage( Integer image ) {
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
