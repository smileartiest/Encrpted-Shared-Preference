# Encrpted-Shared-Preference
For understanding basic encrypted shared preference
Created by Smile Artist Tech Coder.

implementation "androidx.security:security-crypto:1.1.0-alpha02"
 
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
 
 
