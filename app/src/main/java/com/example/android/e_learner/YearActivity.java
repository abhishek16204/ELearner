package com.example.android.e_learner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class YearActivity extends Activity {
   /* FirebaseStorage storage = FirebaseStorage.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    Uri pdfuri;
    Button upload;
    ProgressDialog progressDialog;*/

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year);
        Button year_button = (Button) findViewById(R.id.third_year);


       year_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(YearActivity.this, BranchActivity.class);

               startActivity(intent);
           }
       });
    }}

                   /* setContentView(R.layout.activity_branch);
                    Button branch_button = (Button) findViewById(R.id.cse);
                    branch_button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            try {
                                //Intent intent=new Intent(YearActivity.this,BranchActivity.class);
                                //startActivity(intent);
                                setContentView(R.layout.activity_subject);
                                Button subject_button = (Button) findViewById(R.id.computer_graphics);
                                subject_button.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        try {
                                            setContentView(R.layout.activity_default);
                                           // Intent intent = new Intent(YearActivity.this, xyz.class);
                                               /* intent.putExtra("year","3rd year");
                                                intent.putExtra("subject","computer graphics");
                                                startActivity(intent);*/
                // setContentView(R.layout.activity_default);
                                                /* Button selectFile=findViewById(R.id.select_file);
                                                  upload=findViewById(R.id.upload);
                                                 selectFile.setOnClickListener(new View.OnClickListener() {
                                                     @Override
                                                     public void onClick(View v) {
                                                         if(ContextCompat.checkSelfPermission(YearActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
                                                         {

                                                                 selectPdf();
                                                         }
                                                         else
                                                         {
                                                             ActivityCompat.requestPermissions(YearActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
                                                         }
                                                     }
                                                 });


                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                Log.d("year activity", "set on click for subj");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
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
                            if (pdfuri != null) {
                                uploadFile(pdfuri);
                            } else {
                                Toast.makeText(YearActivity.this, "Select a File", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
                private void uploadFile (Uri pdfuri)
                {
                    progressDialog = new ProgressDialog(this);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.setTitle("Uploading Fiile");
                    progressDialog.setProgress(0);
                    progressDialog.show();
                    final String fileName = System.currentTimeMillis() + "";
                    StorageReference stoageReference = storage.getReference();
                    stoageReference.child("Computer Graphics").child(fileName).putFile(pdfuri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    String url = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                                    DatabaseReference reference = database.getReference();
                                    reference.child(fileName).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(YearActivity.this, "File Successfully uploaded", Toast.LENGTH_SHORT).show();
                                            } else
                                                Toast.makeText(YearActivity.this, "File not  Successfully uploaded", Toast.LENGTH_SHORT).show();


                                        }
                                    });
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(YearActivity.this, "File  Not Successfully uploaded", Toast.LENGTH_SHORT).show();


                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            int currentProgress = (int) (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressDialog.setProgress(currentProgress);
                        }
                    });

                }


                @Override
                public void onRequestPermissionsResult ( int requestCode,
                @NonNull String[] permissions, @NonNull int[] grantResults){
                    if (requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        selectPdf();
                    } else {
                        Toast.makeText(YearActivity.this, "Please provide permissions", Toast.LENGTH_SHORT).show();
                    }
                }

                //    public void move_to_subject()
//    {
//        setContentView(R.layout.activity_subject);
//    }
                private void selectPdf ()
                {
                    Intent intent = new Intent();
                    intent.setType("application/pdf");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, 86);
                }

                @Override
                protected void onActivityResult ( int requestCode, int resultCode,  Intent data){
                    if (requestCode == 86 && resultCode == RESULT_OK && data != null) {
                        pdfuri = data.getData();

                    } else {
                        Toast.makeText(this, "Please select the file", Toast.LENGTH_SHORT).show();

                    }*/
           /* }
        });
    }
}*/




