package site.thewhale.memories.fragments.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.style.AlignmentSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import site.thewhale.memories.R;
import site.thewhale.memories.adapters.PostAdapter;
import site.thewhale.memories.fragments.objects.Post;
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

        Lists list = new Lists();
        list.createList("posts");

        RecyclerView rView = view.findViewById(R.id.mainPostRv);
        rView.setHasFixedSize(true);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(view.getContext());
        rView.setLayoutManager(lm);

        PostAdapter postAdapter = new PostAdapter(list.getPostArrayList(), view.getContext());
        rView.setAdapter(postAdapter);
        return view;
    }
}