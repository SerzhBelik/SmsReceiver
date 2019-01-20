package com.example.belikov.sms_sender;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText msg = findViewById(R.id.msg);
        final EditText address = findViewById(R.id.address);
        final Button send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumb = address.getText().toString();
                String message = msg.getText().toString();
                sendSMS(phoneNumb, message);
            }
        });

    }

    private void sendSMS(String phoneNumber, String message)
    {
        PendingIntent pi= PendingIntent.getActivity(this,0,
                new Intent(this, MainActivity.class),0);
        SmsManager sms= SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber,null, message,pi,null);
    }
}
