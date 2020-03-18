package com.onesoul.moviecataloguels.favorite.tvshow;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onesoul.moviecataloguels.MainViewModel;
import com.onesoul.moviecataloguels.R;
import com.onesoul.moviecataloguels.tvshow.Tvshow;

import java.util.ArrayList;

public class TvFavoriteFragment extends Fragment {
    private RecyclerView recyclerView;
    private TvFavoriteAdapter tvFavoriteAdapter;

    public TvFavoriteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_favorite, container, false);
        recyclerView = view.findViewById(R.id.rv_tv_favorite_fragment);

        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getTvFavorite("tvshow").observe(getViewLifecycleOwner(), getTvFavorite);

        tvFavoriteAdapter = new TvFavoriteAdapter(getActivity());
        tvFavoriteAdapter.notifyDataSetChanged();
        mainViewModel.setTvDatabase("tvshow");
        showRecyclerList();
        recyclerView.setHasFixedSize(true);

        return view;
    }

    private void showRecyclerList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(tvFavoriteAdapter);
    }

    private final Observer<ArrayList<Tvshow>> getTvFavorite = new Observer<ArrayList<Tvshow>>() {
        @Override
        public void onChanged(ArrayList<Tvshow> tvshows) {
            if (tvshows != null) {
                tvFavoriteAdapter.setListTvFavorite(tvshows);
                showRecyclerList();
            }
        }
    };

    public void onResume() {
        super.onResume();
        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.setTvDatabase("tvshow");
    }
}
