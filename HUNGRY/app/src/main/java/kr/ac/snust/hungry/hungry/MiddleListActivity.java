package kr.ac.snust.hungry.hungry;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by John on 8/14/15.
 */
public class MiddleListActivity extends Activity {

    ListView middleListView;
    Middle_listAdapter middle_listAdapter;
    BitmapFactory bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.middle_list_layout);

        //메인메뉴
        middleListView = (ListView)findViewById(R.id.middleListView);
        middle_listAdapter = new Middle_listAdapter(this);

        String[] url = new String[5];

        /*
        *임시 URL 부분*/
        url[0] = "http://54.64.160.105:8080/img/2.jpg";
        url[1] = "http://54.64.160.105:8080/img/3.png";
        url[2] = "http://54.64.160.105:8080/img/P20150110_140203242_F660F5AC-04FD-413C-B623-78F8EA3B1DD0.jpg";
        url[3] = "http://54.64.160.105:8080/img/IMG_5583.jpg";


        /*
        * 임시 데이터 부분
        * addItem()으로 추가*/
        Resources res = getResources();
//        middle_listAdapter.addItem(new Middle_listItem(res.getDrawable(R.drawable.profile), "john",
//                "4.5", "2015.11.11", res.getDrawable(R.drawable.nationality_korean), res.getDrawable(R.drawable.taste_swe),
//                res.getDrawable(R.drawable.location_sl), res.getDrawable(R.drawable.time_lc),
//                "Content is here.", res.getDrawable(R.drawable.samplefood), "5"));
//
//        middle_listAdapter.addItem(new Middle_listItem(res.getDrawable(R.drawable.profile), "john",
//                "4.5", "2015.11.14", res.getDrawable(R.drawable.nationality_chines), res.getDrawable(R.drawable.taste_hot),
//                res.getDrawable(R.drawable.location_kk), res.getDrawable(R.drawable.time_dn),
//                "리스트뷰가 완성되었다!!! 효과는 대단했다!!!!!!.", res.getDrawable(R.drawable.samplefood), "3"));

        middle_listAdapter.addItem(new Middle_listItem(res.getDrawable(R.drawable.profile), "john",
                "4.5", "2015.11.14", res.getDrawable(R.drawable.nationality_chines), res.getDrawable(R.drawable.taste_hot),
                res.getDrawable(R.drawable.location_kk), res.getDrawable(R.drawable.time_dn),
                "우와와와와와와 한국~~~~", url[0], "3"));

        middle_listAdapter.addItem(new Middle_listItem(res.getDrawable(R.drawable.profile), "john",
                "4.5", "2015.11.14", res.getDrawable(R.drawable.nationality_chines), res.getDrawable(R.drawable.taste_hot),
                res.getDrawable(R.drawable.location_kk), res.getDrawable(R.drawable.time_dn),
                "우와와와와와와 대한민국~~~~", url[1], "3"));

        middle_listAdapter.addItem(new Middle_listItem(res.getDrawable(R.drawable.profile), "john",
                "4.5", "2015.11.14", res.getDrawable(R.drawable.nationality_chines), res.getDrawable(R.drawable.taste_hot),
                res.getDrawable(R.drawable.location_kk), res.getDrawable(R.drawable.time_dn),
                "우와와와와와와 중국~~~~~~", url[2], "5"));

        middle_listAdapter.addItem(new Middle_listItem(res.getDrawable(R.drawable.profile), "john",
                "4.5", "2015.11.14", res.getDrawable(R.drawable.nationality_chines), res.getDrawable(R.drawable.taste_hot),
                res.getDrawable(R.drawable.location_kk), res.getDrawable(R.drawable.time_dn),
                "우와와와와와와 일본~~~~", url[3], "3"));


        middleListView.setAdapter(middle_listAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
