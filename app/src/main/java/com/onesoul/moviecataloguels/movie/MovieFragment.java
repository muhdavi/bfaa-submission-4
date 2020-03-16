package com.onesoul.moviecataloguels.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.onesoul.moviecataloguels.MainViewModel;
import com.onesoul.moviecataloguels.R;

import java.util.ArrayList;
import java.util.Objects;

public class MovieFragment extends Fragment {
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_movie, container, false);
        recyclerView = root.findViewById(R.id.rv_movie_fragment);

        MainViewModel mainViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainViewModel.class);
        mainViewModel.getMovies().observe(getViewLifecycleOwner(), getMovie);
        mainViewModel.setMovie();

        movieAdapter = new MovieAdapter(getActivity());
        movieAdapter.notifyDataSetChanged();

        showRecyclerList();
        recyclerView.setHasFixedSize(true);

        return root;
    }

    private void showRecyclerList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(movieAdapter);
    }

    private final Observer<ArrayList<Movie>> getMovie = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movies) {
            if (movies != null) {
                movieAdapter.setListMovie(movies);
                showRecyclerList();
            }
        }
    };


}
