package com.example.lab_1;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab_1.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements TransactionEvents {

    public native boolean transaction(byte[] trd);
    // Used to load the 'lab_1' library on application startup.
    static {
        System.loadLibrary("lab_1");
        System.loadLibrary("mbedcrypto");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int res = initRng();
        byte[] v = randomBytes(10);

        byte[] key = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        byte[] crypt;
        crypt = encrypt(key, v);
        crypt = decrypt(key, crypt);

        // Example of a call to a native method
        //TextView tv = binding.sampleText;
        //tv.setText(stringFromJNI());
        stringFromJNI();
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback() {
                    @Override
                    public void onActivityResult(Object o) {
                        Class<ActivityResult> c = ActivityResult.class;
                        ActivityResult result = c.cast(o);
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            // обработка результата
                            //String pin = data.getStringExtra("pin");
                            //Toast.makeText(MainActivity.this, pin, Toast.LENGTH_SHORT).show();
                            pin = data.getStringExtra("pin");
                            synchronized (MainActivity.this) {
                                MainActivity.this.notifyAll();
                            }
                        }
                    }
                });
    }
    /**
     * A native method that is implemented by the 'lab_1' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public static native int initRng();

    public static native byte[] randomBytes(int no);

    public static native byte[] encrypt(byte[] key, byte[] data);

    public static native byte[] decrypt(byte[] key, byte[] data);

    public static byte[] stringToHex(String s) {
        byte[] hex;
        try {
            hex = Hex.decodeHex(s.toCharArray());
        } catch (DecoderException ex) {
            hex = null;
        }
        return hex;
    }

    /*public void onButtonClick(View v)
    {
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
    }*/
    /*public void onButtonClick(View v)
    {
        Intent it = new Intent(this, PinpadActivity.class);
        startActivity(it);
    }*/
    public void onButtonClick(View v) {
        //Intent it = new Intent(this, PinpadActivity.class);
        //startActivity(it);
        //activityResultLauncher.launch(it);
       /* new Thread(()-> {
            try {
                byte[] trd = stringToHex("9F0206000000000100");
                boolean ok = transaction(trd);
                runOnUiThread(()-> {
                    Toast.makeText(MainActivity.this, ok ? "ok" : "failed", Toast.LENGTH_SHORT).show();
                });

            } catch (Exception ex) {
                // todo: log error
            }
        }).start();*/
        byte[] trd = stringToHex("9F0206000000000100");
        transaction(trd);
    }

    ActivityResultLauncher activityResultLauncher;

    private String pin;

    @Override
    public String enterPin(int ptc, String amount) {
        pin = new String();
        Intent it = new Intent(MainActivity.this, PinpadActivity.class);
        it.putExtra("ptc", ptc);
        it.putExtra("amount", amount);
        synchronized (MainActivity.this) {
            activityResultLauncher.launch(it);
            try {
                MainActivity.this.wait();
            } catch (Exception ex) {
                //todo: log error
            }
        }
        return pin;


    }
    @Override
    public void transactionResult(boolean result) {
        runOnUiThread(()-> {
            Toast.makeText(MainActivity.this, result ? "ok" : "failed", Toast.LENGTH_SHORT).show();
        });
    }


}
