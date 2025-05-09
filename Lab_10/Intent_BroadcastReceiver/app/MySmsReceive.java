package com.example.intent_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MySmsReceive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        processReceive(context, intent);
    }

    public void processReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        String message = "";
        if (extras != null) {
            Object[] smsExtra = (Object[]) extras.get("pdus");
            for (Object pdu : smsExtra) {
                SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
                String body = sms.getMessageBody();
                String address = sms.getOriginatingAddress();
                message += "Có 1 tin nhắn từ " + address + ":\n" + body + "\n";
            }
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}
