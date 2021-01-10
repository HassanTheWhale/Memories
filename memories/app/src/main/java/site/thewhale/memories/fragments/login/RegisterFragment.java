package site.thewhale.memories.fragments.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import site.thewhale.memories.EditActivity;
import site.thewhale.memories.LoadingActivity;
import site.thewhale.memories.LoginActivity;
import site.thewhale.memories.R;
import site.thewhale.memories.other.ShareCodes;

public class RegisterFragment extends Fragment {


    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);


        ViewPager vp = (ViewPager) getActivity().findViewById(R.id.loginViewPager);

        EditText name = view.findViewById(R.id.RegisterName);
        EditText username = view.findViewById(R.id.RegisterUsername);
        EditText email = view.findViewById(R.id.RegisterEmail);
        EditText password = view.findViewById(R.id.RegisterPassword);
        EditText passwordConfirm = view.findViewById(R.id.RegisterPasswordConfirm);

        Button register = view.findViewById(R.id.RegisterBtn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().isEmpty() || username.getText().toString().isEmpty() || email.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Something is missing!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().equals(passwordConfirm.getText().toString()) && (!password.getText().toString().isEmpty() || !passwordConfirm.getText().toString().isEmpty() )) {
                    if (password.getText().toString().length() < 8) {
                        Toast.makeText(getActivity(), "Password must be 8 chars at least!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    ShareCodes.register(email.getText().toString(), password.getText().toString(),
                            getActivity(), name.getText().toString(),
                            username.getText().toString());

                    return;
                }
                Toast.makeText(getActivity(), "Password is not equal!", Toast.LENGTH_SHORT).show();
            }
        });

        TextView login = view.findViewById(R.id.loginFragmentBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(0, true);
            }
        });
        return view;
    }
}