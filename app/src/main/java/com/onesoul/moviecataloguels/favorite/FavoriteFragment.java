package com.onesoul.moviecataloguels.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.onesoul.moviecataloguels.R;
import com.onesoul.moviecataloguels.ViewPageAdapter;

import java.util.Objects;

public class FavoriteFragment extends Fragment {

    public FavoriteFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorite, container, false);

        ViewPager viewPager = root.findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPageAdapter(Objects.requireNonNull(getChildFragmentManager())));

        TabLayout tabLayout = root.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        Objects.requireNonNull(tabLayout.getTabAt(0)).setText(getResources().getText(R.string.title_movie));
        Objects.requireNonNull(tabLayout.getTabAt(1)).setText(getResources().getText(R.string.title_tv));

        return root;
    }
}
