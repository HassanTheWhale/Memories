package site.thewhale.memories.fragments.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import site.thewhale.memories.R;
import site.thewhale.memories.other.KtFunctionsKt;
import www.sanju.motiontoast.MotionToast;

public class ForgetFragment extends Fragment {

    public ForgetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forget, container, false);
        ViewPager vp = (ViewPager) getActivity().findViewById(R.id.loginViewPager);


        EditText emailAddress = view.findViewById(R.id.forgetEmail);

        Button forget = view.findViewById(R.id.ForgetBtn);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.sendPasswordResetEmail(emailAddress.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    KtFunctionsKt.motionS(getActivity(), "Done!", "Please, check your email!");
                                }
                            }
                        });
            }
        });


        TextView login = view.findViewById(R.id.loginFragmentBtn2);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(0, true);
            }
        });
        return view;
    }
}