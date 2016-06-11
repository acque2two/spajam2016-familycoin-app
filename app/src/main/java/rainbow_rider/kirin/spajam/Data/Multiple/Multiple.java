package rainbow_rider.kirin.spajam.Data.Multiple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acq on 16/06/06.
 * Title:  複数を保持するリストを簡単に作成するやつ
 * Author: Kazuho Niidome
 * Memo:   使い方: Multiple<型> hoge = new Multiple<型>
 */

public class Multiple<T> extends ArrayList<T> implements List<T> {

    public boolean add( T object ) {
        super.add( object );
        return false;
    }

    public void set( T object ) {
        this.clear();
        super.add( object );
    }

}
