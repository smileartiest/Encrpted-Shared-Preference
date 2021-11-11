package com.smileartiest.encrptedsharedpreference;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends AppCompatActivity {

    TextView intText,floatTxt,booleanTxt,stringTxt;
    EncryptShared encryptShared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        encryptShared = new EncryptShared(getApplicationContext());
        initialise();
    }

    @Override
    protected void onStart() {
        super.onStart();
        showDetails();
    }

    @Override
    protected void onResume() {
        super.onResume();

        findViewById(R.id.main_set_values).setOnClickListener(v -> {
            encryptShared.setDefaultValues();
            showDetails();
        });

        findViewById(R.id.main_clear_values).setOnClickListener(v -> {
            encryptShared.clearValues();
            showDetails();
        });

        /**
         * Type as integer , float , boolean , string
         * Key as integer , float , boolean , string
         * val 1 for integer
         * val 2 for float
         * val 3 for boolean
         * val 4 for string
         */
        findViewById(R.id.main_set_int).setOnClickListener( v -> {
            encryptShared.setSingleValue(Type.TYPE_INT , Constant.INT_KEY , 8344 , 0f , false , null);
            showDetails();
        });

        findViewById(R.id.main_set_float).setOnClickListener( v -> {
            encryptShared.setSingleValue(Type.TYPE_FLOAT , Constant.FLOAT_KEY , 0 , 1.23f , false , null);
            showDetails();
        });

        findViewById(R.id.main_set_boolean).setOnClickListener( v -> {
            encryptShared.setSingleValue(Type.TYPE_BOOLEAN , Constant.BOOLEAN_KEY , 0 , 0f , true , null);
            showDetails();
        });

        findViewById(R.id.main_set_string).setOnClickListener( v -> {
            encryptShared.setSingleValue(Type.TYPE_STRING , Constant.STRING_KEY , 0 , 0 , false , "Smile Artist");
            showDetails();
        });

    }

    private void showDetails() {
        intText.setText(String.valueOf(encryptShared.getIntValue()));
        floatTxt.setText(String.valueOf(encryptShared.getFloatValue()));
        booleanTxt.setText(String.valueOf(encryptShared.getBooleanValue()));
        stringTxt.setText(encryptShared.getStringValue());
    }

    private void initialise() {
        intText = findViewById(R.id.main_int_txt);
        floatTxt = findViewById(R.id.main_float_txt);
        booleanTxt = findViewById(R.id.main_boolean_txt);
        stringTxt = findViewById(R.id.main_string_txt);
    }

}