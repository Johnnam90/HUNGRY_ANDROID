package kr.ac.snust.hungry.hungry;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 8/27/15.
 */
public class Middle_listAdapter extends BaseAdapter {
    private Context mContext;
    private List<Middle_listItem> mItems = new ArrayList<Middle_listItem>();
    //listItem.java는 자료형이라 생각


    public Middle_listAdapter (Context context) {mContext = context;}
    public void addItem(Middle_listItem itm) {mItems.add(itm);}
    public int getCount() {return mItems.size();}
    public Object getItem(int position) {
        return mItems.get(position);
    }

    public long getItemId(int position) {return position;}

    public View getView(int position, View convertView, ViewGroup parent) {
        Middle_listView middle_listView;
        if(convertView == null) {
            middle_listView = new Middle_listView(mContext, mItems.get(position));
        } else {
            middle_listView = (Middle_listView) convertView;

            //setIcon(View)에 getIcon(Item)을 넘기며 자료를 설정 Item -> Adapter -> View
            //특정 순서의(mItems.get(position) 자료를 인자로 넘긴다

//            middle_listView.setmTmb(mItems.get(position).getTmb());
            middle_listView.setmId(mItems.get(position).getId());
            middle_listView.setmScore(mItems.get(position).getScore());
            middle_listView.setmReg(mItems.get(position).getReg());
            middle_listView.setmType(mItems.get(position).getType());
            middle_listView.setmTaste(mItems.get(position).getTaste());
            middle_listView.setmLoc(mItems.get(position).getLoc());
            middle_listView.setmTime(mItems.get(position).getTime());
            middle_listView.setmContent((mItems.get(position).getContent()));
//            middle_listView.setmPictures(mItems.get(position).getPictures());
            middle_listView.setmUrl(mItems.get(position).getUrl());
            middle_listView.setmComment(mItems.get(position).getComment());
            middle_listView.setmSeq(mItems.get(position).getSeq());
        }

        return middle_listView;
    }


}


