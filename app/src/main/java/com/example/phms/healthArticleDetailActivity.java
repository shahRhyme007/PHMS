package com.example.phms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class healthArticleDetailActivity extends AppCompatActivity {

    TextView tv1;
    ImageView img;
    Button btnBack;
    ScaleGestureDetector scaleGestureDetector;
    float scaleFactor = 1.0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_article_detail);

        tv1 = findViewById(R.id.dietPageName);
        img = findViewById(R.id.image1);
        btnBack = findViewById(R.id.backToDiet);

        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        Intent intent = getIntent();
        tv1.setText(intent.getStringExtra("text1"));
        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            int resId = bundle.getInt("text2");
            img.setImageResource(resId);
        }
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(healthArticleDetailActivity.this, HealthArticle.class));
            }
        });

    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return scaleGestureDetector.onTouchEvent(event);
    }
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector)
        {
            scaleFactor *= detector.getScaleFactor();

            //scaleFactor *=scaleGestureDetector.getScaleFactor();
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 10.0f));
            img.setScaleX(scaleFactor);
            img.setScaleY(scaleFactor);

            float focusX = detector.getFocusX();
            float focusY = detector.getFocusY();

            img.setPivotX(focusX);
            img.setPivotY(focusY);

            return true;
        }
    }
}