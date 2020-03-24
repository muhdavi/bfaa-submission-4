package com.onesoul.moviecataloguels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.onesoul.moviecataloguels.api.ApiClient;
import com.onesoul.moviecataloguels.api.ApiInterface;
import com.onesoul.moviecataloguels.database.DMLHelper;
import com.onesoul.moviecataloguels.movie.Movie;
import com.onesoul.moviecataloguels.tvshow.Tvshow;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {
    String API_KEY = BuildConfig.API_KEY;

    private final MutableLiveData<ArrayList<Movie>> listMovies = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<Movie>> listMoviesFavorite = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<Tvshow>> listTvshows = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<Tvshow>> listTvFavorite = new MutableLiveData<>();

    private ApiInterface apiInterface;
    private DMLHelper dmlHelper;


    public MainViewModel(@NonNull Application application) {
        super(application);
        this.dmlHelper = dmlHelper.getInstance(application);
    }

    public LiveData<ArrayList<Movie>> getMovies() {
        return listMovies;
    }

    public LiveData<ArrayList<Tvshow>> getTvshows() {
        return listTvshows;
    }

    public void setMovie() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        try {
            Call<String> authorized = apiInterface.getDiscoverMovie(API_KEY, "en-US");
            final ArrayList<Movie> listItems = new ArrayList<>();
            authorized.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        try {
                            JSONObject responseObject = new JSONObject(response.body());
                            JSONArray list = responseObject.getJSONArray("results");

                            for (int i = 0; i < list.length(); i++) {
                                JSONObject movie = list.getJSONObject(i);
                                Movie movies = new Movie(movie);
                                listItems.add(movies);
                            }
                            listMovies.postValue(listItems);
                        } catch (Exception e) {
                            Log.d("Exception", e.getMessage());
                        }
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.w("Response Failed", "" + t.getMessage());
                }
            });
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }

    public void setTvshow() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        try {
            Call<String> authorized = apiInterface.getDiscoverTv(API_KEY, "en-US");
            final ArrayList<Tvshow> listItems = new ArrayList<>();
            authorized.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        try {
                            JSONObject responseObject = new JSONObject(response.body());
                            JSONArray list = responseObject.getJSONArray("results");

                            for (int i = 0; i < list.length(); i++) {
                                JSONObject tvshow = list.getJSONObject(i);
                                Tvshow tvshows = new Tvshow(tvshow);
                                listItems.add(tvshows);
                            }
                            listTvshows.postValue(listItems);
                        } catch (Exception e) {
                            Log.d("Exception", e.getMessage());
                        }
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.w("Response Failed", "" + t.getMessage());
                }
            });
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }

    public void setMovieDatabase(String type) {
        ArrayList<Movie> movies = dmlHelper.getListMovieFavorite(type);
        listMoviesFavorite.postValue(movies);
    }

    public LiveData<ArrayList<Movie>> getMoviesFavorite(String type) {
        setMovieDatabase(type);
        return listMoviesFavorite;
    }

    public void setTvDatabase(String type) {
        ArrayList<Tvshow> tvshows = dmlHelper.getListTvFavorite(type);
        listTvFavorite.postValue(tvshows);
    }

    public LiveData<ArrayList<Tvshow>> getTvFavorite(String type) {
        setTvDatabase(type);
        return listTvFavorite;
    }
}
