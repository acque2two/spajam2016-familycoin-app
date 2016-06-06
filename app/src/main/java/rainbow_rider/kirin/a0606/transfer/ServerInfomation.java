package rainbow_rider.kirin.a0606.transfer;

/**
 * Created by acq on 16/05/31.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */
public class ServerInfomation {
    private final static String serverHost = null;
    private final static String serverHostName = "sgnz0606server.herokuapp.com";
    private final static Integer serverPort = 80;

    public static String getServerHost( ) { return serverHost; }

    public static String getServerHostName( ) {
        return serverHostName;
    }

    public static Integer getServerPort( ) {
        return serverPort;
    }
}
