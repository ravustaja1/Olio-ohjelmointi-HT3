package fi.solehmainen.harjoitustyo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import fi.solehmainen.harjoitustyo.fragments.FightFragment;
import fi.solehmainen.harjoitustyo.fragments.HomeFragment;
import fi.solehmainen.harjoitustyo.fragments.TrainFragment;

public class TabPagerAdapter extends FragmentStateAdapter {

    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new TrainFragment();
            case 2:
                return new FightFragment();
            default:
                return new HomeFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
