package cc.siriuscloud.xiaoy;


import android.app.Service;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AlarmActivity extends AppCompatActivity {

    private static final String TAG = AlarmActivity.class.getName();
    private MediaPlayer mediaPlayer;    //媒体播放
    private Vibrator vibrator;          //振动器
    private LinearLayout alarmLayout;
    private TextView titleText;
    private TextView contentText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        String title = this.getIntent().getStringExtra("title");
        String content = this.getIntent().getStringExtra("content");


        titleText = findViewById(R.id.title_txt);
        contentText = findViewById(R.id.content_txt);


        if (!TextUtils.isEmpty(title)) {
            titleText.setText(title);
        }

        if (!TextUtils.isEmpty(content)) {
            contentText.setText(content);
        }


        alarmLayout = findViewById(R.id.alarm_layout);
        alarmLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (null != mediaPlayer) {

                    mediaPlayer.stop();
                    mediaPlayer.release();
                }

                if (null != vibrator) {
                    vibrator.cancel();
                }

                finish();

            }
        });

        mediaPlayer = MediaPlayer.create(this, R.raw.in_call_alarm);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        vibrator = (Vibrator) this.getSystemService(Service.VIBRATOR_SERVICE);
        vibrator.vibrate(new long[]{100, 10, 100, 600}, 0);


    }

}
