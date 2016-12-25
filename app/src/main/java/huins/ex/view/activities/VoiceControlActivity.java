package huins.ex.view.activities;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import huins.ex.R;
import huins.ex.proto.action.Action;
import huins.ex.proto.action.RCAction;
import huins.ex.proto.attribute.AttributeEvent;
import huins.ex.proto.property.VehicleMode;
import huins.ex.util.constants.ConstantsIntent;
import huins.ex.view.activities.base.BaseUIActivity;

/**
 * Created by K on 2016-11-01
 */

public class VoiceControlActivity extends BaseUIActivity{

    private Switch switch_cnt;
    private VoiceControlActivity.ConnectCheckedChangeListener cnt_event_listener;

    private static final IntentFilter eventFilter = new IntentFilter();

    static {
        eventFilter.addAction(AttributeEvent.STATE_CONNECTED);
        eventFilter.addAction(AttributeEvent.STATE_DISCONNECTED);
        eventFilter.addAction(ConstantsIntent.INTENT_GCS_AUTOPILOT_ERROR);
        eventFilter.addAction(ConstantsIntent.INTENT_GCS_CHANGE_MENU);
        eventFilter.addAction(GCSBaseApp.ACTION_DRONE_CONNECTION_FAILED);
    }

    private final BroadcastReceiver eventReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            switch (action) {
                case ConstantsIntent.INTENT_GCS_AUTOPILOT_ERROR:
                    break;
                case ConstantsIntent.INTENT_GCS_CHANGE_MENU:
                    break;
                case AttributeEvent.STATE_CONNECTED:
                    switch_cnt.setChecked(baseApp.getFlight().IsConnected());
                    Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
                    break;
                case AttributeEvent.STATE_DISCONNECTED:
                    Toast.makeText(getApplicationContext(), "Disconnected", Toast.LENGTH_SHORT).show();
                    break;
                case GCSBaseApp.ACTION_DRONE_CONNECTION_FAILED:
                    Toast.makeText(getApplicationContext(), "Connect Failed", Toast.LENGTH_SHORT).show();
                    switch_cnt.setChecked(baseApp.getFlight().IsConnected());
                    break;
            }
        }
    };

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onApiConnected() {
        super.onApiConnected();
        //   enableSlidingUpPanel(baseApp.getFlight());
        getBroadcastManager().registerReceiver(eventReceiver, eventFilter);
    }

    @Override
    public void onApiDisconnected() {
        super.onApiDisconnected();
        //   enableSlidingUpPanel(baseApp.getFlight());
        getBroadcastManager().unregisterReceiver(eventReceiver);
    }


    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        switch_cnt.setChecked(baseApp.getFlight().IsConnected());

        switch_cnt.setOnClickListener(cnt_event_listener);
        switch_cnt.setOnCheckedChangeListener(cnt_event_listener);
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

        switch_cnt.setOnCheckedChangeListener(null);
    }

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }

    public class ConnectCheckedChangeListener implements CompoundButton.OnClickListener, CompoundButton.OnCheckedChangeListener
    {
        @Override
        public void onClick(View view) {
            toggleFlightConnection();
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked && !baseApp.getFlight().IsConnected()) {
                switch_cnt.setChecked(false);
            }
        }
    }

    public void onBackPressed() {
        //super.onBackPressed(); //지워야 실행됨

        AlertDialog.Builder d = new AlertDialog.Builder(this);
        d.setMessage("정말 종료하시겠습니까?");
        d.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // process전체 종료
                finish();
                baseApp.notifyBackPressedFromMain();
            }
        });
        d.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        d.show();
    }

//    Button change_btn_voice;


    TextView r_tv;
    ImageButton v_btn;

    Intent i;
    SpeechRecognizer mRecognizer;

//    AudioManager amanager;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_voice_control);

        r_tv = (TextView) findViewById(R.id.tv_rs);
        v_btn = (ImageButton) findViewById(R.id.btn_voice);
//        amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"ko-KR");
        i.putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE,true); // 오프라인데이터 접근

        switch_cnt = (Switch)findViewById(R.id.switch_cnt_voice);

        cnt_event_listener = new VoiceControlActivity.ConnectCheckedChangeListener();


    }
    public void onDestroy(){
        super.onDestroy();
        //mRecognizer.destroy();
    }

    public void click_voice_btn(View view){
        Log.i("click_btn", "voice_button");

        Toast.makeText(VoiceControlActivity.this, "인식시작", Toast.LENGTH_SHORT).show();
        v_btn.setSelected(true);

        mRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mRecognizer.setRecognitionListener(RListener);
        mRecognizer.startListening(i);
    }

//    public void click_change_btn_voice(View view){
//        Intent intentControlActivity = new Intent(VoiceControlActivity.this, ControlActivity.class);
//        startActivity(intentControlActivity);
//    }

    private RecognitionListener RListener = new RecognitionListener(){
        @Override
        public void onReadyForSpeech(Bundle params) {

        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float rmsdB) {

        }

        @Override
        public void onBufferReceived(byte[] buffer) {

        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onError(int error) {
            if(error==7){
                //not match
            }else if(error==6){
                //time out
            }else if(error==3){
                Toast.makeText(VoiceControlActivity.this,"에러발생 : 다른 녹음기능이 켜져있는지 확인하세요.",Toast.LENGTH_SHORT).show();
            }else if(error==1||error==2){
                Toast.makeText(VoiceControlActivity.this,"에러발생 : 인터넷이 연결되어있는지 확인하세요.",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(VoiceControlActivity.this,"에러발생 에러코드 : "+error,Toast.LENGTH_SHORT).show();
            }

            if(mRecognizer!=null){
                mRecognizer.destroy();
            }
        }

        @Override
        public void onResults(Bundle results) {
            String key = "";
            key = SpeechRecognizer.RESULTS_RECOGNITION;
            ArrayList<String> mResult = results.getStringArrayList(key);
            String[] rs = new String[mResult.size()];
            mResult.toArray(rs);

            r_tv.setText(""+rs[0]);

            //------------------
            voiceControl(""+rs[0]);
            Log.i("call vControl", ""+rs[0]);
            //------------------

            if(mRecognizer != null){
                mRecognizer.destroy();
                Toast.makeText(VoiceControlActivity.this, "인식종료", Toast.LENGTH_SHORT).show();
                v_btn.setSelected(false);
            }
            else{

            }
        }

        @Override
        public void onPartialResults(Bundle partialResults) {
//            String key = "";
//            key = SpeechRecognizer.RESULTS_RECOGNITION;
//            ArrayList<String> mResult = partialResults.getStringArrayList(key);
//            String[] rs = new String[mResult.size()];
//            mResult.toArray(rs);
//            r_tv.setText("" + rs[0]);
        }

        @Override
        public void onEvent(int eventType, Bundle params) {

        }
    };

    TimerTask myTask = new TimerTask(){     //정지 함수
        public void run(){
            Bundle params = new Bundle();
            params.putDouble(RCAction.EXTRA_RC_DATA, 0);
            baseApp.getFlight().performAsyncAction(new Action(RCAction.ACTION_RC_THROTTLE_SEND, params));
            Log.i("function end","up");
        }
    }; //timer.schedule(myTask,500); 단위:ms

    Timer timer = new Timer();


    public static boolean is_takeoff = false; //이륙 상태

    protected void voiceControl(String command) {
        switch (command) {
            case "준비":
                if (!baseApp.getFlight().IsConnected()) {
                    Toast.makeText(VoiceControlActivity.this, "it is not connected", Toast.LENGTH_SHORT).show();
                } else {
                    baseApp.getFlight().changeVehicleMode(VehicleMode.COPTER_ALT_HOLD);
                    baseApp.getFlight().performAction(new Action(RCAction.ACTION_RC_CTR_START));
                    /*
                    Bundle params = new Bundle();
                    baseApp.getFlight().performAction(new Action(RCAction.ACTION_RC_CTR_START));
                    params.putDouble(RCAction.EXTRA_RC_DATA, -0.8); //쓰로틀 시동걸기 좋은 값
                    baseApp.getFlight().performAsyncAction(new Action(RCAction.ACTION_RC_THROTTLE_SEND, params));
                    */
                    Log.i("function end", "voice on");
                }
                break;
            case "일어나":
                if (!baseApp.getFlight().IsConnected()) {
                    Toast.makeText(VoiceControlActivity.this, "it is not connected", Toast.LENGTH_SHORT).show();
                } else {
                    baseApp.getFlight().arm(true);
                    Log.i("function end", "voice on");
                }
                break;
            case "수고했어":
            case "잘자":
                if (!(baseApp.getFlight().IsConnected())) {
                    Toast.makeText(VoiceControlActivity.this, "it is not connected", Toast.LENGTH_SHORT).show();
                } else {
                    baseApp.getFlight().performAction(new Action(RCAction.ACTION_RC_CTR_END));
                    baseApp.getFlight().arm(false);
                    Log.i("function end", "off");
                }
                break;
            case "올라가":
                if(!(baseApp.getFlight().IsConnected())){
                    Toast.makeText(VoiceControlActivity.this,"it is not connected",Toast.LENGTH_SHORT).show();
                }
                else{
                    Bundle params = new Bundle();
                    params.putDouble(RCAction.EXTRA_RC_DATA, +0.3);
                    baseApp.getFlight().performAsyncAction(new Action(RCAction.ACTION_RC_THROTTLE_SEND, params));
                    Log.i("function end","up");
                }
                break;
            case "내려가":
                if(!(baseApp.getFlight().IsConnected())){
                    Toast.makeText(VoiceControlActivity.this,"it is not connected",Toast.LENGTH_SHORT).show();
                }
                else{
                    Bundle params = new Bundle();
                    params.putDouble(RCAction.EXTRA_RC_DATA, -0.2);
                    baseApp.getFlight().performAsyncAction(new Action(RCAction.ACTION_RC_THROTTLE_SEND, params));
                    Log.i("function end","down");
                }
                break;
            case "그만":
                if(!(baseApp.getFlight().IsConnected())){
                    Toast.makeText(VoiceControlActivity.this,"it is not connected",Toast.LENGTH_SHORT).show();
                }
                else {
                    baseApp.getFlight().changeVehicleMode(VehicleMode.COPTER_LAND);
                    //is_takeoff = !is_takeoff;
                }
                break;

            case "멈춰":
                if(!(baseApp.getFlight().IsConnected())){
                    Toast.makeText(VoiceControlActivity.this,"it is not connected",Toast.LENGTH_SHORT).show();
                }
                else{
                    Bundle params = new Bundle();
                    params.putDouble(RCAction.EXTRA_RC_DATA, +0.0);
                    baseApp.getFlight().performAsyncAction(new Action(RCAction.ACTION_RC_THROTTLE_SEND, params));
                }
            case "1단계올라":
            case "1단계 올라":
                if(!(baseApp.getFlight().IsConnected())){
                    Toast.makeText(VoiceControlActivity.this,"it is not connected",Toast.LENGTH_SHORT).show();
                }
                else{

                    Bundle params = new Bundle();
                    params.putDouble(RCAction.EXTRA_RC_DATA, +0.3);
                    baseApp.getFlight().performAsyncAction(new Action(RCAction.ACTION_RC_THROTTLE_SEND, params));
                    try{
                        Thread.sleep(500);
                    }
                    catch(InterruptedException ex){}
                    finally {
                        params.putDouble(RCAction.EXTRA_RC_DATA, +0.0);
                        baseApp.getFlight().performAsyncAction(new Action(RCAction.ACTION_RC_THROTTLE_SEND, params));
                    }
                    Log.i("function end","up");
                }
                break;
            case "1단계내려":
            case "1단계 내려":
                if(!(baseApp.getFlight().IsConnected())){
                    Toast.makeText(VoiceControlActivity.this,"it is not connected",Toast.LENGTH_SHORT).show();
                }
                else{
                    Bundle params = new Bundle();
                    params.putDouble(RCAction.EXTRA_RC_DATA, -0.2);
                    baseApp.getFlight().performAsyncAction(new Action(RCAction.ACTION_RC_THROTTLE_SEND, params));

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable(){
                        public void run(){
                            Bundle params = new Bundle();
                            params.putDouble(RCAction.EXTRA_RC_DATA, +0.0);
                            baseApp.getFlight().performAsyncAction(new Action(RCAction.ACTION_RC_THROTTLE_SEND, params));
                        }
                    }, 1000);
                    Log.i("function end","up");
                }
                break;
            default:
                Toast.makeText(VoiceControlActivity.this, "틀렸잖아요! 다시 말하세요!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

//    public void click_mute_switch(View view) {
//        Log.i("onToggleClicked", "ToggleClick Event Started");
//        // Is the toggle on?
//        boolean on = ((Switch) view).isChecked();
//
//        if (on) {
//            Log.i("onToggleIsChecked", "ToggleClick Is On");
//            //turn off sound, disable notifications
//            amanager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
//            Log.i("STREAM_SYSTEM", "Set to true");
//            //notifications
//            amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
//            Log.i("STREAM_NOTIFICATION", "Set to true");
//            //alarm
//            amanager.setStreamMute(AudioManager.STREAM_ALARM, true);
//            Log.i("STREAM_ALARM", "Set to true");
//            //ringer
//            amanager.setStreamMute(AudioManager.STREAM_RING, true);
//            Log.i("STREAM_RING", "Set to true");
//            //media
//            amanager.setStreamMute(AudioManager.STREAM_MUSIC, true);
//            Log.i("STREAM_MUSIC", "Set to true");
//
//        } else {
//            Log.i("onToggleIsChecked", "ToggleClick Is Off");
//            // turn on sound, enable notifications
//            amanager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
//            Log.i("STREAM_SYSTEM", "Set to False");
//            //notifications
//            amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
//            Log.i("STREAM_NOTIFICATION", "Set to False");
//            //alarm
//            amanager.setStreamMute(AudioManager.STREAM_ALARM, true);
//            Log.i("STREAM_ALARM", "Set to False");
//            //ringer
//            amanager.setStreamMute(AudioManager.STREAM_RING, true);
//            Log.i("STREAM_RING", "Set to False");
//            //media
//            amanager.setStreamMute(AudioManager.STREAM_MUSIC, false);
//            Log.i("STREAM_MUSIC", "Set to False");
//        }
//        Log.i("onToggleClicked", "ToggleClick Event Ended");
//    }
}
