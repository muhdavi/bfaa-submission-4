package com.onesoul.moviecataloguels;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.onesoul.moviecataloguels.favorite.movie.MovieFavoriteFragment;
import com.onesoul.moviecataloguels.tvshow.TvFavoriteFragment;

public class ViewPageAdapter extends FragmentPagerAdapter {
    private final Fragment[] tabFragments;

    public ViewPageAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        tabFragments = new Fragment[] {
                new MovieFavoriteFragment(),
                new TvFavoriteFragment()
        };
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return tabFragments[position];
    }

    @Override
    public int getCount() {
        return tabFragments.length;
    }
}
