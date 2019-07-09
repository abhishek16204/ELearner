package com.example.android.e_learner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class studentFinal extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_final);
        Button fetchFiles=findViewById(R.id.fetchFiles);
        fetchFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    startActivity(new Intent(studentFinal.this,MyRecyclerViewActivity.class));
            }
        });

    }
}
