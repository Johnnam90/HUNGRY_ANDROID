package kr.ac.snust.hungry.hungry;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
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

/**
 * Created by John on 8/14/15.
 */
public class MiddleListActivity extends Activity {

    ListView middleListView;
    Middle_listAdapter middle_listAdapter;
    TextView middle_menu_id;

    getJsonByPhp task;

    String[] url = new String[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.middle_list_layout);

        //데이터 받아오는 역할을 하는 AsyncTask 상속 받은 클래스
        task = new getJsonByPhp();

        //데이터 받아오는 쓰레드 시작
        task.execute("http://54.64.160.105/middle_view.php");

        //인텐트로 전달받은 값 메뉴 이름으로 설정
        middle_menu_id = (TextView) findViewById(R.id.middle_menu_id);
        Intent recievedIntent = getIntent();
        String selectedMenu = recievedIntent.getStringExtra("menu");
        middle_menu_id.setText(selectedMenu);

        //메인메뉴
        middleListView = (ListView) findViewById(R.id.middleListView);
        middle_listAdapter = new Middle_listAdapter(this);


//        middle_listAdapter.addItem(new Middle_listItem(res.getDrawable(R.drawable.profile), "john",
//                "4.5", "2015.11.11", res.getDrawable(R.drawable.nationality_korean), res.getDrawable(R.drawable.taste_swe),
//                res.getDrawable(R.drawable.location_sl), res.getDrawable(R.drawable.time_lc),
//                "Content is here.", res.getDrawable(R.drawable.samplefood), "5"));
//
//        middle_listAdapter.addItem(new Middle_listItem(res.getDrawable(R.drawable.profile), "john",
//                "4.5", "2015.11.14", res.getDrawable(R.drawable.nationality_chines), res.getDrawable(R.drawable.taste_hot),
//                res.getDrawable(R.drawable.location_kk), res.getDrawable(R.drawable.time_dn),
//                "리스트뷰가 완성되었다!!! 효과는 대단했다!!!!!!.", res.getDrawable(R.drawable.samplefood), "3"));

        /*
        middle_listAdapter.addItem(new Middle_listItem("john",
                "4.5", "2015.11.14", res.getDrawable(R.drawable.nationality_chines), res.getDrawable(R.drawable.taste_hot),
                res.getDrawable(R.drawable.location_kk), res.getDrawable(R.drawable.time_dn),
                "우와와와와와와 한국~~~~", url[0], "3"));

        middle_listAdapter.addItem(new Middle_listItem("iu_love",
                "4.5", "2015.11.14", res.getDrawable(R.drawable.nationality_chines), res.getDrawable(R.drawable.taste_hot),
                res.getDrawable(R.drawable.location_kk), res.getDrawable(R.drawable.time_dn),
                "우와와와와와와 대한민국~~~~", url[1], "3"));

        middle_listAdapter.addItem(new Middle_listItem("janejang",
                "4.5", "2015.11.14", res.getDrawable(R.drawable.nationality_chines), res.getDrawable(R.drawable.taste_hot),
                res.getDrawable(R.drawable.location_kk), res.getDrawable(R.drawable.time_dn),
                "우와와와와와와 한국~~~~", url[4], "3"));

        middle_listAdapter.addItem(new Middle_listItem("woobin",
                "4.5", "2015.11.14", res.getDrawable(R.drawable.nationality_chines), res.getDrawable(R.drawable.taste_hot),
                res.getDrawable(R.drawable.location_kk), res.getDrawable(R.drawable.time_dn),
                "우와와와와와와 중국~~~~~~", url[2], "5"));

        middle_listAdapter.addItem(new Middle_listItem("john",
                "4.5", "2015.11.14", res.getDrawable(R.drawable.nationality_chines), res.getDrawable(R.drawable.taste_hot),
                res.getDrawable(R.drawable.location_kk), res.getDrawable(R.drawable.time_dn),
                "우와와와와와와 일본~~~~", url[3], "3"));


        middleListView.setAdapter(middle_listAdapter);
        */
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

        @Override
        protected String doInBackground(String... urls) {
            StringBuilder jsonHtml = new StringBuilder();
            try{
                //url ����
                URL url = new URL(urls[0]);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();


                //����Ǿ��ٸ�
                if(conn != null)    {
                    conn.setConnectTimeout(10000);


                    /* post 하는 부분 */
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setRequestMethod("POST");

                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setUseCaches(false);
                    conn.setDefaultUseCaches(false);

                    conn.connect();

                    String string2post = "a=1&b=1&c=0";
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

                   /*
        *임시 URL 부분*/
            url[0] = "http://54.64.160.105:8080/img/2.jpg";
            url[1] = "http://54.64.160.105:8080/img/3.png";
            url[2] = "http://54.64.160.105:8080/img/P20150110_140203242_F660F5AC-04FD-413C-B623-78F8EA3B1DD0.jpg";
            url[3] = "http://54.64.160.105:8080/img/IMG_5583.jpg";
            url[4] = "http://54.64.160.105:8080/img/Screen%20Shot%202015-05-26%20at%201.12.10%20PM.png";

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
                String testName = lastDatas.get(1).getData(3);
                String testContent = lastDatas.get(1).getData(2);

                middle_listAdapter.addItem(new Middle_listItem(testName,
                        "4.5", "2015.11.14", res.getDrawable(R.drawable.nationality_chines), res.getDrawable(R.drawable.taste_hot),
                        res.getDrawable(R.drawable.location_kk), res.getDrawable(R.drawable.time_dn),
                        testContent, url[0], "3"));

                middle_listAdapter.addItem(new Middle_listItem("iu_love",
                        "4.5", "2015.11.14", res.getDrawable(R.drawable.nationality_chines), res.getDrawable(R.drawable.taste_hot),
                        res.getDrawable(R.drawable.location_kk), res.getDrawable(R.drawable.time_dn),
                        "우와와와와와와 대한민국~~~~", url[1], "3"));

                middle_listAdapter.addItem(new Middle_listItem("janejang",
                        "4.5", "2015.11.14", res.getDrawable(R.drawable.nationality_chines), res.getDrawable(R.drawable.taste_hot),
                        res.getDrawable(R.drawable.location_kk), res.getDrawable(R.drawable.time_dn),
                        "우와와와와와와 한국~~~~", url[4], "3"));

                middle_listAdapter.addItem(new Middle_listItem("woobin",
                        "4.5", "2015.11.14", res.getDrawable(R.drawable.nationality_chines), res.getDrawable(R.drawable.taste_hot),
                        res.getDrawable(R.drawable.location_kk), res.getDrawable(R.drawable.time_dn),
                        "우와와와와와와 중국~~~~~~", url[2], "5"));

                middle_listAdapter.addItem(new Middle_listItem("john",
                        "4.5", "2015.11.14", res.getDrawable(R.drawable.nationality_chines), res.getDrawable(R.drawable.taste_hot),
                        res.getDrawable(R.drawable.location_kk), res.getDrawable(R.drawable.time_dn),
                        "우와와와와와와 일본~~~~", url[3], "3"));


                middleListView.setAdapter(middle_listAdapter);

                //메뉴 클릭 시 인텐트 전달
                middleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Middle_listItem curItem = (Middle_listItem) middle_listAdapter.getItem(position);
                        String curId = curItem.getId();
                        String curScore = curItem.getScore();
                        String curContent = curItem.getContent();

                        //Toast.makeText(getApplicationContext(), "글쓴이눈 ! " + curData, Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), ContentActivity.class);
                        intent.putExtra("id", curId);
                        intent.putExtra("score", curScore);
                        intent.putExtra("content", curContent);
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    }

                });
            }catch(Exception ex) {

            }
        }

    }
}