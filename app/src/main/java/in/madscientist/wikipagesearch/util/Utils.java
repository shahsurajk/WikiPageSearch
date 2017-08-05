package in.madscientist.wikipagesearch.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import in.madscientist.wikipagesearch.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by frapp on 5/8/17.
 */

public class Utils {

    public static boolean isOnline(Context context) {
        boolean result = false;
        try {
            if (context != null) {
                ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                result = netInfo != null && netInfo.isConnectedOrConnecting();
                if (!result) {
                    showToastMessage((Activity) context, context.getString(R.string.ERR_INTERNET_DISCONNECTED));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }
    public static void setDefaultFontForActivity(Context context){
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(context.getString(R.string.font_regular))
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
    public static String generateURLForPageID(String pageID){
        return String.format("https://en.m.wikipedia.org/?curid=%s",pageID);
    }
    public static void showToastMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
