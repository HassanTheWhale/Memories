package site.thewhale.memories.other;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import site.thewhale.memories.LoginActivity;
import site.thewhale.memories.MainActivity;
import site.thewhale.memories.R;
import site.thewhale.memories.adapters.MainAdapter;
import site.thewhale.memories.objects.User;

public class ShareCodes {
    private static FirebaseAuth mAuth;

    static FirebaseDatabase db = FirebaseDatabase.getInstance("https://memories-b188b-default-rtdb.firebaseio.com/");
    static DatabaseReference dbr = db.getReference();

    public static void register(String email, String password, Activity activity, String name, String username) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email.toString(), password.toString())
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("FB Signup", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            user.sendEmailVerification();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name).build();
                            user.updateProfile(profileUpdates);

                            //String username, String email, String name, String password
                            User userObj = new User(username, email, name, password);
                            dbr.child("users").push().setValue(userObj);
                            Lists.createList("users");
                            KtFunctionsKt.motionS(activity, "Done!", "Please confirm your email!");
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            if (currentUser != null) {
                                FirebaseAuth.getInstance().signOut();
                            }
                            Intent i = new Intent(activity, LoginActivity.class);
                            activity.startActivity(i);
                            activity.finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("FB Signup", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(activity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static void login(String email, String password, Activity activity) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email.toString(), password.toString())
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Query user = dbr.child("users").orderByChild("email").equalTo(email);
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
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Sign in", "signInWithEmail:success");
                            Intent i = new Intent(activity, MainActivity.class);
                            activity.startActivity(i);
                            activity.finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Sign in", "signInWithEmail:failure", task.getException());
                            Toast.makeText(activity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static void logOut(Activity activity) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Sign-out Confirmation");
        builder.setMessage("Are you sure that you want to sign out?!");
        builder.setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(activity, LoginActivity.class);
                activity.startActivity(i);
                activity.finish();
                KtFunctionsKt.motionS(activity, "Good Bye", "See you soon!ً");
            }
        });
        builder.setNegativeButton("No :D", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(activity, "Have Fun!", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static void changeDetails(Activity activity) {
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(activity, LoginActivity.class);
        activity.startActivity(i);
        activity.finish();
        KtFunctionsKt.motionS(activity, "Done!", "Please re-loginً");
    }
    static MainAdapter adapterViewPager;
    public static com.mikepenz.materialdrawer.Drawer createDrawer(Activity activity) {
        ViewPager vpPager = (ViewPager) activity.findViewById(R.id.mainViewPager);
        adapterViewPager = new MainAdapter(((FragmentActivity)activity).getSupportFragmentManager());
        
        vpPager.setAdapter(adapterViewPager);
        String name = "N/A";
        String email = "N/A";
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            name = user.getDisplayName();
            email = user.getEmail();
        } else {
            name = "N/A";
            email = "N/A";
        }

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(activity)
//                .withSelectionListEnabled(false)
//                .withHeaderBackground(R.drawable.header)
//                .addProfiles(
//                        new ProfileDrawerItem().withName(name).withEmail(email).withSelectable(false)
//                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();


        //if you want to update the items at a later time it is recommended to keep it in a variable
        SecondaryDrawerItem item1 = new SecondaryDrawerItem().withIdentifier(1).withTextColor(Color.BLACK).withIconColor(Color.BLACK).withSelectedTextColor(Color.BLACK).withSelectedIconColor(Color.BLACK).withName("Home").withIcon(FontAwesome.Icon.faw_home);
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withSelectedTextColor(Color.BLACK).withSelectedIconColor(Color.DKGRAY).withName("Account Settings").withIcon(FontAwesome.Icon.faw_cogs);
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier(3).withSelectedTextColor(Color.DKGRAY).withSelectedIconColor(Color.DKGRAY).withName("Sign Out").withIcon(FontAwesome.Icon.faw_sign_out_alt);
        SecondaryDrawerItem item5 = new SecondaryDrawerItem().withIdentifier(4).withSelectedTextColor(Color.DKGRAY).withSelectedIconColor(Color.DKGRAY).withName("Search").withIcon(FontAwesome.Icon.faw_search);
        SecondaryDrawerItem item6 = new SecondaryDrawerItem().withIdentifier(5).withSelectedTextColor(Color.GRAY).withSelectedIconColor(Color.DKGRAY).withName("My Account").withIcon(FontAwesome.Icon.faw_user);

        //create the drawer and remember the `Drawer` result object
        com.mikepenz.materialdrawer.Drawer result = new DrawerBuilder()
                .withActivity(activity)
                .withAccountHeader(headerResult)
                .withTranslucentStatusBar(false)
                .addDrawerItems(
                        item1,
                        item5,
                        item6,
                        new DividerDrawerItem(),
                        item2,
                        item3
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.getIdentifier() == 1) {
                            vpPager.setCurrentItem(0, true);
                        } else if (drawerItem.getIdentifier() == 2) {

                        } else if (drawerItem.getIdentifier() == 3) {
                            logOut(activity);
                        } else if (drawerItem.getIdentifier() == 4) {
                            vpPager.setCurrentItem(1, true);

                        } else if (drawerItem.getIdentifier() == 5) {
                            vpPager.setCurrentItem(2, true);

                        }
                        return false;
                    }
                })
                .build();
        return result;
    }


}
