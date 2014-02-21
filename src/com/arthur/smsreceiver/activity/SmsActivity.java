package com.arthur.smsreceiver.activity;

import com.arthur.smsreceiver.R;
import com.arthur.smsreceiver.broadcast.SmsReceiver;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.app.Service;
import android.content.IntentFilter;

public class SmsActivity extends Activity {
	private SmsReceiver mSmsReceiver; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Vibrator vib = (Vibrator)this.getSystemService(Service.VIBRATOR_SERVICE);
		vib.vibrate(1000);
		
		//��̬ע����ܶ�����Ϣ
		IntentFilter localIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
		//�������ȼ�
        localIntentFilter.setPriority(Integer.MAX_VALUE);
        mSmsReceiver = new SmsReceiver();
        registerReceiver(mSmsReceiver, localIntentFilter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mSmsReceiver);
	}

}
