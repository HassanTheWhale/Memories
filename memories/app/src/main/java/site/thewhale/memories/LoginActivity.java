package site.thewhale.memories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import site.thewhale.memories.adapters.LoginAdapter;

public class LoginActivity extends AppCompatActivity {

    private String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setBackgroundDrawableResource(R.drawable.loading_bg);

        ViewPager viewPager = findViewById(R.id.loginViewPager);
        viewPager.setAdapter(new LoginAdapter(getSupportFragmentManager()));
    }
}