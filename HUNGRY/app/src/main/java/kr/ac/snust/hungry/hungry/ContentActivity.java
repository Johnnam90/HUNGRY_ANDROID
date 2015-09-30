package kr.ac.snust.hungry.hungry;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

/**
 * Created by gomi on 15. 9. 30..
 */
public class ContentActivity extends Activity {

    ListView replyListView;
    reply_listAdapter replyAdapter;

    ImageView id_thumbnail;
    ImageView img1;
    ImageView img2;
    ImageView img3;

    TextView idArea;
    TextView replyNum;
    TextView scoreArea;
    TextView contentArea;

    ScrollView scrollView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_layout);

        Intent intent = getIntent();

        // 리스트뷰 객체 참조
        replyListView = (ListView) findViewById(R.id.reply_listView);

        //썸네일 설쩡쩡
        id_thumbnail=(ImageView) findViewById(R.id.id_thumbnail);
        Glide.with(this).load("http://54.64.160.105:8080/img/thumb/KWB.jpg").into(id_thumbnail);

        //아이디 설쩡
        idArea=(TextView) findViewById(R.id.id_area);
        idArea.setText("" + intent.getStringExtra("id"));

        //점수 설쩡
        scoreArea=(TextView) findViewById(R.id.scoreTxt);
        scoreArea.setText(""+intent.getStringExtra("score"));

        //내용 설정
        contentArea=(TextView) findViewById(R.id.contentTxt);
        contentArea.setText(""+intent.getStringExtra("content"));

        //내용 이미지 설쩡
        img1=(ImageView) findViewById(R.id.img1);
        img2=(ImageView) findViewById(R.id.img2);
        img3=(ImageView) findViewById(R.id.img3);

        replyNum=(TextView) findViewById(R.id.reply_num);

        String imgURL[] = {"http://54.64.160.105:8080/img/2.jpg","http://54.64.160.105:8080/img/1.jpg"};

        if(imgURL.length==1){
            Glide.with(this).load(imgURL[0]).into(img1);
        }else if(imgURL.length==2){
            Glide.with(this).load(imgURL[0]).into(img1);

            img2.setVisibility(View.VISIBLE);
            Glide.with(this).load(imgURL[1]).into(img2);
        }else if(imgURL.length==3){
            Glide.with(this).load(imgURL[0]).into(img1);

            img2.setVisibility(View.VISIBLE);
            Glide.with(this).load(imgURL[1]).into(img2);

            img3.setVisibility(View.VISIBLE);
            Glide.with(this).load(imgURL[2]).into(img3);
        }

        // 어댑터 객체 생성
        replyAdapter = new reply_listAdapter(this);

        // 아이템 데이터 만들기
        Resources res = getResources();
        replyAdapter.addItem(new reply_listItem("라곰", "2015-06-09", "900 원"));
        replyAdapter.addItem(new reply_listItem("혀누", "2015-06-09", "1500 원"));
        replyAdapter.addItem(new reply_listItem("민서크", "2015-06-09", "900 원"));
        replyAdapter.addItem(new reply_listItem("아이유", "2015-06-09", "900 원"));
        replyAdapter.addItem(new reply_listItem("졘졘", "2015-06-09", "1500 원"));
        replyAdapter.addItem(new reply_listItem("쬲", "2015-06-09", "1500 원"));
        replyAdapter.addItem(new reply_listItem("KRK", "2015-06-09", "1500 원"));
        replyAdapter.addItem(new reply_listItem("KMS", "2015-06-09", "1500 원"));
        replyAdapter.addItem(new reply_listItem("NHW", "2015-06-09", "1500 원"));
        replyAdapter.addItem(new reply_listItem("우왛", "2015-06-09", "1500 원"));

        int cnt=replyAdapter.getCount();
        replyNum.setText(""+cnt);
        replyListView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, cnt * 160));

        // 리스트뷰에 어댑터 설정
        replyListView.setAdapter(replyAdapter);

        //스크롤 화면 맨 위로 강제이동
        scrollView=(ScrollView) findViewById(R.id.scrollView);
        scrollView.fullScroll(View.FOCUS_UP);

        // 새로 정의한 리스너로 객체를 만들어 설정
        replyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                reply_listItem curItem = (reply_listItem) replyAdapter.getItem(position);
                String[] curData = curItem.getData();

                Toast.makeText(getApplicationContext(), "Selected : " + curData[0], Toast.LENGTH_LONG).show();

            }

        });
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
