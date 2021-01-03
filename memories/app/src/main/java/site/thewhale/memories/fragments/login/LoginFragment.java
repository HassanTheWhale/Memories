package site.thewhale.memories.fragments.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import site.thewhale.memories.LoginActivity;
import site.thewhale.memories.MainActivity;
import site.thewhale.memories.R;

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

        Button login = view.findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent((LoginActivity)getActivity(), MainActivity.class);
                startActivity(i);
//                (LoginActivity)getActivity().finish();
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