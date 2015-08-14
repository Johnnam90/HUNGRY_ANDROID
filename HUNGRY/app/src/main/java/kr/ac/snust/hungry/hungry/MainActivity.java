package kr.ac.snust.hungry.hungry;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;



public class MainActivity extends Activity {
    //Main Activity
    ListView main_listView;
    Main_menuAdapter main_menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_listView = (ListView)findViewById(R.id.main_listView);
        main_menuAdapter = new Main_menuAdapter(this);

        Resources res = getResources();
        main_menuAdapter.addItem(new Main_menuItem(res.getDrawable(R.drawable.main_waiting), "번호표 관련"));
        main_menuAdapter.addItem(new Main_menuItem(res.getDrawable(R.drawable.main_recent), "최근 게시물"));
        main_menuAdapter.addItem(new Main_menuItem(res.getDrawable(R.drawable.main_popular), "인기 게시물"));
        main_menuAdapter.addItem(new Main_menuItem(res.getDrawable(R.drawable.main_recommend), "음식점 추천"));
        main_menuAdapter.addItem(new Main_menuItem(res.getDrawable(R.drawable.main_favorite), "관심 음식점"));
        main_menuAdapter.addItem(new Main_menuItem(res.getDrawable(R.drawable.main_mine), "내 활동내역"));

        main_listView.setAdapter(main_menuAdapter);

//        ImageRound imageRound = new ImageRound();
//        imageRound.getRoundedCornerBitmap();
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
