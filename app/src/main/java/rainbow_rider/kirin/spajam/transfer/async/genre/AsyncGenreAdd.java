package rainbow_rider.kirin.spajam.transfer.async.genre;

import java.util.ArrayList;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Genre;
import rainbow_rider.kirin.spajam.transfer.async.Sender;

/**
 * Created by acq on 16/06/07.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */

public class AsyncGenreAdd extends Sender {
    public AsyncGenreAdd( Genre genre ) {
        ArrayList<Genre> genreList = new ArrayList<>();
        genreList.add( genre );
        allData = new Data();
        allData.setGenre( genreList );
        super.setPath( "/genreadd" );
    }
}
