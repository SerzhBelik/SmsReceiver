package com.example.belikov.sms_sender;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {

    public static final String PHONE_NUMBER = "phone number";
    public static final String TEXT = "text";

    @Override
    public void onReceive(Context context, Intent intent) {
// Минимальные проверки
        if (intent != null && intent.getAction() != null) {
// Получаем сообщения
            Object[] pdus = (Object[]) intent.getExtras().get("pdus");
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < pdus.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }
            String smsFromPhone = messages[0].getDisplayOriginatingAddress();
            StringBuilder body = new StringBuilder();
            for (int i = 0; i < messages.length; i++) {
                body.append(messages[i].getMessageBody());
            }
            String bodyText = body.toString();

            Intent secondIntent = new Intent(context, ActivityTwo.class);
            secondIntent.putExtra(smsFromPhone, PHONE_NUMBER);
            secondIntent.putExtra(bodyText, TEXT);
            context.startActivity(intent);

        }
    }

}
