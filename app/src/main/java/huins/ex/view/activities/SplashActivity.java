package huins.ex.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import huins.ex.R;

/**
 * Created by KJH on 2016-11-06.
 */

public class SplashActivity extends Activity{

    Handler handler;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler = new Handler();

        handler.postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(SplashActivity.this, VoiceControlActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }

}
