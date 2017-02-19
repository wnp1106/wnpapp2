package net.mechashop.wnpapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;

/**
 * Created by Noie on 2/18/2017.
 */

public class MyAlert {

    // Explicit
    private Context context;

    public MyAlert(Context context) {
        this.context = context;
    }

    public void myDiaog(String strTitle, String strMassage){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false); // button Undo on mobile Disable
        builder.setIcon(R.drawable.nobita48);
        builder.setTitle(strTitle);
        builder.setMessage(strMassage);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();


    }// myDiaog

}// Main Class
