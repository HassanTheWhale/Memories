package site.thewhale.memories;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class OtherProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);

        getWindow().setBackgroundDrawableResource(R.drawable.main_bg);
    }
}