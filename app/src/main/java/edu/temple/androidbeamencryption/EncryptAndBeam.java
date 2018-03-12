package edu.temple.androidbeamencryption;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.security.KeyPair;

public class EncryptAndBeam extends AppCompatActivity {
    NfcAdapter nfcAdapter;
    Spinner spin;
    KeyPair keys;
    String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt_and_beam);
        keys = null;
        spin = (Spinner)findViewById(R.id.modeSelect);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mode = view.toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        nfcAdapter.setNdefPushMessageCallback(new MyMessageCallback(this), this, this);
    }







    public KeyPair getKey(){
        return keys;
    }

    public void setKey(KeyPair keys){
        this.keys = keys;
    }

    public String getMode(){
        return mode;
    }
}
