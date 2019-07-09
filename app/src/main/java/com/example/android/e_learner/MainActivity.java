package com.example.android.e_learner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton admin_button,teacher_button,student_button;
        static int id= -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        admin_button =(ImageButton)findViewById(R.id.admin);
        teacher_button =(ImageButton)findViewById(R.id.teacher);
        student_button =(ImageButton)findViewById(R.id.student);
        admin_button.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                try {
                    id=0;
                    Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        teacher_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    id=1;
                    Intent intent = new Intent(MainActivity.this, LoginActivity_Teacher.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


            student_button.setOnClickListener(new View.OnClickListener()   {
                public void onClick(View v)  {
                    try {
                        id=2;
                        Intent intent=new Intent(MainActivity.this,LoginActivity_Student.class );
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

        });

    }
}
