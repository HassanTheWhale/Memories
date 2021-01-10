package site.thewhale.memories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import site.thewhale.memories.adapters.LoginAdapter;
import site.thewhale.memories.objects.User;
import site.thewhale.memories.other.Lists;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    FirebaseDatabase db = FirebaseDatabase.getInstance("https://memories-b188b-default-rtdb.firebaseio.com/");
    DatabaseReference dbr = db.getReference();

    public void onStart() {
        super.onStart();
        Lists.createList("users");

        mAuth = FirebaseAuth.getInstance();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {

            Query user = dbr.child("users").orderByChild("email").equalTo(currentUser.getEmail());
            user.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot user : snapshot.getChildren()) {
                        User setUser = user.getValue(User.class);
                        Lists.currentUser = setUser;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

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