package kr.ac.snust.hungry.hungry;

/**
 * Created by minseok on 2015-09-30.
 */
public class ListItem {

    private String[] mData;

    public ListItem(String[] data) {

        mData = data;
    }

    //user constructor
    public ListItem(String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {

        mData = new String[11];
        mData[0] = str1;
        mData[1] = str2;
        mData[2] = str3;
        mData[3] = str4;
        mData[4] = str5;
        mData[5] = str6;
        mData[6] = str7;
        mData[7] = str8;
        mData[8] = str9;
        mData[9] = str10;
        mData[10] = str11;
    }

    public String[] getData() { return mData;}
    public String getData(int index) { return mData[index];}
    public void setData(String[] data)  { mData = data;}
}

