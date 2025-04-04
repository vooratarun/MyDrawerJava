package com.example.mydrawerjava;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mydrawerjava.ui.dashboard.DashboardFragment;
import com.example.mydrawerjava.ui.home.HomeFragment;
import com.example.mydrawerjava.ui.notifications.NotificationsFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new DashboardFragment();
            case 2:
                return new NotificationsFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Total number of fragments
    }
}
