package site.thewhale.memories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import site.thewhale.memories.adapters.LoginAdapter;
import site.thewhale.memories.objects.User;
import site.thewhale.memories.other.Lists;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    public void onStart() {
        super.onStart();
        Lists.createList("users");

        mAuth = FirebaseAuth.getInstance();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
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