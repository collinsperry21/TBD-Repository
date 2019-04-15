package com.example.student.charactersheet5e;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BeginCAC extends AppCompatActivity {

    private Button navigate_next_CAC;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin_cac);

        navigate_next_CAC = findViewById(R.id.NextCAC01);
        navigate_next_CAC.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(BeginCAC.this, BeginCAC.class);
                startActivity(intent);
            }
        });
    }
}
