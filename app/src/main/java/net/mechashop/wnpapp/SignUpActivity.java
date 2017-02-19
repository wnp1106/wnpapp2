package net.mechashop.wnpapp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SignUpActivity extends AppCompatActivity {

    //Explicit
    private EditText nameEditText, userEditText, passEditText;
    private ImageView imageView;
    private Button button;
    private String nameString, userString, passString, pathImageString, nameImageString;
    private Uri uri;
    private boolean aBoolean = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bindwidget
        bindWidget();
        //button Controller
        buttonController();
        //Image Controller
        ImageController();

    } // Main Method

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( resultCode == RESULT_OK ) {
            // Success choose image
            Log.d("18febV1", "Result OK");
            aBoolean = false;
            // Choose image show
            uri = data.getData();
            try {

                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                imageView.setImageBitmap(bitmap);


            } catch (Exception e){
                e.printStackTrace();
            }

            //Find Image Path
            String[] strings = new String[]{MediaStore.Images.Media.DATA}; // get Image Data to Verible
            Cursor cursor = getContentResolver().query(uri, strings, null /**/,
                    null , /* where */
                    null /* order */);
            if ( cursor != null ) {

                cursor.moveToFirst();
                int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                pathImageString = cursor.getString(index);

            } else {
                pathImageString = uri.getPath();
            } // if cursor

            Log.d("19febV1", "Path Image ==>" + pathImageString);

        } // if


    } //On Activity

    private void ImageController() {

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Move to choose image gallery
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*"); // type for all image on mobile
                startActivityForResult(Intent.createChooser(intent, "โปรดเลือก App เลือกรูป"), 1);

            } // onclick imageView
        });
    }// Image Controller

    private void buttonController() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get value form Edit Text
                nameString = nameEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passString = passEditText.getText().toString().trim();
                //Check Space
                if( nameString.length() == 0 ||
                        userString.length() == 0 ||
                        passString.length() == 0 ){
                    Log.d("18febV1","Have Space");
                    MyAlert myAlert = new MyAlert(SignUpActivity.this);
                    myAlert.myDiaog("Have Space","Please Fill All Every Blank");
                } else if ( aBoolean ) {
                    // Non Choose Image
                    MyAlert myAlert = new MyAlert(SignUpActivity.this);
                    myAlert.myDiaog("ยังไม่เลือกรูป","Please Select Your Images");
                } else {



                }


            } //onclick
        });
    } // buttonController

    private void bindWidget() {
        nameEditText = (EditText) findViewById(R.id.editText);
        userEditText = (EditText) findViewById(R.id.editText2);
        passEditText = (EditText) findViewById(R.id.editText3);

        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.button3);

    } //bindWidget


} // Main
