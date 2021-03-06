package site.thewhale.memories.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import site.thewhale.memories.fragments.login.ForgetFragment;
import site.thewhale.memories.fragments.login.LoginFragment;
import site.thewhale.memories.fragments.login.RegisterFragment;

public class LoginAdapter extends FragmentPagerAdapter {
    public LoginAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new LoginFragment();
            case 1:
                return new RegisterFragment();
            case 2:
                return new ForgetFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
