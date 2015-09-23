package kr.ac.snust.hungry.hungry;

import android.graphics.drawable.Drawable;

/**
 * Created by John on 8/27/15.
 */
public class Middle_listItem {
    private Drawable mTmb;
    private String mId;
    private String mScore;
    private String mReg;
    private Drawable mType;
    private Drawable mTaste;
    private Drawable mLoc;
    private Drawable mTime;
    private String mContent;
    private Drawable mPictures;
    private String mComment;

    private Drawable mIcon;
    private String mData;

    //순서는 인자순
    public Middle_listItem (Drawable tmb, String id, String score, String reg, Drawable type, Drawable taste,
                            Drawable loc, Drawable time, String content, Drawable pictures, String comment) {
        mTmb = tmb;
        mId = id;
        mScore = score;
        mReg = reg;
        mType = type;
        mTaste = taste;
        mLoc = loc;
        mTime = time;
        mContent = content;
        mPictures = pictures;
        mComment = comment;
    }

    public Drawable getTmb() { return mTmb; }
    public String getId() { return mId; }
    public String getScore() { return mScore; }
    public String getReg() { return mReg; }
    public Drawable getType() { return mType; }
    public Drawable getTaste() { return mTaste; }
    public Drawable getLoc() { return mLoc; }
    public Drawable getTime() { return mTime; }
    public String  getContent() { return mContent; }
    public Drawable getPictures() { return mPictures; }
    public String getComment() { return mComment; }
}
