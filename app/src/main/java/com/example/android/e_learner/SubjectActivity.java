package com.example.android.e_learner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SubjectActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        Button computer_graphics = (Button) findViewById(R.id.computer_graphics);
        Button computer_networks = (Button) findViewById(R.id.computer_networks);
        computer_graphics.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (MainActivity.id == 1 || MainActivity.id==0) {
                    Intent intent = new Intent(SubjectActivity.this, xyz.class);
                    startActivity(intent);
                }
                else if (MainActivity.id==2)
                {
                    Intent intent=new Intent(SubjectActivity.this,studentFinal.class);
                            startActivity(intent);
                }
            }
        });
        computer_networks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.id == 1 || MainActivity.id==0) {
                    Intent intent = new Intent(SubjectActivity.this,xyz1.class);
                    startActivity(intent);
                }
                else if (MainActivity.id==2)
                {
                    Intent intent=new Intent(SubjectActivity.this,studentFinal.class);
                    startActivity(intent);
                }

            }
        });

    }
}
