package site.thewhale.memories;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import site.thewhale.memories.adapters.PostAdapter;
import site.thewhale.memories.objects.Post;
import site.thewhale.memories.objects.User;
import site.thewhale.memories.other.KtFunctionsKt;
import site.thewhale.memories.other.Lists;

public class PostActivity extends AppCompatActivity {

    FirebaseDatabase db = FirebaseDatabase.getInstance("https://memories-b188b-default-rtdb.firebaseio.com/");
    DatabaseReference dbr = db.getReference();


    private FirebaseAuth mAuth;
    FirebaseStorage storage;
    StorageReference storageReference;

    private Uri imgUri;

    EditText memory;
    ImageView camera;
    ImageView photo;
    ImageView file;
    Button send;

    private Bitmap image;

    private void choosePic() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        getWindow().setBackgroundDrawableResource(R.drawable.main_bg);

        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        memory = findViewById(R.id.newPostMem);
        camera = findViewById(R.id.newPostCamera);
        photo = findViewById(R.id.newPostImage);
        file = findViewById(R.id.newPostFile);
        send = findViewById(R.id.newPostSend);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (memory.getText().toString().isEmpty()) {
                    KtFunctionsKt.motionE(PostActivity.this, "Error", "Your memory can not be empty!");
                    return;
                }

                if (memory.getText().toString().length() < 5) {
                    KtFunctionsKt.motionE(PostActivity.this, "Error", "Your memory can not be so small!");
                    return;
                }

                uploadPicture();
            }
        });
        boolean isVisible = false;
        try {
            String buttonID = "newPostSend";//String name of id
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            View view = findViewById(resID);
            if (isVisible) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
        }catch (Exception e) {
                e.printStackTrace();
        }

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, 1);
            }
        });

        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePic();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imgUri = data.getData();
            photo.setImageURI(imgUri);
            boolean isVisible = true;
            try {
                String buttonID = "newPostSend";//String name of id
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                View view = findViewById(resID);
                if (isVisible) {
                    view.setVisibility(View.VISIBLE);
                } else {
                    view.setVisibility(View.GONE);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private String uploadPicture() {

        final ProgressDialog pd = new ProgressDialog(PostActivity.this);
        pd.setTitle("Uploading!");
        pd.show();

        String randomKey = UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("images2/" + randomKey);
        riversRef.putFile(imgUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
//                        String ID, String img, String username, int likes, String comment
                        Post PostObj = new Post(randomKey, Lists.currentUser.getUsername(), 0, memory.getText().toString());
                        dbr.child("posts").push().setValue(PostObj);
                        Lists.createList("posts");
                        Intent i = new Intent(PostActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        pd.dismiss();
                        Toast.makeText(PostActivity.this, "Failed to upload!", Toast.LENGTH_SHORT).show();
                        // Handle unsuccessful uploads
                        // ...
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progressPercenet = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                pd.setMessage("Progress: " + (int) progressPercenet);
            }
        });

        return randomKey;
    }


}