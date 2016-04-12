package sjtu.rfid.transportsys;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import tools.PhotoUtil;

public class PhotoActivity extends Activity {

    Button photoBtn;
    Button pickBtn;
    ImageView photo;

    String lastPicPath = "";

    private static final int TAKE_PICTURE = 0x000001;
    private static final int PICK_PICTURE = 0x000002;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        photoBtn = (Button)findViewById(R.id.btn_take_photo);
        pickBtn = (Button)findViewById(R.id.btn_pick_photo);
        photo = (ImageView)findViewById(R.id.imageView);

        TextView title = (TextView) findViewById(R.id.text_title);
        title.setText("选择照片");
        Button btnBack = (Button) findViewById(R.id.btn_title_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                Uri imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.jpg"));

                //String filePath = getApplicationContext().getFilesDir().getAbsolutePath();
                //lastPicPath = PhotoUtil.getPhotopath(getApplicationContext().getFilesDir().getAbsolutePath());
                //File out = new File(lastPicPath);
                //Uri uri = Uri.fromFile(out);

                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);

                startActivityForResult(openCameraIntent, TAKE_PICTURE);
            }
        });

        pickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhotoIntent,PICK_PICTURE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PICTURE:
                Bitmap bm = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/image.jpg");
                File file = new File(Environment.getExternalStorageDirectory()+"/Image/IMG_"+System.currentTimeMillis()+".jpg");
                if(file.exists())
                    file.delete();
                FileOutputStream out;
                try {
                    out = new FileOutputStream(file);
                    if(bm.compress(Bitmap.CompressFormat.JPEG,90,out))
                    {
                        out.flush();
                        out.close();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
                photo.setImageBitmap(bm);
                break;
            case PICK_PICTURE:
                Uri selectedImage = data.getData();
                String[] filePathColumns = { MediaStore.Images.Media.DATA };
                Cursor cursor = this.getContentResolver().query(selectedImage, filePathColumns, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumns[0]);
                String fileName = cursor.getString(columnIndex);
                cursor.close();
                Bitmap bmp = BitmapFactory.decodeFile(fileName);
                photo.setImageBitmap(bmp);
                break;
        }
    }
}
