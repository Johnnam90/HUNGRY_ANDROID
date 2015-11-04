package kr.ac.snust.hungry.hungry;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.util.Log;

/**
 * Created by John on 8/14/15.
 */
public class MiddleListActivity extends Activity {

    ListView middleListView;
    Middle_listAdapter middle_listAdapter;
    TextView middle_menu_id;
    String selectedMenu;
    String obj;
    String userId;

    getJsonByPhp task;

    String[] url = new String[5];

    //drawable 초기화 부분
    Drawable typeImg = new Drawable() {
        @Override
        public void draw(Canvas canvas) {

        }

        @Override
        public void setAlpha(int i) {

        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {

        }

        @Override
        public int getOpacity() {
            return 0;
        }
    };
    Drawable tasteImg = new Drawable() {
        @Override
        public void draw(Canvas canvas) {

        }

        @Override
        public void setAlpha(int i) {

        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {

        }

        @Override
        public int getOpacity() {
            return 0;
        }
    };
    Drawable locImg = new Drawable() {
        @Override
        public void draw(Canvas canvas) {

        }

        @Override
        public void setAlpha(int i) {

        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {

        }

        @Override
        public int getOpacity() {
            return 0;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.middle_list_layout);

        //데이터 받아오는 역할을 하는 AsyncTask 상속 받은 클래스
        task = new getJsonByPhp();

        //데이터 받아오는 쓰레드 시작
        task.execute("http://54.64.160.105/function_queries.php");

        //인텐트로 전달받은 값 메뉴 이름으로 설정
        middle_menu_id = (TextView) findViewById(R.id.middle_menu_id);
        Intent recievedIntent = getIntent();

        selectedMenu = recievedIntent.getStringExtra("menu");
        userId = recievedIntent.getStringExtra("id");

        //post를 위한 요청 obj 변수
        if(selectedMenu.equals("최근 게시물")){obj="recent";}
        else if(selectedMenu.equals("인기 게시물")){obj="popular";}
        else if(selectedMenu.equals("관심 음식점")){obj="favor";}
        else if(selectedMenu.equals("내 활동내역")){obj="history";}
        else if(selectedMenu.equals("음식점 추천")){obj="recommend";}
        else{}

        middle_menu_id.setText(selectedMenu);

        //메인메뉴
        middleListView = (ListView) findViewById(R.id.middleListView);
        middle_listAdapter = new Middle_listAdapter(this);

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

    private class getJsonByPhp extends AsyncTask<String, Integer, String> {

        String logtag = "logcat test";

        @Override
        protected String doInBackground(String... urls) {
            StringBuilder jsonHtml = new StringBuilder();

            try{
                //url 변수 선언
                URL url = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                //타임아웃
                if(conn != null)    {
                    conn.setConnectTimeout(10000);


                    /* post 하는 부분 */
                    //전달 헤더 설정
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setRequestMethod("POST");

                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setUseCaches(false);
                    conn.setDefaultUseCaches(false);

                    conn.connect();

                    String string2post = "obj="+obj+"&"+"id="+userId;
                    byte[] bytes2post = string2post.getBytes();

                    //����Ǿ���. �ڵ尡 ���ϵǸ�,
                    //
                    //먼저 보내기. outputStream을 이용함.
                    OutputStream outputstream = conn.getOutputStream();
                    outputstream.write(bytes2post);
                    outputstream.flush();
                    outputstream.close();

                    //���� bytes �ε� stream �ް�
                    if(HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                        InputStream inputstream_0 = conn.getInputStream();
                        //InputStreamReader   bytes stream �� character stream ���� �ٲٰ�.
                        InputStreamReader inputstreamreader_0 = new InputStreamReader(inputstream_0, "UTF-8");
                        //inputstreamreader   reader�� wrap�ϰ� buffered ������༭ �̿��ϱ� ���� ��. (�ð����̵�)
                        BufferedReader buffered = new BufferedReader(inputstreamreader_0);

                        //buffered data�� ó����
                        for (; ; ) {
                            //�ؽ�Ʈ ������ �о� ����
                            String line = buffered.readLine();

                            //Toast.makeText(getApplicationContext(), ""+line, Toast.LENGTH_SHORT).show();
                            if (line == null) break;
                            // ����� �ؽ�Ʈ ������ jsonHtml stringbuilder�� ����.
                            jsonHtml.append(line + "\n");
                        }
                        //bufferedReader �ݰ�.
                        buffered.close();
                        inputstreamreader_0.close();
                        inputstream_0.close();

                        //HttpURLConnection �� �ݰ�.
                        conn.disconnect();
                    }
                }
            }
            catch(Exception ex){
                ex.printStackTrace();
            }

            return jsonHtml.toString();
        }

        protected void onPostExecute(String str) {
            String seq;
            String content;
            String writer;
            String regdate;
            String thumb;
            String avg;
            String img;
            String type;
            String taste;
            String location;
            String comment_num;

            /*
        * 임시 데이터 부분
        * addItem()으로 추가*/
            Resources res = getResources();

            //임시 이미지 URL
//            url[0] = "http://54.64.160.105:8080/img/2.jpg";
//            url[1] = "http://54.64.160.105:8080/img/3.png";
//            url[2] = "http://54.64.160.105:8080/img/P20150110_140203242_F660F5AC-04FD-413C-B623-78F8EA3B1DD0.jpg";
//            url[3] = "http://54.64.160.105:8080/img/IMG_5583.jpg";
//            url[4] = "http://54.64.160.105:8080/img/Screen%20Shot%202015-05-26%20at%201.12.10%20PM.png";
//


            ArrayList<ListItem> lastDatas = new ArrayList<ListItem>();
            StringBuilder tempLastString = new StringBuilder();

            //txtView.setText(str);
            //string�� �ִ� �����͸� json ���� �ް� ó���� �� �ְ�.

            try {
                //JSONObject �����ϰ� �ȿ� result ��̸� ����.
                JSONObject jsonObject = new JSONObject(str);
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++) {
                    //JSONObject �� ���� ���� �ִ� JSONArray���� i��° JSONObject ��.
                    JSONObject nodeData = jsonArray.getJSONObject(i);

                    //�� ��忡�� �ش� Ű�� ���� �ް�.
                    seq = nodeData.getString("seq");
                    content = nodeData.getString("content");
                    writer = nodeData.getString("writer");
                    regdate = nodeData.getString("regdate");
                    thumb = nodeData.getString("thumb");
                    avg = nodeData.getString("avg");
                    img = nodeData.getString("img");
                    type = nodeData.getString("type");
                    taste = nodeData.getString("taste");
                    location = nodeData.getString("location");
                    comment_num = nodeData.getString("comment_num");

                    //��ü �ν��Ͻ� ����.
                    //////////////////���⼭ ���Ͱ� �ȵ�.
                    ListItem data = new ListItem(seq, content, writer, regdate, thumb, avg, img, type, taste, location, comment_num);
                    //ArrayList�� ��ü ����.
                    lastDatas.add(i, data);



                }

                //얻은 데이터로 리스트 뷰 만드는 부분

                /*
                user 이름을 테스트 해봄
                 */

                for(int i=0; i<lastDatas.size();i++){

                    ListItem tempItem = lastDatas.get(i);

                    if(tempItem.getData(7).equals("korean")){
                        typeImg = res.getDrawable(R.drawable.nationality_korean);
                    }else if(tempItem.getData(7).equals("japanes")){
                        typeImg = res.getDrawable(R.drawable.nationality_japanes);
                    }else if(tempItem.getData(7).equals("european")){
                        typeImg = res.getDrawable(R.drawable.nationality_european);
                    }else if(tempItem.getData(7).equals("chines")){
                        typeImg = res.getDrawable(R.drawable.nationality_chines);
                    }else{
                        //그냥 국가 이미지
                    }
                    if(tempItem.getData(8).equals("hot")){
                        tasteImg = res.getDrawable(R.drawable.taste_hot);
                    }else if(tempItem.getData(8).equals("swe")){
                        tasteImg = res.getDrawable(R.drawable.taste_swe);
                    }else if(tempItem.getData(8).equals("sal")){
                        tasteImg = res.getDrawable(R.drawable.taste_sal);
                    }else if(tempItem.getData(8).equals("soa")){
                        tasteImg = res.getDrawable(R.drawable.taste_soa);
                    }else if(tempItem.getData(8).equals("bit")){
                        tasteImg = res.getDrawable(R.drawable.taste_bit);
                    }else{
                        //그냥 맛
                    }
                    if(tempItem.getData(9).equals("Seoul")){
                        locImg = res.getDrawable(R.drawable.location_sl);
                    }else if(tempItem.getData(9).equals("KK")){
                        locImg = res.getDrawable(R.drawable.location_kk);
                    }else if(tempItem.getData(9).equals("KW")){
                        locImg = res.getDrawable(R.drawable.location_kw);
                    }else if(tempItem.getData(9).equals("KS")){
                        locImg = res.getDrawable(R.drawable.location_ks);
                    }else if(tempItem.getData(9).equals("CC")){
                        locImg = res.getDrawable(R.drawable.location_cc);
                    }else if(tempItem.getData(9).equals("JR")){
                        locImg = res.getDrawable(R.drawable.location_jr);
                    }else if(tempItem.getData(9).equals("JJ")){
                        locImg = res.getDrawable(R.drawable.location_jj);
                    }else{

                    }


                    Log.v(logtag+" after temp", tempItem.getData(6));

                    middle_listAdapter.addItem(new Middle_listItem(tempItem.getData(2),
                            tempItem.getData(5), tempItem.getData(3), typeImg, tasteImg,
                            locImg, res.getDrawable(R.drawable.time_dn),
                            tempItem.getData(1), "http://54.64.160.105:8080/img/"+tempItem.getData(6), tempItem.getData(10), tempItem.getData(0)));
                }

                middleListView.setAdapter(middle_listAdapter);

                //메뉴 클릭 시 인텐트 전달
                middleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Middle_listItem curItem = (Middle_listItem) middle_listAdapter.getItem(position);
                        String curId = curItem.getId();
                        String curScore = curItem.getScore();
                        String curContent = curItem.getContent();
                        String curSeq = curItem.getSeq();

                        //Toast.makeText(getApplicationContext(), "글쓴이눈 ! " + curData, Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), ContentActivity.class);
                        intent.putExtra("id", curId);
                        intent.putExtra("score", curScore);
                        intent.putExtra("content", curContent);
                        intent.putExtra("seq", curSeq);
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        Log.d("MESSAGE1", "OK");
                    }

                });
            }catch(Exception ex) {

            }
        }

    }
}