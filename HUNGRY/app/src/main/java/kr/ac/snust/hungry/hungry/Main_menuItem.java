package kr.ac.snust.hungry.hungry;
//데이터 정의

import android.graphics.drawable.Drawable;

/**
 * Created by John on 7/31/15.
 */
public class Main_menuItem {
    private Drawable mIcon;
    private String mData;

    public Main_menuItem(Drawable icon, String name) {
        mIcon = icon;
        mData = name;
    }

    public String getData() {
        return mData;
    }

    public Drawable getIcon() {
        return mIcon;
    }

}
