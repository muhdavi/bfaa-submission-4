package com.onesoul.moviecataloguels.movie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.onesoul.moviecataloguels.R;
import com.onesoul.moviecataloguels.database.DMLHelper;

import java.util.Objects;

public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_MOVIE = "extra_movie";
    private DMLHelper dmlHelper;
    private Movie movie;
    private Button btnFavorite;
    private Boolean isFavorite = false;

    public MovieDetailActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        TextView tvTitle = findViewById(R.id.tv_title_movie);
        TextView tvOverview = findViewById(R.id.tv_overview_movie);
        TextView tvReleaseDate = findViewById(R.id.tv_release_date);
        TextView tvVoteCount = findViewById(R.id.tv_vote_count);
        TextView tvVoteAverage = findViewById(R.id.tv_vote_average);
        ImageView imgPhoto = findViewById(R.id.img_poster_movie);
        btnFavorite = findViewById(R.id.button_favorite);

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Detail Movie");

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        btnFavorite.setOnClickListener(this);

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        tvTitle.setText(movie.getmTitle());
        tvOverview.setText(movie.getmOverview());
        tvReleaseDate.setText(movie.getmReleaseDate());
        tvVoteCount.setText(movie.getmVoteCount());
        tvVoteAverage.setText(movie.getmVoteAverage());
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w780/" + movie.getmPhoto())
                .placeholder(R.drawable.poster_example)
                .transform(new FitCenter())
                .into(imgPhoto);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_favorite)
            if (!isFavorite) {
                movie.setmType("movie");
                dmlHelper = DMLHelper.getInstance(getApplicationContext());
                dmlHelper.open();
                long result = dmlHelper.insertMovie(movie);
                dmlHelper.close();
                if (result > 0) {
                    btnFavorite.setBackground(getResources().getDrawable(R.drawable.ic_favorite_24px));
                    Toast.makeText(getApplicationContext(), "Success " + movie.getmTitle() + "Added to Favorite", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed " + movie.getmTitle() + " Delete Favorite", Toast.LENGTH_SHORT).show();
                }
            } else {
                movie.setmType("movie");
                dmlHelper.open();
                long result = dmlHelper.deleteMovie(movie.getmId());
                dmlHelper.close();
                if (result > 0) {
                    btnFavorite.setBackground(getResources().getDrawable(R.drawable.ic_favorite_border_24dp));
                    Toast.makeText(getApplicationContext(), "Success " + movie.getmTitle() + " Delete from Favorite", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed " + movie.getmTitle() + " Delete Favorite", Toast.LENGTH_SHORT).show();
                }
            }
    }
}
