package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    //Butterknife view bindings
    @BindView(R.id.origin_tv)
    protected TextView originTextView;
    @BindView(R.id.also_known_tv)
    protected TextView akaTextView;
    @BindView(R.id.description_tv)
    protected TextView descriptionTextView;
    @BindView(R.id.ingredients_tv)
    protected TextView ingredientsTextView;

    @BindView(R.id.origin_label_tv)
    protected TextView originLabelTextView;
    @BindView(R.id.aka_label_tv)
    protected TextView akaLabelTextView;
    @BindView(R.id.desc_label_tv)
    protected TextView descLabelTextView;
    @BindView(R.id.ingred_label_tv)
    protected TextView ingredLabelTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        // DEFAULT IMAGE COMES FROM:
        // https://www.flickr.com/photos/re_birf/71582837/;
        // Photo Taken 12/8/2005;
        // Creative Commons Attribution 2.0 Generic
        // Text added via addtext.com

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.drawable.sandwich_not_found)
                .error(R.drawable.sandwich_not_found)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        //TODO Populate these out
        if (sandwich.getPlaceOfOrigin().isEmpty()) {
            originLabelTextView.setVisibility(View.GONE);
            originTextView.setVisibility(View.GONE);
        } else {
            originTextView.setText(sandwich.getPlaceOfOrigin());
        }

        if (sandwich.getAlsoKnownAs().isEmpty()) {
            akaLabelTextView.setVisibility(View.GONE);
            akaTextView.setVisibility(View.GONE);
        } else {
            akaTextView.setText(sandwich.getAlsoKnownAs().toString().replaceAll("[\\[\\]]", ""));
        }

        if (sandwich.getDescription().isEmpty()) {
            descLabelTextView.setVisibility(View.GONE);
            descriptionTextView.setVisibility(View.GONE);
        } else {
            descriptionTextView.setText(sandwich.getDescription());
        }

        if (sandwich.getIngredients().isEmpty()) {
            ingredLabelTextView.setVisibility(View.GONE);
            ingredientsTextView.setVisibility(View.GONE);
        } else {
            ingredientsTextView.setText(sandwich.getIngredients().toString().replaceAll("[\\[\\]]", "").replaceAll("[\\,]", " *"));
        }

        //     TODO ... if null, hide whole section
        //     TODO ... add in default image
        //     TODO ... remove [] from aka and ingredients
        //TODO ... add in comments
        //TODO ... check submission requirements




    }
}
