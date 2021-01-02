package site.thewhale.memories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.bitvale.lightprogress.LightProgress;

public class LoadingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        LightProgress light = findViewById(R.id.light);
        light.on();

        Handler h = new Handler(getMainLooper());
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                    Intent i = new Intent(LoadingActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
            }
        }, 5000);

    }
}