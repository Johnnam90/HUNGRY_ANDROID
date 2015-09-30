package kr.ac.snust.hungry.hungry;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gomi on 15. 9. 23..
 */
public class reply_listAdapter extends BaseAdapter {

    private Context mContext;

    private List<reply_listItem> mItems = new ArrayList<reply_listItem>();

    public reply_listAdapter(Context context) {
        mContext = context;
    }

    public void addItem(reply_listItem it) {
        mItems.add(it);
    }

    public void setListItems(List<reply_listItem> lit) {
        mItems = lit;
    }

    public int getCount() {
        return mItems.size();
    }

    public Object getItem(int position) {
        return mItems.get(position);
    }

    public boolean areAllItemsSelectable() {
        return false;
    }

    public boolean isSelectable(int position) {
        try {
            return mItems.get(position).isSelectable();
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        reply_listView itemView;
        if (convertView == null) {
            itemView = new reply_listView(mContext, mItems.get(position));
        } else {
            itemView = (reply_listView) convertView;

            itemView.setText(0, mItems.get(position).getData(0));
            itemView.setText(1, mItems.get(position).getData(1));
            itemView.setText(2, mItems.get(position).getData(2));
        }

        return itemView;
    }

}
