package com.example.android.e_learner;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class xyz extends AppCompatActivity
{
    FirebaseStorage storage = FirebaseStorage.getInstance();
    FirebaseDatabase database =FirebaseDatabase.getInstance();
    Button upload;
    TextView notification;
    Uri pdfuri;
    ProgressDialog progressDialog;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_default);
    //Button branch_button=(Button)findViewById(R.id.cse);
                   // branch_button.setOnClickListener(new View.OnClickListener()   {
       // public void onClick(View v)  {
        //    try {
                //Intent intent=new Intent(YearActivity.this,BranchActivity.class);
                //startActivity(intent);
                //setContentView(R.layout.activity_subject);
                //Button subject_button=(Button)findViewById(R.id.computer_graphics);
              //  subject_button.setOnClickListener(new View.OnClickListener()   {
                   // public void onClick(View v)  {
                      //  try {
                                                /*Intent intent=new Intent(YearActivity.this,Default.class);
                                                intent.putExtra("year","3rd year");
                                                intent.putExtra("subject","computer graphics");
                                                startActivity(intent);*/
                          //  setContentView(R.layout.activity_default);
                            Button selectFile=findViewById(R.id.select_file);
                            notification=findViewById(R.id.notification);
                            upload=findViewById(R.id.upload);
                            selectFile.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(ContextCompat.checkSelfPermission(xyz.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
                                    {

                                        selectPdf();
                                    }
                                    else
                                    {
                                        ActivityCompat.requestPermissions(xyz.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
                                    }
                                }
                            });






//        Log.d("year activity","set on click for branch");
//
//        subject_button.setOnClickListener(new View.OnClickListener()   {
//            public void onClick(View v)  {
//                try {
//                    Intent intent=new Intent(YearActivity.this,Default.class);
//                    intent.putExtra("year","3rd year");
//                    intent.putExtra("subject","computer graphics");
//                    startActivity(intent);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        Log.d("year activity","set on click for subj");
        upload.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        if(pdfuri!=null) {
        uploadNew(pdfuri);
        }
        else
        {
        Toast.makeText(xyz.this,"Select a File",Toast.LENGTH_SHORT).show();
        }

        }
        });
        }
private void uploadFile(Uri pdfuri)
        { progressDialog= new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading Fiile");
        progressDialog.setProgress(0);
        progressDialog.show();
        final String fileName=System.currentTimeMillis()+".pdf";
        final String fileName1=System.currentTimeMillis()+"";
        StorageReference stoageReference=storage.getReference();
        stoageReference.child("Computer Graphics").child(fileName).putFile(pdfuri)
        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
@Override
public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
        String url=taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
        DatabaseReference reference=database.getReference();
        reference.child(fileName1).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
@Override
public void onComplete(@NonNull Task<Void> task) {
        if(task.isSuccessful())
        {
            progressDialog.dismiss();
        Toast.makeText(xyz.this,"File Successfully uploaded",Toast.LENGTH_SHORT).show();
        }
        else
        Toast.makeText(xyz.this,"File not  Successfully uploaded",Toast.LENGTH_SHORT).show();


        }
        });
        }
        }).addOnFailureListener(new OnFailureListener() {
@Override
public void onFailure(@NonNull Exception e) {
        Toast.makeText(xyz.this,"File  Not Successfully uploaded",Toast.LENGTH_SHORT).show();


        }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
@Override
public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

        int currentProgress=(int)(100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
        progressDialog.setProgress(currentProgress);
        }
        });

        }

    private void uploadNew(Uri pdfuri){

        progressDialog= new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading Fiile");
        progressDialog.setProgress(0);
        progressDialog.show();

        StorageReference  storeNew = FirebaseStorage.getInstance().getReference();

//        final StorageReference ref=storeNew.getReference("tt");
        final String randomName = UUID.randomUUID().toString();
        final StorageReference  ref=storeNew.child("Computer_Graphics").child(randomName+ ".pdf");
//        ref.child(pdfuri.toString()).putFile(pdfuri);

        UploadTask uploadTask = ref.putFile(pdfuri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        String url=task.getResult().toString();
                            addData(randomName,url);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    private void addData(String s, String url) {

        DatabaseReference reference=database.getReference();
        reference.child("Computer_Graphics").child(s).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    progressDialog.dismiss();
                    Toast.makeText(xyz.this,"File Successfully uploaded",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(xyz.this,"File not  Successfully uploaded",Toast.LENGTH_SHORT).show();


            }
        });
    }


    @Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==9 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
        selectPdf();
        }else
        {
        Toast.makeText(xyz.this,"Please provide permissions",Toast.LENGTH_SHORT).show();
        }
        }

//    public void move_to_subject()
//    {
//        setContentView(R.layout.activity_subject);
//    }
private void selectPdf()
        {
        Intent intent =new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,86);
        }

@Override
protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==86 &&  resultCode==RESULT_OK && data!=null)
        {
        pdfuri=data.getData();
        notification.setText("File is selected ");


        }
        else
        {
        Toast.makeText(this,"Please select the file",Toast.LENGTH_SHORT).show();

        }
        }
        }


