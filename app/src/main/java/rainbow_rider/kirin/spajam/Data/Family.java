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
    public String f_id;
    public String f_name;

    public ArrayList<User> users  = new ArrayList<>();
    public ArrayList<Work> work = new ArrayList<>();
    public ArrayList<Product> product = new ArrayList<>();

    public ArrayList<User> getUsers( ) {
        return users;
    }

    public void setUsers( ArrayList<User> users ) {
        this.users = users;
    }

    public ArrayList<Product> getProduct( ) {
        return product;
    }

    public void setProduct( ArrayList<Product> product ) {
        this.product = product;
    }

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


    public ArrayList<Work> getWork( ) {
        return work;
    }

    public void setWork( ArrayList<Work> work ) {
        this.work = work;
    }

}
