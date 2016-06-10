package rainbow_rider.kirin.spajam.transfer.sync.genre;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Genre;
import rainbow_rider.kirin.spajam.Data.Multiple.Genres;
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
        Genres genreList = new Genres();
        genreList.set( genre );
        allData = new Data();
        allData.setGenre( genreList );
        super.setPath( "/genredel" );
    }
}
