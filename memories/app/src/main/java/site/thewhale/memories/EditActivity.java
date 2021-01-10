package site.thewhale.memories;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
import site.thewhale.memories.adapters.PostAdapter;
import site.thewhale.memories.objects.User;
import site.thewhale.memories.other.Lists;
import site.thewhale.memories.other.ShareCodes;

public class EditActivity extends AppCompatActivity {

    FirebaseDatabase db = FirebaseDatabase.getInstance("https://memories-b188b-default-rtdb.firebaseio.com/");
    DatabaseReference dbr = db.getReference();

    private FirebaseAuth mAuth;
    FirebaseStorage storage;
    StorageReference storageReference;

    CircleImageView img;
    private Uri imgUri;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imgUri = data.getData();
            img.setImageURI(imgUri);
        }
    }

    private String uploadPicture() {

        final ProgressDialog pd = new ProgressDialog(EditActivity.this);
        pd.setTitle("Uploading!");
        pd.show();

        String randomKey = UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("images/"+randomKey);
        riversRef.putFile(imgUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        pd.dismiss();
                        Toast.makeText(EditActivity.this, "Failed to upload!", Toast.LENGTH_SHORT).show();
                        // Handle unsuccessful uploads
                        // ...
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progressPercenet = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                pd.setMessage("Progress: "+ (int) progressPercenet);
            }
        });

        return randomKey;
    }

    private void choosePic() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        mAuth = FirebaseAuth.getInstance();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        img = findViewById(R.id.Edit_profile_image);

        getWindow().setBackgroundDrawableResource(R.drawable.main_bg);

        EditText name = findViewById(R.id.EditName);
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

        storageReference.child("images/"+Lists.currentUser.getImg()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(EditActivity.this).load(uri).into(img);
            }
           }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePic();
            }
        });

        Button save = findViewById(R.id.saveEditBtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().isEmpty() || username.getText().toString().isEmpty() || email.getText().toString().isEmpty()) {
                    Toast.makeText(EditActivity.this, "Something is missing!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().equals(passwordConfirm.getText().toString()) && (!password.getText().toString().isEmpty() || !passwordConfirm.getText().toString().isEmpty())) {
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

                                FirebaseUser currentUser = mAuth.getCurrentUser();
                                if (currentUser != null) {
                                    if (!currentUser.getEmail().equals(email.getText().toString()))
                                        currentUser.updateEmail(email.getText().toString());
                                    currentUser.updatePassword(password.getText().toString());

                                }
                                newUser.setImg(uploadPicture());
                                Lists.currentUser = newUser;
                                dbr.child("users").push().setValue(newUser);
                                ShareCodes.changeDetails(EditActivity.this);
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