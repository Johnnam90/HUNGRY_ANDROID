package kr.ac.snust.hungry.hungry;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import java.util.List;

/**
 * Created by John on 7/31/15.
 */
public class Main_menuAdapter extends BaseAdapter{

    private Context mContext;
    private List<Main_menuItem> mItems = new ArrayList<Main_menuItem>();

    public Main_menuAdapter (Context context) {mContext = context;}
    public void addItem(Main_menuItem itm) {mItems.add(itm);}
    public int getCount() {return mItems.size();}
    public Object getItem(int position) {
        return mItems.get(position);
    }

    public long getItemId(int position) {return position;}

    public View getView(int position, View convertView, ViewGroup parent) {
        Main_menuView main_menuView;
        if(convertView == null) {
            main_menuView = new Main_menuView(mContext, mItems.get(position));
        } else {
            main_menuView = (Main_menuView) convertView;

            main_menuView.setIcon(mItems.get(position).getIcon());
            main_menuView.setText(mItems.get(position).getData());
        }

        return main_menuView;
    }
}
