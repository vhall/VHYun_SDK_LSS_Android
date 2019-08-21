package com.vhallyun.lss;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.vhall.framework.VhallSDK;


import com.vhall.push.VHLivePushFormat;
import com.vhallyun.lss.push.PushActivity;
import com.vhallyun.lss.screenRecord.ScreenRecordActivity;
import com.vhallyun.lss.watchlive.LivePlayerActivity;
import com.vhallyun.lss.watchplayback.VodPlayerActivity;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.RECORD_AUDIO;


/**
 * Created by Hank on 2017/12/8.
 */
public class MainActivity extends Activity {

    TextView tv_appid;
    private static final String TAG = "VHLivePusher";
    private static final int REQUEST_PUSH = 0;
    private static final int REQUEST_AUDIO_RECORD = 4;
    public static final String KEY_LSS_ID = "lssId";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_VOD_ID = "vodId";
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    EditText edtLssId, edtToken, edtVodId;
    private String token;
    private String roomId, vodId, channelId;
    private RadioGroup rgDpi;
    private EditText edtFrameRate, edtBitRate;
    private String frameRate, bitRate;
    private int dpi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        tv_appid = this.findViewById(R.id.tv_appid);
        rgDpi = findViewById(R.id.rg_dpi);
        tv_appid.setText(VhallSDK.getInstance().getAPP_ID());
        roomId = sp.getString(KEY_LSS_ID, "");
        token = sp.getString(KEY_TOKEN, "");
        vodId = sp.getString(KEY_VOD_ID, "");
        edtLssId = findViewById(R.id.edt_room_id);
        edtToken = findViewById(R.id.edt_token);
        edtVodId = findViewById(R.id.edt_vod_id);
        edtFrameRate = findViewById(R.id.edt_frame_rate);
        edtBitRate = findViewById(R.id.edt_bit_rate);
        edtFrameRate.setText("15");
        edtBitRate.setText("1500");
        edtVodId.setText(vodId);
        edtLssId.setText(roomId);
        edtToken.setText(token);
        editor = sp.edit();

    }

    public void push(View view) {
        roomId = edtLssId.getText().toString().trim();
        editor.putString(KEY_LSS_ID, roomId);
        channelId = roomId;
        dpi = VHLivePushFormat.PUSH_MODE_XHD;
        switch (rgDpi.getCheckedRadioButtonId()) {
            case R.id.rb_dpi_480:
                dpi = VHLivePushFormat.PUSH_MODE_HD;
                break;
            case R.id.rb_dpi_720:
                dpi = VHLivePushFormat.PUSH_MODE_XHD;
                break;
            case R.id.rb_dpi_1080:
                dpi = VHLivePushFormat.PUSH_MODE_XXHD;
                break;
        }
        frameRate = edtFrameRate.getText().toString().trim();
        bitRate = edtBitRate.getText().toString().trim();
        if (getPushPermission(REQUEST_PUSH)) {
            Intent intent = new Intent(this, PushActivity.class);
            intent.putExtra("dpi", dpi);
            if (!TextUtils.isEmpty(frameRate)) {
                intent.putExtra("frameRate", Integer.valueOf(frameRate));
            }
            if (!TextUtils.isEmpty(bitRate)) {
                intent.putExtra("bitRate", Integer.valueOf(bitRate));
            }
            startAct(intent);
        }
    }

    public void playlive(View view) {
        roomId = edtLssId.getText().toString().trim();
        editor.putString(KEY_LSS_ID, roomId);
        channelId = roomId;
        Intent intent = new Intent(this, LivePlayerActivity.class);
        startAct(intent);
    }


    public void showScreenRecord(View view) {
        roomId = edtLssId.getText().toString().trim();
        editor.putString(KEY_LSS_ID, roomId);
        channelId = roomId;
        if (getAudioRecordPermission()) {
            Intent intent = new Intent(this, ScreenRecordActivity.class);
            startAct(intent);

        }
    }

    //观看回放需要下载、保存和读取文档信息
    public void playvod(View view) {
        vodId = edtVodId.getText().toString().trim();
        editor.putString(KEY_VOD_ID, vodId);
        channelId = vodId;
        Intent intent = new Intent(this, VodPlayerActivity.class);
        startAct(intent);
    }

    private boolean getPushPermission(int requestCode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        Log.e(TAG, "CAMERA:" + checkSelfPermission(CAMERA) + " MIC:" + checkSelfPermission(RECORD_AUDIO));
        if (checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        requestPermissions(new String[]{CAMERA, RECORD_AUDIO}, requestCode);
        return false;
    }

    private boolean getAudioRecordPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        requestPermissions(new String[]{RECORD_AUDIO}, REQUEST_AUDIO_RECORD);
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PUSH) {
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "get REQUEST_PUSH permission success");
                Intent intent = new Intent(this, PushActivity.class);
                intent.putExtra("dpi", dpi);
                if (!TextUtils.isEmpty(frameRate)) {
                    intent.putExtra("frameRate", frameRate);
                }
                if (!TextUtils.isEmpty(bitRate)) {
                    intent.putExtra("bitRate", bitRate);
                }
                startAct(intent);
            }
        } else if (requestCode == REQUEST_AUDIO_RECORD) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(this, ScreenRecordActivity.class);
                startAct(intent);
            }
        }
    }

    private void startAct(Intent intent) {
        token = edtToken.getText().toString().trim();
        editor.putString(KEY_TOKEN, token);
        editor.commit();

        if (TextUtils.isEmpty(channelId) || TextUtils.isEmpty(token))
            return;
        intent.putExtra("channelid", channelId);
        intent.putExtra("token", token);
        startActivity(intent);
    }


}
