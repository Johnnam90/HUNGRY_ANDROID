package kr.ac.snust.hungry.hungry;
//아이템으로 보여줄 뷰 정의

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by John on 7/31/15.
 */
public class Main_menuView extends LinearLayout {

    /**
     * Main_menuItem의 변수와는 다름.
     * 레이아웃의 객체화를 위한 변수선언.
     */
    private ImageView mIcon;
    private TextView mText;

    //생성자
    public Main_menuView(Context context, Main_menuItem aItem) {
        super(context); //default constructor

        //Layout Inflation
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.main_menu, this, true); //XML 객체화

        //Set Icon
        mIcon = (ImageView) findViewById(R.id.main_menuListImg);
        mIcon.setImageDrawable(aItem.getIcon()); //어뎁테에서 가져온 이미지를 해당 아이디에 설정

        //Set Text
        mText = (TextView) findViewById(R.id.main_menuListTxt);
        mText.setText(aItem.getData());
    }

        //setter 선언

    public void setIcon(Drawable icon) {
        mIcon.setImageDrawable(icon);
    }

    public void setText(String data) {
        mText.setText(data);
    }

}
