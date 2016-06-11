package rainbow_rider.kirin.spajam.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by acq on 16/06/11.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */
public class Family implements Serializable {
    private String f_id;
    private String f_name;

    private ArrayList<User> users;
    private ArrayList<Work> work;
    private ArrayList<Product> product;

    public String getF_id( ) {
        return f_id;
    }

    public void setF_id( String f_id ) {
        this.f_id = f_id;
    }

    public String getF_name( ) {
        return f_name;
    }

    public void setF_name( String f_name ) {
        this.f_name = f_name;
    }

    public ArrayList<User> getUser( ) {
        return users;
    }

    public void setUser( ArrayList<User> user ) {
        this.users = user;
    }

    public ArrayList<Work> getWork( ) {
        return work;
    }

    public void setWork( ArrayList<Work> work ) {
        this.work = work;
    }

}
