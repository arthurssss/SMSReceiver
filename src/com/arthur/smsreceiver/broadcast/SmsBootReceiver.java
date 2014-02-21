package com.arthur.smsreceiver.broadcast;

import com.arthur.smsreceiver.service.SmsService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SmsBootReceiver extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.e("arthur", "SmsBootReceiver onReceive");
		if(intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
		{
			Intent i = new Intent(context, SmsService.class);
            context.startService(i);
		}
	}
}
