package kr.ac.snust.hungry.hungry;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
    String txtSeq;
<<<<<<< HEAD
    String imgURL[];

    int cnt;
=======

    String imgURL[];

    int cnt;

>>>>>>> 8cb97a36eca03ca3d6d501f0f2b91c895d1f26d6

    getJsonByPHP task;


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
        contentArea.setText("" + intent.getStringExtra("content"));

        txtSeq = intent.getStringExtra("seq");

        Toast.makeText(getApplicationContext(), "" + txtSeq, Toast.LENGTH_LONG).show();

<<<<<<< HEAD
        task = new getJsonByPHP();
        task.execute("http://54.64.160.105/content.php");
=======

        task = new getJsonByPHP();
        task.execute("http://54.64.160.105/content.php");

>>>>>>> 8cb97a36eca03ca3d6d501f0f2b91c895d1f26d6

        //내용 이미지 설쩡
        img1=(ImageView) findViewById(R.id.img1);
        img2=(ImageView) findViewById(R.id.img2);
        img3=(ImageView) findViewById(R.id.img3);

        replyNum=(TextView) findViewById(R.id.reply_num);

        // 어댑터 객체 생성
        replyAdapter = new reply_listAdapter(this);


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

    private class getJsonByPHP extends AsyncTask<String, Integer, String>{

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

                    String string2post = "posting_seq="+txtSeq;
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

        protected void onPostExecute(String str){
            String commentSeq;
            String commentContent;
            String commentRegdate;
            String commentWriter;


            int idx=str.indexOf("@");
            String str1=str.substring(0, idx);
            String str2=str.substring(idx + 1);

            Resources res = getResources();

            //str1: 이미지, Glide 설졍

            try{
                String basedUrl = "http://54.64.160.105:8080/img/";

                JSONObject jsonObject = new JSONObject(str1);
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                imgURL = new String[jsonArray.length()];

                for(int i=0; i< jsonArray.length(); i++){
                    JSONObject nodeData = jsonArray.getJSONObject(i);

                    imgURL[i] =basedUrl + nodeData.getString("img");
                    Log.d("imgURL",imgURL[i]);
                }
                if(imgURL.length==1){
                    Glide.with(ContentActivity.this).load(imgURL[0]).into(img1);
                }else if(imgURL.length==2){
                    Glide.with(ContentActivity.this).load(imgURL[0]).into(img1);

                    img2.setVisibility(View.VISIBLE);
                    Glide.with(ContentActivity.this).load(imgURL[1]).into(img2);
                }else if(imgURL.length==3){
                    Glide.with(ContentActivity.this).load(imgURL[0]).into(img1);

                    img2.setVisibility(View.VISIBLE);
                    Glide.with(ContentActivity.this).load(imgURL[1]).into(img2);

                    img3.setVisibility(View.VISIBLE);
                    Glide.with(ContentActivity.this).load(imgURL[2]).into(img3);
                }
            }catch (Exception ex){

            }

            //str2: 댓글, 리스트뷰 작성
            ArrayList<reply_listItem> lastDatas = new ArrayList<reply_listItem>();
            StringBuilder tempLastString = new StringBuilder();

            try{
                JSONObject jsonObject = new JSONObject(str2);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                cnt = jsonArray.length();

                for (int i = 0; i < cnt; i++) {
                    JSONObject nodeData = jsonArray.getJSONObject(i);

                    commentContent = nodeData.getString("content");
                    commentWriter = nodeData.getString("writer");
                    commentRegdate = nodeData.getString("regdate");

                    reply_listItem data = new reply_listItem(commentContent, commentWriter, commentRegdate);

                    lastDatas.add(i, data);

                    //making List View just like replyAdapter.addItem(new reply_listItem(writer,regdate, content));
                    for(int r=0; r<lastDatas.size();r++){

                        reply_listItem tempItem = lastDatas.get(r);

                        replyAdapter.addItem(new reply_listItem(tempItem.getData(1), tempItem.getData(2), tempItem.getData(0)));
                    }
                    //댓글위한 세로 길이 확보

                    cnt+=1;
                    replyNum.setText(""+cnt);
                    replyListView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, cnt * 160));

                    replyListView.setAdapter(replyAdapter);
                }
            }catch (Exception ex){

            }
        }
    }
}
