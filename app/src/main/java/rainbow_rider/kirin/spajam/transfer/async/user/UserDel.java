package rainbow_rider.kirin.spajam.transfer.async.user;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Multiple.Users;
import rainbow_rider.kirin.spajam.Data.User;
import rainbow_rider.kirin.spajam.transfer.async.Sender;

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
