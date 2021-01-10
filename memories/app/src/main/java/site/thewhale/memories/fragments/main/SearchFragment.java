package site.thewhale.memories.fragments.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import site.thewhale.memories.EditActivity;
import site.thewhale.memories.MainActivity;
import site.thewhale.memories.R;
import site.thewhale.memories.adapters.PostAdapter;
import site.thewhale.memories.adapters.UserAdapter;
import site.thewhale.memories.objects.User;
import site.thewhale.memories.other.Lists;

public class SearchFragment extends Fragment {

    public SearchFragment() {
        // Required empty public constructor
    }
    UserAdapter userAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);



        final RecyclerView rView = view.findViewById(R.id.searchRV);
        rView.setHasFixedSize(true);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(view.getContext());
        rView.setLayoutManager(lm);

        Lists.createList("users");

        userAdapter = new UserAdapter(Lists.getUserArrayList(), view.getContext());
        rView.setAdapter(userAdapter);

        final EditText user = view.findViewById(R.id.searchUser);

        ImageView search = view.findViewById(R.id.searchBtn);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lists.createList("users");
                userAdapter.notifyDataSetChanged();
            }
        });

        user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
                userAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    private void filter(String s) {
        ArrayList<User> filterUsers = new ArrayList<User>();
        for (User user : Lists.getUserArrayList()) {
            if (user.getUsername().toLowerCase().contains(s.toLowerCase())) {
                filterUsers.add(user);
            }
        }
        userAdapter.filterList(filterUsers);
    }
}