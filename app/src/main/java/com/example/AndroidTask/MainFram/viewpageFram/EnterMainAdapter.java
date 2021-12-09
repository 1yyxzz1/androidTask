package com.example.AndroidTask.MainFram.viewpageFram;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class EnterMainAdapter extends FragmentStateAdapter  {
    private final ArrayList<Fragment> fragmentArrayList;

    public EnterMainAdapter(FragmentActivity fragmentActivity, ArrayList<Fragment> fragmentArrayList) {
        super(fragmentActivity);
        this.fragmentArrayList = fragmentArrayList;
    }

    @Override
    public Fragment createFragment(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentArrayList.size();
    }
}
