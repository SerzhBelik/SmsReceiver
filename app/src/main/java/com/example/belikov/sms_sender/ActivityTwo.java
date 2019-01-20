package com.example.belikov.sms_sender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityTwo extends Activity {

    private String phoneNumber;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        TextView sendedFrom = findViewById(R.id.sender);
        TextView text = findViewById(R.id.text);
        Button answer = findViewById(R.id.answer);

        intent = getIntent();

        phoneNumber = intent.getStringExtra(SmsReceiver.PHONE_NUMBER);
        sendedFrom.setText(phoneNumber);
        text.setText(intent.getStringExtra(SmsReceiver.TEXT));

        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondIntent = new Intent(ActivityTwo.this , MainActivity.class);
                secondIntent.putExtra(phoneNumber, SmsReceiver.PHONE_NUMBER);
                startActivity(intent);
            }
        });


    }
}
