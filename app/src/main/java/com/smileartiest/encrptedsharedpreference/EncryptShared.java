package com.smileartiest.encrptedsharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import androidx.annotation.RequiresApi;
import androidx.security.crypto.MasterKey;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * Encrypted shared preference
 * Created on 12 November 2021
 * Created by Smile artist
 * This Encrypted shared preference need Android Marshmellow version
 * Required API 23 and above
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class EncryptShared {

    private static final String KEY_STORE_ALIAS = "SmileKey";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    /**
     * need for creating master key
     * @return KeyGen Parameter
     */
    private KeyGenParameterSpec createKeyGenParameterSpec() {
        return new KeyGenParameterSpec.Builder(
                KEY_STORE_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT
        ).setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setKeySize(256)
                .build();
    }

    /**
     * Get Master key
     * @param context
     * @param keyGenParameterSpec
     * @return
     * @throws GeneralSecurityException
     * @throws IOException
     */
    private MasterKey getMasterKey(Context context, KeyGenParameterSpec keyGenParameterSpec) throws GeneralSecurityException, IOException {
        return new MasterKey.Builder(context, KEY_STORE_ALIAS)
                .setKeyGenParameterSpec(keyGenParameterSpec)
                .build();
    }

    /**
     * Creating of object for this class
     * Required @param context
     */
    public EncryptShared(Context context) {
        try {
            pref = androidx.security.crypto.EncryptedSharedPreferences.create(
                    context,
                    KEY_STORE_ALIAS,
                    getMasterKey(context, createKeyGenParameterSpec()),
                    androidx.security.crypto.EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    androidx.security.crypto.EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
            editor = pref.edit();
            editor.apply();
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    public void setSingleValue(String type , String key , int val1 , float val2 , boolean val3 ,String val4) {
        switch (type) {
            case Type.TYPE_INT:
                editor.putInt(key,val1);
                break;
            case Type.TYPE_FLOAT:
                editor.putFloat(key,val2);
                break;
            case Type.TYPE_BOOLEAN:
                editor.putBoolean(key,val3);
                break;
            case Type.TYPE_STRING:
                editor.putString(key,val4);
                break;
        }
        editor.apply();
        editor.commit();
    }

    /**
     * Set Default values
     * set Integer , float , boolean , String
     */
    public void setDefaultValues() {
        editor.putInt(Constant.INT_KEY,123456);
        editor.putFloat(Constant.FLOAT_KEY,1.23456f);
        editor.putBoolean(Constant.BOOLEAN_KEY,true);
        editor.putString(Constant.STRING_KEY,"Sample value");
        editor.apply();
        editor.commit();
    }

    /**
     * Clear all data's
     * once we clear use commit
     * commit for final change
     */
    public void clearValues() {
        editor.clear();
        editor.commit();
    }

    public int getIntValue() {
        return pref.getInt(Constant.INT_KEY , 0);
    }

    public float getFloatValue() {
        return pref.getFloat(Constant.FLOAT_KEY , 00);
    }

    public boolean getBooleanValue() {
        return pref.getBoolean(Constant.BOOLEAN_KEY , false);
    }

    public String getStringValue() {
        return pref.getString(Constant.STRING_KEY , "Default");
    }

}
