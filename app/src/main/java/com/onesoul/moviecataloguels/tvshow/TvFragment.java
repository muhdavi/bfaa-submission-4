package com.onesoul.moviecataloguels.tvshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.onesoul.moviecataloguels.MainViewModel;
import com.onesoul.moviecataloguels.R;

import java.util.ArrayList;
import java.util.Objects;

public class TvFragment extends Fragment {
    private RecyclerView recyclerView;
    private MainViewModel mainViewModel;
    private TvAdapter tvAdapter;

    public TvFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tv, container, false);
        recyclerView = root.findViewById(R.id.rv_tv_fragment);

        mainViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainViewModel.class);
        mainViewModel.getTvshows().observe(getViewLifecycleOwner(), getTvshow);
        mainViewModel.setTvshow();

        tvAdapter = new TvAdapter(getActivity());
        tvAdapter.notifyDataSetChanged();

        showRecyclerList();
        recyclerView.setHasFixedSize(true);

        return root;
    }

    private void showRecyclerList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(tvAdapter);
    }

    public final Observer<ArrayList<Tvshow>> getTvshow = new Observer<ArrayList<Tvshow>>() {
        @Override
        public void onChanged(ArrayList<Tvshow> tvshows) {
            if (tvshows != null) {
                tvAdapter.setListTv(tvshows);
                showRecyclerList();
            }
        }
    };
}
