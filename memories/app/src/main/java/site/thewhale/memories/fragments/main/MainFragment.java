package site.thewhale.memories.fragments.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import site.thewhale.memories.PostActivity;
import site.thewhale.memories.R;
import site.thewhale.memories.adapters.PostAdapter;
import site.thewhale.memories.objects.Post;
import site.thewhale.memories.other.Lists;

public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        RecyclerView rView = view.findViewById(R.id.mainPostRv22);
        rView.setHasFixedSize(true);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
        rView.setLayoutManager(lm);

        PostAdapter postAdapter = new PostAdapter(Lists.getPostArrayList(), getContext(), 0);
        rView.setAdapter(postAdapter);

        FloatingActionButton fab = view.findViewById(R.id.uploadPic);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent i = new Intent(getActivity(), PostActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}