package kr.ac.snust.hungry.hungry;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by gomi on 15. 9. 23..
 */
public class reply_listView extends LinearLayout {
    private TextView reply_writer;
    private TextView reply_date;
    private TextView reply_content;

    public reply_listView(Context context, reply_listItem rItem){
        super(context);

        // Layout Inflation
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.reply_listitem, this, true);

        // Set Text 01
        reply_writer = (TextView) findViewById(R.id.reply_writer);
        reply_writer.setText(rItem.getData(0));

        // Set Text 02
        reply_date = (TextView) findViewById(R.id.reply_date);
        reply_date.setText(rItem.getData(1));

        // Set Text 03
        reply_content = (TextView) findViewById(R.id.reply_content);
        reply_content.setText(rItem.getData(2));
    }
    /**
     * set Text
     *
     * @param index
     * @param data
     */
    public void setText(int index, String data) {
        if (index == 0) {
            reply_writer.setText(data);
        } else if (index == 1) {
            reply_date.setText(data);
        } else if (index == 2) {
            reply_content.setText(data);
        } else {
            throw new IllegalArgumentException();
        }
    }

}
