package com.arthur.smsreceiver.service;

import com.arthur.smsreceiver.broadcast.SmsReceiver;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

public class SmsService extends Service{
	private SmsReceiver mSmsReceiver; 
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
		Vibrator vib = (Vibrator)SmsService.this.getSystemService(Service.VIBRATOR_SERVICE);
		vib.vibrate(1000);

		//动态注册接受短信消息
		IntentFilter localIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
		//设置优先级
        localIntentFilter.setPriority(Integer.MAX_VALUE);
        mSmsReceiver = new SmsReceiver();
        registerReceiver(mSmsReceiver, localIntentFilter);
		
        Log.v("arthur", "SmsService启动"+ System.currentTimeMillis());  
		Toast.makeText(SmsService.this, "SmsService启动", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mSmsReceiver);
	}

}
