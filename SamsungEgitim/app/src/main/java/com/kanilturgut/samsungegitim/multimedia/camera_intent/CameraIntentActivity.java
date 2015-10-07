package com.kanilturgut.samsungegitim.multimedia.camera_intent;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.kanilturgut.samsungegitim.R;

import java.io.File;
import java.io.IOException;

public class CameraIntentActivity extends AppCompatActivity {

    private final String TAG = CameraIntentActivity.class.getSimpleName();
    private Context mContext = this;

    private final int REQUEST_IMAGE_CAPTURE = 1001;
    private final int REQUEST_IMAGE_CAPTURE_FULL = 1002;
    private final int REQUEST_GALLERY = 1003;

    private Button bCapture, bCaptureFullSize, bFromGallery;
    private ImageView ivPhoto;
    private static Uri mImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_intent);

        bCapture = (Button) findViewById(R.id.bTakePhoto);
        bCaptureFullSize = (Button) findViewById(R.id.bTakePhotoFullSize);
        bFromGallery = (Button) findViewById(R.id.bFromGallery);

        ivPhoto = (ImageView) findViewById(R.id.ivTakenPhoto);

        bCapture.setOnClickListener(myMultimediaButtonsListener);
        bCaptureFullSize.setOnClickListener(myMultimediaButtonsListener);
        bFromGallery.setOnClickListener(myMultimediaButtonsListener);
    }

    private View.OnClickListener myMultimediaButtonsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == bCapture) {
                takePictureAndGetThumbnail();
            } else if (v == bCaptureFullSize) {
                takePictureAndFullSize();
            } else if (v == bFromGallery) {
                getPhotoFromGallery();
            }
        }
    };


    private void takePictureAndGetThumbnail() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void takePictureAndFullSize() {
        // add write external storage permission

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File photo = null;

        try {
            File tempDir = Environment.getExternalStorageDirectory();
            tempDir = new File(tempDir.getAbsolutePath() + "/.temp/");
            if (!tempDir.exists()) {
                tempDir.mkdir();
            }

            photo = File.createTempFile("picture", "jpg", tempDir);
            photo.delete();

        } catch (Exception e) {
            Log.v(TAG, "Can't create file to take picture!");
            Toast.makeText(mContext, "Please check SD card! Image shot is impossible!", Toast.LENGTH_LONG).show();
        }

        mImageUri = Uri.fromFile(photo);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE_FULL);
    }

    private void getPhotoFromGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_GALLERY);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != RESULT_OK) {
            Toast.makeText(mContext, "RESULT OK dönmedi !!!!", Toast.LENGTH_SHORT).show();
        } else {

            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                Bundle extras = data.getExtras();
                Bitmap bitmap = (Bitmap) extras.get("data");
                ivPhoto.setImageBitmap(bitmap);
            } else if (requestCode == REQUEST_IMAGE_CAPTURE_FULL) {

                Bitmap bitmap = BitmapFactory.decodeFile(mImageUri.getPath());

                ExifInterface exif;
                try {
                    Bitmap rotatedBitmap;
                    exif = new ExifInterface(mImageUri.getPath());

                    int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
                    Matrix matrix = new Matrix();
                    if (orientation == 6) {
                        matrix.postRotate(90);
                    } else if (orientation == 3) {
                        matrix.postRotate(180);
                    } else if (orientation == 8) {
                        matrix.postRotate(270);
                    }

                    if (bitmap != null) {
                        rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

                        ivPhoto.setImageBitmap(rotatedBitmap);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_GALLERY) {

                if (data == null)
                    return;

                Uri selectedImageUri = data.getData();

                String path = getPath(selectedImageUri);

                Bitmap bitmap = BitmapFactory.decodeFile(path);

                try {
                    ExifInterface exif = new ExifInterface(path);

                    int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
                    Log.e("TAG", "orientation is " + orientation);
                    Matrix matrix = new Matrix();
                    if (orientation == 6) {
                        matrix.postRotate(90);
                    } else if (orientation == 3) {
                        matrix.postRotate(180);
                    } else if (orientation == 8) {
                        matrix.postRotate(270);
                    }

                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

                    ivPhoto.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG, "ERROR", e);
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Get a file path from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.
     *
     * @param uri The Uri to query.
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String getPath(final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(mContext, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(mContext, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(mContext, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(mContext, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public String getDataColumn(Context context, Uri uri, String selection,
                                String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}
