package com.example.belikov.sms_sender;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {

    int messageId = 0;

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
            makeNote(context, smsFromPhone, bodyText);


// Это будет работать только на Android ниже 4.4
//            abortBroadcast();
        }
    }

    // Вывод уведомления в строке состояния
    private void makeNote(Context context, String addressFrom, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
