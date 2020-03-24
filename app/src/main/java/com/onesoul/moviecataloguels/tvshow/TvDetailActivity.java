package com.onesoul.moviecataloguels.tvshow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.onesoul.moviecataloguels.BuildConfig;
import com.onesoul.moviecataloguels.R;
import com.onesoul.moviecataloguels.database.DMLHelper;

import java.util.Objects;

public class TvDetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_TV = "extra_tv";
    private Button btnFavorite;
    private Tvshow tvshow;
    private DMLHelper dmlHelper;
    private boolean isFavorite = false;

    public TvDetailActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);
        TextView tvTitle = findViewById(R.id.tv_title_tv);
        TextView tvOverview = findViewById(R.id.tv_overview_tv);
        TextView tvReleaseDate = findViewById(R.id.tv_release_date);
        TextView tvVoteCount = findViewById(R.id.tv_vote_count);
        TextView tvVoteAverage = findViewById(R.id.tv_vote_average);
        ImageView imgPhoto = findViewById(R.id.img_poster_tv);
        btnFavorite = findViewById(R.id.button_favorite);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail TV Show");
        }

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        btnFavorite.setOnClickListener(this);

        tvshow = getIntent().getParcelableExtra(EXTRA_TV);
        tvTitle.setText(tvshow.getmTitle());
        tvOverview.setText(tvshow.getmOverview());
        tvReleaseDate.setText(tvshow.getmReleaseDate());
        tvVoteCount.setText(tvshow.getmVoteCount());
        tvVoteAverage.setText(tvshow.getmVoteAverage());
        Glide.with(this)
                .load(BuildConfig.URL_IMAGE + tvshow.getmPhoto())
                .placeholder(R.drawable.poster_example)
                .transform(new FitCenter())
                .into(imgPhoto);

        dmlHelper = dmlHelper.getInstance(getApplicationContext());
        if (dmlHelper.CheckData(String.valueOf(tvshow.getmId()))) {
            btnFavorite.setBackground(getResources().getDrawable(R.drawable.ic_favorite_24px));
            isFavorite = true;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_favorite) {
            if (!isFavorite) {
                tvshow.setmType("tvshow");
                dmlHelper = DMLHelper.getInstance(getApplicationContext());
                dmlHelper.open();
                long result = dmlHelper.insertTvshow(tvshow);
                dmlHelper.close();
                if (result > 0) {
                    btnFavorite.setBackground(getResources().getDrawable(R.drawable.ic_favorite_24px));
                    Toast.makeText(getApplicationContext(), tvshow.getmTitle() + " " + R.string.has_add, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), tvshow.getmTitle() + " " + R.string.hasnot_add, Toast.LENGTH_SHORT).show();
                }
            } else {
                tvshow.setmType("tvshow");
                dmlHelper.open();
                long result = dmlHelper.deleteTvshow(tvshow.getmId());
                dmlHelper.close();
                if (result > 0) {
                    btnFavorite.setBackground(getResources().getDrawable(R.drawable.ic_favorite_border_24dp));
                    Toast.makeText(getApplicationContext(), tvshow.getmTitle() + " " + R.string.has_delete, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed " + tvshow.getmTitle() + " " + R.string.hasnot_delete, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
