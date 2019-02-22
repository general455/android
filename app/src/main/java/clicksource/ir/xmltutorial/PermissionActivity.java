package clicksource.ir.xmltutorial;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PermissionActivity extends AppCompatActivity {

    ImageView imgPicture;
    Button btnChoose;
    private static final int REQUEST_OPEN_GALLERY = 1001;
    private static final int PERMISSION_REQUEST = 102;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        imgPicture=(ImageView)findViewById(R.id.img_permission_picture);
        btnChoose=(Button)findViewById(R.id.btn_permission_gallery);

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        final Dialog dialog=new Dialog(PermissionActivity.this);
                        dialog.setContentView(R.layout.dialog);
                        dialog.setCancelable(false);
                        TextView txtOk=dialog.findViewById(R.id.txt_dialog_ok);
                        txtOk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
                            }
                        });
                        dialog.show();
                    }
                } else {
                    createFilePath();
                }


            }
        });
    }

    private void createFilePath(){
        Intent intent =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,REQUEST_OPEN_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_OPEN_GALLERY  && data!=null){
            Uri uri=data.getData();
            String[] info={MediaStore.Images.Media.DATA};
            Cursor cursor=getContentResolver().query(uri,info,null,null,null);
            cursor.moveToFirst();
            int columnIndex=cursor.getColumnIndex(info[0]);
            final String filePath=cursor.getString(columnIndex);
            //Toast.makeText(PermissionActivity.this,filePath,Toast.LENGTH_SHORT).show();
            imgPicture.setImageBitmap(BitmapFactory.decodeFile(filePath));//set image to imageview
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==PERMISSION_REQUEST && grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            //Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show();
            createFilePath();
        }else{
            Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();
        }
    }
}
