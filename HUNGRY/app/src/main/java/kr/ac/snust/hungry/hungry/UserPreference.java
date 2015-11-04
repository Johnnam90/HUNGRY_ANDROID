package kr.ac.snust.hungry.hungry;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by John on 11/4/15.
 */
public class UserPreference {
    static final String PREF_USER_ID= "userid";
    static final String PREF_USER_PW = "userpw";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserId(Context ctx, String userId) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_ID, userId);
        editor.commit();
    }

    public static void setUserPw(Context ctx, String userPw) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_PW, userPw);
        editor.commit();
    }

    public static String getUserId(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_ID, "");
    }

    public static String getUserPw(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_PW, "");
    }

}

