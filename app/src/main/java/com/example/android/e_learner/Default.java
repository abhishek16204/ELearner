package com.example.android.e_learner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Default extends AppCompatActivity {
    // Get a non-default Storage bucket
    Button button_1 = (Button) findViewById(R.id.computer_graphics);
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);
        Intent intent = getIntent();
        final String year = intent.getStringExtra("year");
        final String subject = intent.getStringExtra("subject");
        final StorageReference spaceRef = storageRef.child(year+"/"+subject+"resume_abhishek.pdf");
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    download_file(spaceRef);
                }
                catch (IOException e)
                {
                    Log.d("default","failed download");
                }
            }
        });


    }


    public void download_file(StorageReference islandRef) throws IOException
    {



        final File localFile = File.createTempFile("resumeAbhishek", "pdf");
        final String path = Environment.getExternalStorageDirectory().toString()+"/E-Learner";
        islandRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                File saveFile = new File(path);
                saveFile.mkdirs();
                File file = new File(saveFile, "resumeAbhishek.pdf");
                if (file.exists()) file.delete();
                try
                {
                    copyredirect(localFile, file);
                }
                catch(IOException e)
                {
                    Log.d("saving file","cant dsave the file");
                }
//                File saveFile = new File(localFile,path);



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }
    public static void copyredirect(File localFile,File file) throws IOException {
        try {
            copy(localFile, file);
        }
        catch(IOException e)
        {
            Log.d("saving file","cant save");
        }
    }
    public static void copy(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }
}
