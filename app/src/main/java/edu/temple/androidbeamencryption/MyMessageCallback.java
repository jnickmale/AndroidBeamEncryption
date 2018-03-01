package edu.temple.androidbeamencryption;

import android.app.Activity;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.widget.EditText;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import static android.nfc.NdefRecord.createMime;

/**
 * Created by nmale_000 on 2/26/2018.
 */

public class MyMessageCallback implements NfcAdapter.CreateNdefMessageCallback {
    Activity activity;

    public MyMessageCallback(Activity activity) {
        this.activity = activity;
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent nfcEvent) {
        //code to create key and put it in an ndefmessage
        KeyPairGenerator keyGen;
        KeyPair keys = null;
        String pubKey = "";
        String secretMessage = "";
        NdefMessage msg = null;

        EncryptAndBeam act = (EncryptAndBeam)activity;


        if((act).getMode() == "keys") {
            if ((act).getKey() == null) {
                try {
                    keyGen = KeyPairGenerator.getInstance("RSA");
                    keys = keyGen.generateKeyPair();
                    (act).setKey(keys);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            } else {
                keys = (act).getKey();
            }

            if (keys != null) {
                pubKey = keys.getPublic().toString();
                pubKey = "-----BEGIN PUBLIC KEY-----\n".concat(pubKey);
                pubKey = pubKey.concat("-----END PUBLIC KEY-----");
                try {
                    msg = new NdefMessage(pubKey.getBytes());
                } catch (FormatException e) {
                    e.printStackTrace();
                }
                msg = new NdefMessage(
                        new NdefRecord[]{createMime(
                                "AndroidBeamEncryption/vnd.com.android.beam/key", pubKey.getBytes())
                        });
            }
        }else if((act).getMode() == "message"){
            secretMessage = ((EditText)act.findViewById(R.id.messageSend)).getText().toString();

        }

        return msg;
    }
}