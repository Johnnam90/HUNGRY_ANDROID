package kr.ac.snust.hungry.hungry;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
 * Created by John on 10/7/15.
 */

public class UserActivity extends Activity {
    Button logIn_btn;
    Button connectWeb_btn;
    EditText logIn_id;
    EditText logIn_pw;
    getJsonByPhp task;

    public String pub_userId;
    public String pub_userPw;
    public String pub_userId_AD;
    public String pub_userPw_AD;
    public String pub_userName_AD;
    public String pub_userThumb_AD;
    public String TestId = "john";
    public String TestPw = "john";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);

        //자동로그인
        if(UserPreference.getUserId(UserActivity.this).length() == 0)
        {
            //Log-in Process
            Toast.makeText(getApplicationContext(), "인터넷 연결이 필요합니다.", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "로그인 중입니다.", Toast.LENGTH_LONG).show();
            // Call Next Activity
            pub_userId = UserPreference.getUserId(UserActivity.this);
            pub_userPw = UserPreference.getUserPw(UserActivity.this);
            Log.d("PREF_ID", pub_userId);
            Log.d("PREF_PW", pub_userPw);

            task = new getJsonByPhp();
            task.execute("http://54.64.160.105/login_res.php");
        }


        //Inflation
        logIn_btn = (Button) findViewById(R.id.logIn_btn);
        connectWeb_btn = (Button) findViewById(R.id.connectWeb_btn);
        logIn_id = (EditText) findViewById(R.id.logIn_id);
        logIn_pw = (EditText) findViewById(R.id.logIn_pw);

        /*
        * 로그인 MainActivity로 넘기기 */
        logIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = logIn_id.getText().toString();
                String userPw = logIn_pw.getText().toString();

                //AsyncTask에서 이용하기 위함
                pub_userId = userId;
                pub_userPw = userPw;

                task = new getJsonByPhp();
                task.execute("http://54.64.160.105/login_res.php");
            }
        });

        /*
        * 회원가입 */
        connectWeb_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                Intent web_intent = new Intent();
                web_intent.setAction(Intent.ACTION_VIEW);
                web_intent.setData(Uri.parse("http://54.64.160.105:8080"));
                startActivity(web_intent);
            }
        });



    }

    private class getJsonByPhp extends AsyncTask<String, Integer, String> {
        protected String doInBackground(String... urls) {
            StringBuilder jsonHtml = new StringBuilder();

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                if(conn != null) {
                    conn.setConnectTimeout(10000);

                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setRequestMethod("POST");

                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setUseCaches(false);
                    conn.setDefaultUseCaches(false);

                    conn.connect();

                    String string2post = "id="+pub_userId;
                    byte[] bytes2post = string2post.getBytes();

                    OutputStream opStream = conn.getOutputStream();
                    opStream.write(bytes2post);
                    opStream.flush();
                    opStream.close();

                    if(HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                        InputStream ipStream = conn.getInputStream();
                        InputStreamReader ipStreamRd = new InputStreamReader(ipStream, "UTF-8");
                        BufferedReader buffered = new BufferedReader(ipStreamRd);

                        for(; ; ) {
                            String line = buffered.readLine();

                            if (line == null) break;

                            jsonHtml.append(line + "\n");
                        }
                        buffered.close();
                        ipStreamRd.close();
                        ipStream.close();

                        conn.disconnect();
                    }
                }
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
            return jsonHtml.toString();
        }

        protected void onPostExecute(String str) {
            String id;
            String pass;
            String name;
            String regdate;
            String thumb;

            ArrayList<LogIn_listItem> lastDatas = new ArrayList<LogIn_listItem>();
            StringBuilder tempLastString = new StringBuilder();

            try {
                JSONObject jsonObject = new JSONObject(str);
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for(int i = 0; i< jsonArray.length(); i++) {
                    JSONObject nodeData = jsonArray.getJSONObject(i);

                    id = nodeData.getString("id");
                    pass = nodeData.getString("pass");
                    name = nodeData.getString("name");
                    regdate = nodeData.getString("regdate");
                    thumb = nodeData.getString("thumb");

                    LogIn_listItem data = new LogIn_listItem(id, pass, name, regdate, thumb);
                    lastDatas.add(i, data);
                }

                LogIn_listItem tempItem = lastDatas.get(0);
                pub_userId_AD = tempItem.getData(0);
                pub_userPw_AD = tempItem.getData(1);
                pub_userName_AD = tempItem.getData(2);
                pub_userThumb_AD = tempItem.getData(4);

                /*
                * 이 작업은 반드시 onPostExecute에서 이루어져야 함
                * onCreate에서 할 시 어뎁터 필요할 수 있음 */
               if(pub_userId.equals(pub_userId_AD) && pub_userPw.equals(pub_userPw_AD)) {
                   //성공
                   Log.d("MESSAGE", "1");

                   //SharedPreferences 저장
                   UserPreference.setUserId(UserActivity.this, pub_userId_AD);
                   UserPreference.setUserPw(UserActivity.this, pub_userPw_AD);
                   Log.d("MESSAGE", "1");

                   logIn_id.setText("");
                   logIn_pw.setText("");

                   Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                   intent.putExtra("id", pub_userId_AD);
//                    intent.putExtra("pw", pub_userPw_AD);
                   intent.putExtra("name", pub_userName_AD);
                   intent.putExtra("thumb", pub_userThumb_AD);
                   intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                   Log.d("MESSAGE", "1");
                } else {
                    Toast.makeText(getApplicationContext(), "사용자 정보를 다시 확인해주세요", Toast.LENGTH_SHORT).show();
                    logIn_id.setText("");
                    logIn_pw.setText("");
                   Log.d("MESSAGE", "1");
                }

            }catch(Exception ex) {

            }
        }
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
