package com.example.android.bbmp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseFile;
import com.parse.ParseObject;

import java.io.ByteArrayOutputStream;
public class CameraActivity extends Activity {
    Button btnTakePhoto,submit;
    ImageView imgTakenPhoto;
    private static final int CAM_REQUEST=1313;
    private static final int SELECTED_PICTURE=1;

    ImageView iv;
    ParseObject imgupload = new ParseObject("image");
    ParseFile file;
    String name1,name2;
    private EditText t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_activity);
        Toolbar tool1=(Toolbar)findViewById(R.id.tool_bar1);
        tool1.setTitle("Form");
        t1 = (EditText) findViewById(R.id.editText);
      //  final EditText t2 = (EditText) findViewById(R.id.editText2);
        name1 = t1.getEditableText().toString();
      //  name2 = t2.getEditableText().toString();
        btnTakePhoto = (Button) findViewById(R.id.button1);
        submit=(Button)findViewById(R.id.button);
        imgTakenPhoto = (ImageView) findViewById(R.id.imageview1);
        btnTakePhoto.setOnClickListener(new btnTakePhotoClicker());
        submit.setOnClickListener(new submitopt());
        //pick image from gallery
        iv = (ImageView) findViewById(R.id.imageview1);
    }

    public void btnClick(View v){
        Intent i=new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, SELECTED_PICTURE);
    }




    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected  void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == CAM_REQUEST)
        {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imgTakenPhoto.setImageBitmap(thumbnail);


            //  Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
            byte[] dat=bytes.toByteArray();
            Log.d("done", "done");
            ParseFile file = new ParseFile("android.jpeg", dat);
            // Upload the image into Parse Cloud
            file.saveInBackground();
            Log.d("done", "done");

            // Create a New Class called "ImageUpload" in Parse

            // Create a column named "ImageName" and set the string
            imgupload.put("picname", "AndroidBegin Logo");
            Log.d("done", "done");


            // Create a column named "ImageFile" and insert the image
            imgupload.put("pics", file);
            imgupload.put("desc",t1.getText().toString());
            Log.d("done", "done");
            imgupload.put("loc",(MapsActivity.location).toString());
            imgupload.put("issue",(MainActivity.item1).toString());

            // Create the class and the columns
            imgupload.saveInBackground();
            Log.d("done", "done");

        }
        else if(requestCode==SELECTED_PICTURE && resultCode==RESULT_OK && null!=data) {


            switch (requestCode) {
                case SELECTED_PICTURE:
                    if (resultCode == RESULT_OK) {
                        Uri uri = data.getData();
                        String[] projection = {MediaStore.Images.Media.DATA};

                        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(projection[0]);
                        String filePath = cursor.getString(columnIndex);
                        cursor.close();

                        Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
                        Drawable d = new BitmapDrawable(yourSelectedImage);

                        iv.setBackground(d);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        // Compress image to lower quality scale 1 - 100
                        yourSelectedImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        byte[] image = stream.toByteArray();

                        // Create the ParseFile
                        file = new ParseFile("andgin.png", image);
                        // Upload the image into Parse Cloud
                        file.saveInBackground();

                        // Create a New Class called "ImageUpload" in Parse
                        // ParseObject imgupload = new ParseObject("image");

                        // Create a column named "ImageName" and set the string
                        imgupload.put("picname", "AndroidBegin Logo");

                        // Create a column named "ImageFile" and insert the image
                        imgupload.put("pics", file);
                        imgupload.put("desc",t1.getText().toString());
                        imgupload.put("loc",(MapsActivity.location).toString());
                        imgupload.put("issue",(MainActivity.item1).toString());

                        // Create the class and the columns
                        imgupload.saveInBackground();

                    }
                    break;

                default:
                    break;
            }
        }
        else{
            Toast.makeText(this,"You havent picked any image",Toast.LENGTH_LONG).show();
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    class btnTakePhotoClicker implements Button.OnClickListener
    {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent cameraintent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraintent, CAM_REQUEST);
        }
    }
    class submitopt implements Button.OnClickListener
    {
        @Override
        public void onClick(View v) {
           if( imgTakenPhoto.getDrawable()== null) {
               imgupload.put("desc", t1.getText().toString());
               imgupload.put("loc", (MapsActivity.location).toString());
               imgupload.put("issue", (MainActivity.item1).toString());
               imgupload.saveInBackground();
           }
          Intent intent1=new Intent(CameraActivity.this,FinalActivity.class);
            startActivity(intent1);
        }
    }


}


