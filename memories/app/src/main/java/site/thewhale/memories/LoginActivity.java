package site.thewhale.memories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.ArrayList;

import site.thewhale.memories.adapters.LoginAdapter;
import site.thewhale.memories.objects.User;
import site.thewhale.memories.other.Lists;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setBackgroundDrawableResource(R.drawable.loading_bg);

        Lists.createList("users");

        ViewPager viewPager = findViewById(R.id.loginViewPager);
        viewPager.setAdapter(new LoginAdapter(getSupportFragmentManager()));
    }
}