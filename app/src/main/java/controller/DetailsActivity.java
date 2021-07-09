package controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.palette.graphics.Palette;

import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.webinar.bawp.R;

import java.util.ArrayList;

import Material.BbookP1;
import Video.GpayCourseActivity1;
import data.CourseData;
import model.Course;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private int courseId;
    private Course course;
    private ImageView courseImageView;
    private TextView courseTitle;
    private InputMethodManager inputManager;
    private LinearLayout revealView;
    private EditText commentEditText;

    private Button b1;
    private Button b2;
    private boolean isEditTextVisible = false;
    
    private FloatingActionButton button;
    private ArrayList<String> comments;
    private ArrayAdapter<String> commentsAdapter;
    private ListView commentsListview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        b1 = (Button) findViewById(R.id.play_video);
        b2 = (Button) findViewById(R.id.open_material);

        setUpUI();
        setUpAdapter();
        loadCourse();
        getPhoto();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailsActivity.this, GpayCourseActivity1.class);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailsActivity.this, BbookP1.class);
                startActivity(intent);
            }
        });



//        Toast.makeText(this, "course_id: " + courseId, Toast.LENGTH_SHORT)
//                .show();
    }

    private void setUpAdapter() {
        commentsListview = (ListView)  findViewById(R.id.detailsCommentsListView);
        comments = new ArrayList<>();
        commentsAdapter = new ArrayAdapter<>(this, R.layout.comment_row, comments);
        commentsListview.setAdapter(commentsAdapter);
    }

    private void loadCourse() {
        course = new CourseData().courseList().get(getIntent().getExtras().getInt("course_id"));
        courseImageView.setImageResource(course.getImageResourseId(this));
        courseTitle.setText(course.getCourseName());
    }

    private void setUpUI() {
        inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        courseImageView = (ImageView) findViewById(R.id.detailsCourseImage);
        courseTitle = (TextView) findViewById(R.id.detailsCourseTitle);

        revealView = (LinearLayout) findViewById(R.id.revealView);
        revealView.setVisibility(View.INVISIBLE);
        isEditTextVisible = false;

        button = (FloatingActionButton) findViewById(R.id.detailsAddButton);
        button.setOnClickListener(this);

        commentEditText = (EditText) findViewById(R.id.detailsComments);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.detailsAddButton:
                if (! isEditTextVisible) {
                    revealEditText(revealView);
                    commentEditText.requestFocus();
                    inputManager.showSoftInput(commentEditText, InputMethodManager.SHOW_IMPLICIT);

//                    button.setImageResource(R.drawable.icn_morph);
//                    Animatable animatable = (Animatable) button.getDrawable();
//                    animatable.start();
                }else {
                    hideEditText(revealView);
                    inputManager.hideSoftInputFromWindow(commentEditText.getWindowToken(), 0);
                    addToComment(commentEditText.getText().toString().trim());
                    commentEditText.setText("");
//                    button.setImageResource(R.drawable.icn_morph_reverse);
//                    Animatable animatable = (Animatable) button.getDrawable();
//                    animatable.start();
                }
                    break;
        }
    }

    private void getPhoto() {
        Bitmap photo = BitmapFactory.decodeResource(getResources(), course.getImageResourseId(this));
        colorized(photo);
    }

    private void colorized(Bitmap photo) {
        Palette palette = Palette.from(photo).generate();
        applyPalette(palette);
    }

    private void applyPalette(Palette palette) {
        getWindow().setBackgroundDrawable(new ColorDrawable(palette.getDarkMutedColor(0)));
        courseTitle.setBackgroundColor(palette.getMutedColor(0));
        revealView.setBackgroundColor(palette.getLightMutedColor(0));
    }

    private void addToComment(String comment) {
        comments.add(comment);

    }

    private void hideEditText(final LinearLayout revealView) {
        int cx = revealView.getRight() - 30;
        int cy = revealView.getBottom() - 60;

        int initialRadius = revealView.getWidth();
        Animator anim = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(revealView,
                    cx, cy, initialRadius, 0f);
        }
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                revealView.setVisibility(View.INVISIBLE);
            }
        });
        isEditTextVisible = false;
        anim.start();


    }

    private void revealEditText(LinearLayout revealView) {
        int cx = revealView.getRight() - 30;
        int cy = revealView.getBottom() - 60;

        int finalRadius = Math.max(revealView.getWidth(), revealView.getHeight());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Animator anim = ViewAnimationUtils.createCircularReveal(revealView,
                    cx, cy, 0f, finalRadius);

            revealView.setVisibility(View.VISIBLE);
            isEditTextVisible = true;
            anim.start();

        }
    }
}