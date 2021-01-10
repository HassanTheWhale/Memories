package site.thewhale.memories.fragments.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import site.thewhale.memories.LoginActivity;
import site.thewhale.memories.MainActivity;
import site.thewhale.memories.R;
import site.thewhale.memories.other.ShareCodes;

public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        final ViewPager vp = (ViewPager) getActivity().findViewById(R.id.loginViewPager);

        TextView signUp = view.findViewById(R.id.signUpFragment);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(1, true);
            }
        });

        EditText email = view.findViewById(R.id.loginEmail);
        EditText password = view.findViewById(R.id.loginPassword);

        Button login = view.findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Something is missing!", Toast.LENGTH_SHORT).show();
                    return;
                }
                ShareCodes.login(email.getText().toString(), password.getText().toString(), getActivity());
            }
        });

        TextView forget = view.findViewById(R.id.loginPasswordForget);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(2, true);
            }
        });

        return view;
    }
}