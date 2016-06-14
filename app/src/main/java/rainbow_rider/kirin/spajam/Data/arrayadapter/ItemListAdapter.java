package rainbow_rider.kirin.spajam.Data.arrayadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import rainbow_rider.kirin.spajam.Data.Work;
import rainbow_rider.kirin.spajam.R;

public class ItemListAdapter extends GenericArrayAdapter<Work> {

    ViewHolder mViewHolder;

    public ItemListAdapter( Context context, int resource ) {
        super( context, resource );
    }

    public View getView( int position, View convertView, ViewGroup parent ) {

        Work listItem = getItem( position );

        if ( convertView == null ) {

            convertView = super.getView( position, convertView, parent );

            mViewHolder = new ViewHolder();

            mViewHolder.icon = ( ImageView ) convertView.findViewById( R.id.item_list_view_icon );

            mViewHolder.illust = ( ImageView ) convertView.findViewById( R.id.item_list_view_illust );

            mViewHolder.title = ( TextView ) convertView.findViewById( R.id.item_list_view_title );

            mViewHolder.point = ( TextView ) convertView.findViewById( R.id.item_list_view_point );

            convertView.setTag( mViewHolder );

        } else {
            mViewHolder = ( ViewHolder ) convertView.getTag();
        }

        //mViewHolder.food_icon.setBackground( listitem.getFoodIcon() );

        mViewHolder.title.setText(listItem.getW_name());

        mViewHolder.point.setText(String.valueOf(listItem.getPoint()) + "ポイント");

        listItem.getImage();

        //mViewHolder.icon.setImageResource( listItem.getImage() );

        return convertView;
    }

    static class ViewHolder {
        ImageView icon;
        ImageView illust;
        TextView title;
        TextView content;
        TextView point;
    }
}

