package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class  DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
   private TextView origin,known,description,ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

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

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        description=(TextView)findViewById(R.id.description_tv);
        origin=(TextView)findViewById(R.id.origin_tv);
        known=(TextView)findViewById(R.id.also_known_tv);
        ingredients=(TextView)findViewById(R.id.ingredients_tv);

        android.app.ActionBar ab= getActionBar();
        ab.setTitle(sandwich.getMainName());

        description.setText(sandwich.getDescription());
        origin.setText(sandwich.getPlaceOfOrigin());
        known.setText(model(sandwich.getAlsoKnownAs()));
        ingredients.setText(model(sandwich.getIngredients()));

        }

    public StringBuilder model(List<String> list)
    {
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<list.size();i++)
            builder.append(list.get(i)).append('\n');

        return builder;
    }
}
