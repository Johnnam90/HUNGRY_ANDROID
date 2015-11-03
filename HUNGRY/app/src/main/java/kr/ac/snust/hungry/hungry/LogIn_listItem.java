package kr.ac.snust.hungry.hungry;

/**
 * Created by John on 11/3/15.
 */
public class LogIn_listItem {

    private String[] mData;

    public LogIn_listItem(String[] data) {

        mData = data;
    }

    //user constructor
    public LogIn_listItem(String str1, String str2, String str3, String str4, String str5) {

        mData = new String[5];
        mData[0] = str1;
        mData[1] = str2;
        mData[2] = str3;
        mData[3] = str4;
        mData[4] = str5;
    }

    public String[] getData() { return mData;}
    public String getData(int index) { return mData[index];}
    public void setData(String[] data)  { mData = data;}
}

