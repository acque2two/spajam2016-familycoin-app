package rainbow_rider.kirin.spajam.Data;

import java.io.Serializable;

public class User implements Serializable {
    private long u_id;
    private String u_name;
    private Long count;
    private String f_id;
    private Integer score;
    private Boolean admin;
    private Boolean adult;
    private Boolean sex;

    public long getU_id( ) {
        return u_id;
    }

    public void setU_id( long u_id ) {
        this.u_id = u_id;
    }

    public String getU_name( ) {
        return u_name;
    }

    public void setU_name( String u_name ) {
        this.u_name = u_name;
    }

    public Long getCount( ) {
        return count;
    }

    public void setCount( Long count ) {
        this.count = count;
    }

    public String getF_id( ) {
        return f_id;
    }

    public void setF_id( String f_id ) {
        this.f_id = f_id;
    }

    public Integer getScore( ) {
        return score;
    }

    public void setScore( Integer score ) {
        this.score = score;
    }

    public Boolean getAdmin( ) {
        return admin;
    }

    public void setAdmin( Boolean admin ) {
        this.admin = admin;
    }

    public Boolean getAdult( ) {
        return adult;
    }

    public void setAdult( Boolean adult ) {
        this.adult = adult;
    }

    public Boolean getSex( ) {
        return sex;
    }

    public void setSex( Boolean sex ) {
        this.sex = sex;
    }
}


