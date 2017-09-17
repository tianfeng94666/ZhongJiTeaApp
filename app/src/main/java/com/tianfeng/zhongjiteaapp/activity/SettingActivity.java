package com.tianfeng.zhongjiteaapp.activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.popupwindow.ImageInitiDialog;
import com.tianfeng.zhongjiteaapp.utils.BimpUtils;
import com.tianfeng.zhongjiteaapp.viewutils.CircleImageView;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class SettingActivity extends BaseActivity {
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.iv_head_photo)
    CircleImageView ivHeadPhoto;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tv_username)
    TextView tvUsername;
    @Bind(R.id.rl_name)
    RelativeLayout rlName;
    @Bind(R.id.tv_re)
    ImageView tvRe;
    @Bind(R.id.rl_reset_password)
    RelativeLayout rlResetPassword;
    @Bind(R.id.ll_setting)
    LinearLayout llSetting;

    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_PHOTO = 2;
    private static final int CROP_PHOTO = 3;

    private Uri mImageCaptureUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_head_photo, R.id.rl_name, R.id.rl_reset_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head_photo:
                initPopupwindow();
                break;
            case R.id.rl_name:
                break;
            case R.id.rl_reset_password:
                break;
        }
    }

    private void initPopupwindow() {
        setCameraPermission();
        ImageInitiDialog imageInitiDialog = new ImageInitiDialog(SettingActivity.this);
        imageInitiDialog.showDialog(llSetting);
        imageInitiDialog.setOnImageSelectListener(new ImageInitiDialog.OnImageSelectListener() {
            @Override
            public void onCamera() {
                        /*拍    照*/
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        /*给拍的照片随机取名*/
                mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "tmp_avatar_"
                        + String.valueOf(System.currentTimeMillis())
                        + ".jpg"));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, PICK_FROM_CAMERA);
                //设置切换动画，从右边进入，左边退出
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }

            @Override
            public void onPhotos() {
                        /*相册中选取*/
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_FROM_PHOTO);
                //设置切换动画，从右边进入，左边退出
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        });
    }

    private static String[] PERMISSIONS_CAMERA_AND_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};

    public void setCameraPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            int storagePermission = this.checkSelfPermission(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int cameraPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
            if (storagePermission != PackageManager.PERMISSION_GRANTED || cameraPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_CAMERA_AND_STORAGE,
                        0x007);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;

        switch (requestCode) {
            case PICK_FROM_CAMERA:
                doCrop();
                break;
            case PICK_FROM_PHOTO:
                mImageCaptureUri = data.getData();
                doCrop();
                break;
            case CROP_PHOTO:
                Bundle extras = data.getExtras();
                Bitmap photo = null;
                if (extras != null) {
                    photo = extras.getParcelable("data");
                }
                File f = new File(getRealPathFromURI(mImageCaptureUri));
                if (f.exists() && photo != null) {
                      /*图片路径压缩*/
                    final File file = new File(BimpUtils.getInstace().savebitmap(photo));
//                    submitToServer(file);
                } else {
                    Toast.makeText(this, "the file doesnt exist", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
    public class CropOption {
        public CharSequence title;
        public Drawable icon;
        public Intent appIntent;
    }


    private void doCrop() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image/*");
        List<ResolveInfo> list = getPackageManager().queryIntentActivities(intent, 0);
        int size = list.size();
        if (size == 0) {
            Toast.makeText(this, "Can not find image crop app",
                    Toast.LENGTH_SHORT).show();
            return;
        } else {
            intent.setData(mImageCaptureUri);
            intent.putExtra("outputX", 200);
            intent.putExtra("outputY", 200);
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("scale", true);
            intent.putExtra("return-data", true);
            if (size == 1) {
                Intent i = new Intent(intent);
                ResolveInfo res = list.get(0);
                i.setComponent(new ComponentName(res.activityInfo.packageName,
                        res.activityInfo.name));
                startActivityForResult(i, CROP_PHOTO);
                //设置切换动画，从右边进入，左边退出
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        }
    }

}
