package com.arthur.smsreceiver.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {
	public SmsReceiver() {
		Log.v("TAG", "SmsRecevier create");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.v("TAG", "SmsRecevier onReceive");
		Object[] pdus = (Object[]) intent.getExtras().get("pdus");
		if (pdus != null && pdus.length > 0) {
			SmsMessage[] messages = new SmsMessage[pdus.length];
			for (int i = 0; i < pdus.length; i++) {
				byte[] pdu = (byte[]) pdus[i];
				messages[i] = SmsMessage.createFromPdu(pdu);
			}
			for (SmsMessage message : messages) {
				String content = message.getMessageBody();// �õ���������
				String sender = message.getOriginatingAddress();// �õ�����Ϣ�ĺ���
				if (sender.equals("13816884644")) {
					abortBroadcast();// ��ֹ����
					Log.e("TAG", "�˺���Ϊ���������룬������!");
					SmsManager smsManager = SmsManager.getDefault();// ����Ϣʱ��Ҫ��
					smsManager.sendTextMessage("+8613816884644", null, content,
							null, null);// ת����
					Log.v("TAG", content);
				}
			}
		}
	}
}
