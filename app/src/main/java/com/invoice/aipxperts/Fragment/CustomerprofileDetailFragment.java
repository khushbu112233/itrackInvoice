package com.invoice.aipxperts.Fragment;


import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.models.Image;
import com.invoice.aipxperts.Activity.AddCompanyProfileActivity;
import com.invoice.aipxperts.R;
import com.invoice.aipxperts.Utils.ConnectionDetector;
import com.invoice.aipxperts.Utils.Constants;
import com.invoice.aipxperts.Widget.TextView_Bold;
import com.invoice.aipxperts.Widget.TextView_Regular;
import com.invoice.aipxperts.databinding.CustomerProfileDetailFragmentLayoutBinding;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by aipxperts-ubuntu-01 on 30/10/17.
 */

public class CustomerprofileDetailFragment extends Fragment {
    CustomerProfileDetailFragmentLayoutBinding mBinding;
    Context context;
    View rootView;
    public Dialog mDialogRowBoardList;
    private Window mWindow;
    private WindowManager.LayoutParams mLayoutParams;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final int MY_REQUEST_CODE = 100;
    public static Uri fileUri;
    int REQUEST_CAMERA = 200;
    int SELECT_FILE = 201;
    ConnectionDetector connectionDetector;
    File storeDirectory;
    String storePath = "";
    private static final String IMAGE_DIRECTORY_NAME = "Camera";
    String encodedString = "";
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.customer_profile_detail_fragment_layout, container, false);
        rootView = mBinding.getRoot();
        context = getActivity();
        connectionDetector = new ConnectionDetector(context);
        Constants.setTextWatcher(context, mBinding.edtClientName, mBinding.txtClientName);
        Constants.setTextWatcher(context, mBinding.edtAddress1, mBinding.txtCompanyAddress);
        Constants.setTextWatcher(context, mBinding.edtAddress2, mBinding.txtCompanyAddress2);
        Constants.setTextWatcher(context, mBinding.edtCity, mBinding.txtCity);
        Constants.setTextWatcher(context, mBinding.edtState, mBinding.txtState);
        Constants.setTextWatcher(context, mBinding.edtZip, mBinding.txtZip);
        Constants.setTextWatcher(context, mBinding.edtCountry, mBinding.txtCountry);

        mBinding.imgEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog_camera();

            }
        });
        mBinding.txtDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AddCompanyProfileActivity)context).SetCompanyId(UUID.randomUUID().toString());
                ((AddCompanyProfileActivity)context).SetCompanyName(mBinding.edtClientName.getText().toString());
                ((AddCompanyProfileActivity)context).SetCompanyAddress1(mBinding.edtAddress1.getText().toString());
                ((AddCompanyProfileActivity)context).SetCompanyAddress2(mBinding.edtAddress2.getText().toString());
                ((AddCompanyProfileActivity)context).SetCompanyCity(mBinding.edtCity.getText().toString());
                ((AddCompanyProfileActivity)context).SetCompanyState(mBinding.edtState.getText().toString());
                ((AddCompanyProfileActivity)context).SetCompanyPincode(mBinding.edtZip.getText().toString());
                ((AddCompanyProfileActivity)context).SetCountry(mBinding.edtCountry.getText().toString());
                ((AddCompanyProfileActivity)context).SetImage_Path(storePath);

                CompanyDetailMainFragment.mBinding.viewPager1.setCurrentItem(1);


            }
        });


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    /**
     * image function
     */

    public void showInputDialog_camera() {

        mDialogRowBoardList = new Dialog(context);
        mDialogRowBoardList.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialogRowBoardList.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mDialogRowBoardList.setContentView(R.layout.photo_dialog_layout);
        mDialogRowBoardList.setCancelable(false);
        mWindow = mDialogRowBoardList.getWindow();
        mLayoutParams = mWindow.getAttributes();
        mLayoutParams.gravity = Gravity.BOTTOM;
        mWindow.setAttributes(mLayoutParams);
        final TextView_Regular txtgallery = (TextView_Regular) mDialogRowBoardList.findViewById(R.id.txtgallery);
        final TextView_Regular txtcamera = (TextView_Regular) mDialogRowBoardList.findViewById(R.id.txtcamera);
        final TextView_Bold cancel = (TextView_Bold) mDialogRowBoardList.findViewById(R.id.cancel);

        txtgallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, AlbumSelectActivity.class);
                intent.putExtra(Constants.INTENT_EXTRA_LIMIT, Constants.DEFAULT_LIMIT);
                getActivity().startActivityForResult(intent, SELECT_FILE);
               /* Intent intent = new Intent(context,GalleryActivity.class);
                startActivity(intent);*/
                mDialogRowBoardList.dismiss();
                mBinding.imgEvent.setEnabled(true);
            }
        });

        txtcamera.setOnClickListener(new View.OnClickListener() {

                                         @Override
                                         public void onClick(View v) {


                                             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                 if (getActivity().checkSelfPermission(Manifest.permission.CAMERA)
                                                         != PackageManager.PERMISSION_GRANTED) {
                                                     getActivity().requestPermissions(new String[]{Manifest.permission.CAMERA},
                                                             MY_REQUEST_CODE);
                                                 } else {
                                                     Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                                     fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                                                     Log.v("fileUri", fileUri + "--");
                                                     intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                                                     // start the image capture Intent
                                                     getActivity().startActivityForResult(intent, REQUEST_CAMERA);
                                                     mDialogRowBoardList.dismiss();
                                                 }
                                             } else {
                                                 Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                                 fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                                                 Log.v("fileUri", fileUri + "--");
                                                 intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                                                 // start the image capture Intent
                                                 getActivity().startActivityForResult(intent, REQUEST_CAMERA);
                                                 mDialogRowBoardList.dismiss();
                                             }
                                             mBinding.imgEvent.setEnabled(true);

                                         }
                                     }
        );


        cancel.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                mDialogRowBoardList.dismiss();
                mBinding.imgEvent.setEnabled(true);
            }
        });

        mDialogRowBoardList.show();
    }


    public void onActivityGallery(Image data)
    {
        String selectedImagePath=data.path;

        storePath=data.path;

        Glide.with(context).load(data.path).centerCrop().into(mBinding.imgEvent);
        try {
            String timeStamp = "/" + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + ".jpg";

            File file = new File(storeDirectory + timeStamp);
            if (!file.exists()) {
                file.createNewFile();
            }

            Log.e("LLL", "Des : " + storeDirectory + timeStamp + "Source : " + selectedImagePath);
            copyFile(new File(selectedImagePath), new File(storeDirectory + timeStamp));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //selectedImagePath = selectedImagePath.substring(5, selectedImagePath.length());
        //  encodedString = Base64.encodeToString(byteArray, Base64.DEFAULT);
        mBinding.imgCamera.setVisibility(View.GONE);

    }
    public void onActivity(Intent data)
    {
        Bitmap thumbnail=null;
        Bitmap rotatedBitmap = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();

            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                    options);

            if (bitmap.getWidth() > bitmap.getHeight()) {
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0,bitmap.getWidth(),bitmap.getHeight(), matrix, true);
            }
            else {
                rotatedBitmap=bitmap;
            }

            // rotatedBitmap=bitmap;
            Log.v("rotatedBitmap",rotatedBitmap+"--");

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            rotatedBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            Bitmap b = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            Bitmap bmpnew=Bitmap.createScaledBitmap(b, 500, 500, false);

            encodedString = Base64.encodeToString(byteArray, Base64.DEFAULT);

            //  mBinding.imgEvent.setImageBitmap(bmpnew);
            mBinding.imgCamera.setVisibility(View.GONE);
            storePath=fileUri.getPath();
            Glide.with(context).load(new File(fileUri.getPath())).centerCrop().into(mBinding.imgEvent);

            // file1= bitmapToFile(bmpnew);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createFolder() {

        storeDirectory = new File("/sdcard/Android/data/" + getActivity().getPackageName() + "/");
        if (!storeDirectory.exists()) {
            storeDirectory.mkdirs();
        }
    }
    private void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        storePath = destFile.getAbsolutePath();
        FileChannel source = null;
        FileChannel destination = null;
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        if (destination != null && source != null) {
            destination.transferFrom(source, 0, source.size());
        }
        if (source != null) {
            source.close();
        }
        if (destination != null) {
            destination.close();
        }
    }
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }

}
