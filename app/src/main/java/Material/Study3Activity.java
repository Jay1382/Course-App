package Material;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.webinar.bawp.R;

public class Study3Activity extends AppCompatActivity {

    PDFView study3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study3);


        study3=(PDFView) findViewById(R.id.pdfView3);
        study3.fromAsset("part3.pdf").load();

    }
}