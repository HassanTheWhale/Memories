package site.thewhale.memories.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import site.thewhale.memories.fragments.login.LoginFragment;
import site.thewhale.memories.fragments.login.RegisterFragment;
import site.thewhale.memories.fragments.main.MainFragment;

public class MainAdapter extends FragmentPagerAdapter {
    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new MainFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 1;
    }
}
