package site.thewhale.memories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import site.thewhale.memories.objects.User;
import site.thewhale.memories.other.Lists;
import site.thewhale.memories.other.ShareCodes;

public class EditActivity extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance("https://memories-b188b-default-rtdb.firebaseio.com/");
    DatabaseReference dbr = db.getReference();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        mAuth = FirebaseAuth.getInstance();

        getWindow().setBackgroundDrawableResource(R.drawable.main_bg);

        EditText name =findViewById(R.id.EditName);
        EditText username = findViewById(R.id.EditUsername);
        EditText email = findViewById(R.id.EditEmail);
        EditText password = findViewById(R.id.EditPassword);
        EditText passwordConfirm = findViewById(R.id.EditPasswordConfirm);

        String sName = Lists.currentUser.getName();
        String sUsername = Lists.currentUser.getUsername();
        String sEmail = Lists.currentUser.getEmail();

        name.setText(sName);
        username.setText(sUsername);
        email.setText(sEmail);

        Button save = findViewById(R.id.saveEditBtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().isEmpty() || username.getText().toString().isEmpty() || email.getText().toString().isEmpty()) {
                    Toast.makeText(EditActivity.this, "Something is missing!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().equals(passwordConfirm.getText().toString()) && (!password.getText().toString().isEmpty() || !passwordConfirm.getText().toString().isEmpty() )) {
                    if (password.getText().toString().length() < 8) {
                        Toast.makeText(EditActivity.this, "Password must be 8 chars at least!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                        Query user = dbr.child("users").orderByChild("email").equalTo(Lists.currentUser.getEmail());
                    user.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot user : snapshot.getChildren()) {
                                user.getRef().removeValue();
                                User newUser = new User(username.getText().toString(), email.getText().toString(), name.getText().toString(), password.getText().toString());
                                dbr.child("users").push().setValue(newUser);
                                FirebaseUser currentUser = mAuth.getCurrentUser();
                                if (currentUser != null) {
                                    if (!currentUser.getEmail().equals(email.getText().toString()))
                                        currentUser.updateEmail(email.getText().toString());
                                        currentUser.updatePassword(password.getText().toString());
                                        ShareCodes.changeDetails(EditActivity.this);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    return;
                }
                Toast.makeText(EditActivity.this, "Password is not equal!", Toast.LENGTH_SHORT).show();


            }
        });

    }
}