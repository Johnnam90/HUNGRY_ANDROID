package kr.ac.snust.hungry.hungry;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by John on 8/27/15.
 */
public class Middle_listView extends LinearLayout {
    private ImageView mTmb;
    private TextView mId;
    private TextView mScore;
    private TextView mReg;
    private ImageView mType;
    private ImageView mTaste;
    private ImageView mLoc;
    private ImageView mTime;
    private TextView mContent;
    private ImageView mPictures;
    private TextView mComment;

    private ImageView mIcon;
    private TextView mText;

    //생성자
    //d
    public Middle_listView(Context context, Middle_listItem aItem) {
        super(context); //default constructor

        //Layout Inflation
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.middle_listview, this, true); //XML 객체화

        //Set TMB
        //레이아웃을 객체화
//        mTmb = (ImageView) findViewById(R.id.middle_thumbNail);
//        mTmb.setImageDrawable(aItem.getTmb());

        mId = (TextView) findViewById(R.id.middle_idBlank);
        mId.setText(aItem.getId());

        mScore = (TextView) findViewById(R.id.middle_score);
        mScore.setText(aItem.getScore());

        mReg = (TextView) findViewById(R.id.middle_regDate);
        mReg.setText(aItem.getReg());

        mType = (ImageView) findViewById(R.id.middle_type);
        mType.setImageDrawable(aItem.getType());

        mTaste = (ImageView) findViewById(R.id.middle_taste);
        mTaste.setImageDrawable(aItem.getTaste());

        mLoc = (ImageView) findViewById(R.id.middle_location);
        mLoc.setImageDrawable(aItem.getLoc());

        mTime = (ImageView) findViewById(R.id.middle_time);
        mTime.setImageDrawable(aItem.getTime());

        mContent = (TextView) findViewById(R.id.middle_content);
        mContent.setText(aItem.getContent());

        mPictures = (ImageView) findViewById(R.id.middle_image);
        mPictures.setImageDrawable(aItem.getPictures());

        mComment = (TextView) findViewById(R.id.middle_commentBtn);
        mComment.setText(aItem.getComment());

    }

    //setter 선언
    //레이아웃에 자료 설정
    public void setmTmb(Drawable tmb) { mTmb.setImageDrawable(tmb);}
    public void setmId(String id) { mId.setText(id);}
    public void setmScore(String score) { mScore.setText(score);}
    public void setmReg(String reg) { mReg.setText(reg);}
    public void setmType(Drawable type) { mType.setImageDrawable(type);}
    public void setmTaste(Drawable taste) { mTaste.setImageDrawable(taste);}
    public void setmLoc(Drawable loc) { mLoc.setImageDrawable(loc); }
    public void setmTime(Drawable time) { mTime.setImageDrawable(time); }
    public void setmContent(String content) { mContent.setText(content); }
    public void setmPictures(Drawable pictures) { mPictures.setImageDrawable(pictures);}
    public void setmComment(String comment) { mComment.setText(comment); }

}
