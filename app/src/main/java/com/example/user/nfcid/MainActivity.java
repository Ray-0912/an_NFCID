package com.example.user.nfcid;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NfcAdapter nfcAdapter;
    TextView textViewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (nfcAdapter!=null&& nfcAdapter.isEnabled()){
            Toast.makeText(this,"已開啟NFC",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"請開啟NFC功能!",Toast.LENGTH_LONG).show();
        }


        textViewInfo = (TextView) findViewById(R.id.info);

        }



    @Override
    protected void onResume(){
        super.onResume();

        Intent intent = getIntent();
        String action = intent.getAction();

        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
            Toast.makeText(this,
                    "onResume() - ACTION_TAG_DISCOVERED",
                    Toast.LENGTH_SHORT).show();
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            if(tag == null){
                textViewInfo.setText("tag == null");
            }else{
                  String taginfo ="";
                byte[] tagId = tag.getId();
                for(int i=0; i<tagId.length; i++){
                    taginfo += Integer.toHexString(tagId[i] & 0xFF);
                }

                textViewInfo.setText(taginfo);
            }

        }

    }

}
