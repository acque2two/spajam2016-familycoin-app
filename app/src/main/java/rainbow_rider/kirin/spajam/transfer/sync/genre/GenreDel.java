package rainbow_rider.kirin.spajam.transfer.sync.genre;

import java.util.ArrayList;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Genre;
import rainbow_rider.kirin.spajam.transfer.sync.Sender;

/**
 * Created by acq on 16/06/07.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */

public class GenreDel extends Sender {
    public GenreDel( Genre genre ) {
        ArrayList<Genre> genreList = new ArrayList<>();
        genreList.add( genre );
        allData = new Data();
        allData.setGenre( genreList );
        super.setPath( "/genredel" );
    }
}
