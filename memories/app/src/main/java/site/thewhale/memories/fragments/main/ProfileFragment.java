package site.thewhale.memories.fragments.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import site.thewhale.memories.EditActivity;
import site.thewhale.memories.LoginActivity;
import site.thewhale.memories.MainActivity;
import site.thewhale.memories.R;
import site.thewhale.memories.ZeroPointActivity;
import site.thewhale.memories.adapters.PostAdapter;
import site.thewhale.memories.other.Lists;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        RecyclerView rView = view.findViewById(R.id.recyclerViewProfile);
        rView.setHasFixedSize(true);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(view.getContext());
        rView.setLayoutManager(lm);

        PostAdapter postAdapter = new PostAdapter(Lists.getPostArrayList(), view.getContext(), 1);
        rView.setAdapter(postAdapter);

        Button edit = view.findViewById(R.id.profileEdit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent((MainActivity) (getActivity()), EditActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}