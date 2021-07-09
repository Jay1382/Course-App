package Material;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.webinar.bawp.R;

public class Study4Activity extends AppCompatActivity {


    ImageView imageView1, imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study4);


        imageView1 = (ImageView) findViewById(R.id.gpay);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Study4Activity.this, Video.GpayCourseActivity1.class);
                startActivity(intent);
            }
        });

        imageView2 = (ImageView) findViewById(R.id.gpay1);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Study4Activity.this, Video.GpayCourseActivity1.class);
                startActivity(intent);
            }
        });

    }
}