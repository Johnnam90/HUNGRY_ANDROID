package kr.ac.snust.hungry.hungry;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by John on 10/7/15.
 */
public class UserActivity extends Activity {
    Button logIn_btn;
    Button connectWeb_btn;
    EditText logIn_id;
    EditText logIn_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);

        Toast.makeText(getApplicationContext(), "인터넷 연결이 필요합니다.", Toast.LENGTH_SHORT).show();

        logIn_btn = (Button) findViewById(R.id.logIn_btn);
        connectWeb_btn = (Button) findViewById(R.id.connectWeb_btn);
        logIn_id = (EditText) findViewById(R.id.logIn_id);
        logIn_pw = (EditText) findViewById(R.id.logIn_pw);

        logIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = logIn_id.getText().toString();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("id", userId);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);

            }
        });

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
