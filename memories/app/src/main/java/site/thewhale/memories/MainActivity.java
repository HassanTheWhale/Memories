package site.thewhale.memories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialize.color.Material;

import site.thewhale.memories.adapters.MainAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setBackgroundDrawableResource(R.drawable.main_bg);

        ViewPager viewPager = findViewById(R.id.mainViewPager);
        TabLayout tl = findViewById(R.id.mainTab);
        viewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        tl.setupWithViewPager(viewPager);

        tl.getTabAt(0).setIcon(R.drawable.home);
        tl.getTabAt(1).setIcon(R.drawable.loupe);
        tl.getTabAt(2).setIcon(R.drawable.user);
    }
}