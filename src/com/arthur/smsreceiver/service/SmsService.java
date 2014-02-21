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

		//��̬ע����ܶ�����Ϣ
		IntentFilter localIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
		//�������ȼ�
        localIntentFilter.setPriority(Integer.MAX_VALUE);
        mSmsReceiver = new SmsReceiver();
        registerReceiver(mSmsReceiver, localIntentFilter);
		
        Log.v("arthur", "SmsService����"+ System.currentTimeMillis());  
		Toast.makeText(SmsService.this, "SmsService����", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mSmsReceiver);
	}

}
