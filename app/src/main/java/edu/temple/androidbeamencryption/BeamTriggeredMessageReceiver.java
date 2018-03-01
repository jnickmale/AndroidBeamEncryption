package edu.temple.androidbeamencryption;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.security.PublicKey;

public class BeamTriggeredMessageReceiver extends AppCompatActivity {
    EditText editText;
    PublicKey pubKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beam_triggered_message_receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        editText = (EditText)findViewById(R.id.text1);
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            processIntent(getIntent());
        }

    }

    @Override
    public void onNewIntent(Intent intent) {
        // onResume gets called after this to handle the intent
        setIntent(intent);
    }

    /**
     * Parses the NDEF Message from the intent and prints to the TextView
     */
    void processIntent(Intent intent) {
        if(intent.getData() //is a key
         {
            textView = (TextView) findViewById(R.id.textView);
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES);
            // only one message sent during the beam
            NdefMessage msg = (NdefMessage) rawMsgs[0];
            // record 0 contains the MIME type, record 1 is the AAR, if present
            textView.setText(new String(msg.getRecords()[0].getPayload()));

             //pubKey = ;
        }else{//if it is a message
            //decrypt the message anddisplay
        }
    }
}
