package site.thewhale.memories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialize.color.Material;

import site.thewhale.memories.adapters.MainAdapter;
import site.thewhale.memories.other.ShareCodes;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setBackgroundDrawableResource(R.drawable.loading_bg);

        ViewPager viewPager = findViewById(R.id.mainViewPager);
        TabLayout tl = findViewById(R.id.mainTab);
        viewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        tl.setupWithViewPager(viewPager);

        Toolbar mActionBarToolbar;
        mActionBarToolbar = (Toolbar) findViewById(R.id.mainToolbar);
        mActionBarToolbar.setTitle("Memories");
        setSupportActionBar(mActionBarToolbar);

        Drawer result = ShareCodes.createDrawer( MainActivity.this);
        result.setToolbar(MainActivity.this, mActionBarToolbar);

        tl.getTabAt(0).setIcon(R.drawable.home);
        tl.getTabAt(1).setIcon(R.drawable.loupe);
        tl.getTabAt(2).setIcon(R.drawable.user);
    }
}