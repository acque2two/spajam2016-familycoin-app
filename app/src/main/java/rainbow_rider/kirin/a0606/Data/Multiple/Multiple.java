package rainbow_rider.kirin.a0606.Data.Multiple;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by acq on 16/06/06.
 * Title:  複数を保持するリストを簡単に作成するやつ
 * Author: Kazuho Niidome
 * Memo:   使い方: Multiple<型> hoge = new Multiple<型>
 */

public class Multiple<T> extends ArrayList<T> implements List<T> {


    @Override
    public T get( int index ) {
        return super.get( index );
    }

    @Override
    public boolean addAll( Collection collection ) {
        return super.addAll( collection );
    }

    public boolean add( T object ) {
        super.add( object );
        return false;
    }

    @Override
    public void add( int index, T object ) {
        super.add( index, object );
    }

    public void set( T object ) {
        this.clear();
        super.add( object );
    }

    @Override
    public T remove( int index ) {
        return super.remove( index );
    }

    @Override
    public boolean remove( Object object ) {
        return super.remove( object );
    }
}
