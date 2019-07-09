package com.example.android.e_learner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BranchActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);
        Button cse = (Button) findViewById(R.id.cse);
        cse.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BranchActivity.this,SubjectActivity.class);
                startActivity(intent);
            }
        });

    }
}