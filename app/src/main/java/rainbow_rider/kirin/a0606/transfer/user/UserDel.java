package rainbow_rider.kirin.a0606.transfer.user;

import rainbow_rider.kirin.a0606.Data.Data;
import rainbow_rider.kirin.a0606.Data.Multiple.Users;
import rainbow_rider.kirin.a0606.Data.User;
import rainbow_rider.kirin.a0606.transfer.Sender;

/**
 * Created by acq on 16/06/07.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */

public class UserDel extends Sender {
    public UserDel( User user ) {
        Users userList = new Users();
        userList.set( user );
        allData = new Data();
        allData.setUser( userList );
        super.setPath( "/userdel" );
    }
}
