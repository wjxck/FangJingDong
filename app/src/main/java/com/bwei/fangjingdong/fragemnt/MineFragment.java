package com.bwei.fangjingdong.fragemnt;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.fangjingdong.R;
import com.bwei.fangjingdong.presenter.UserPresenter;
import com.bwei.fangjingdong.utils.ImageUtils;
import com.bwei.fangjingdong.utils.MyApp;
import com.bwei.fangjingdong.view.LoginActivity;
import com.bwei.fangjingdong.view.MyActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.util.Random;

import static android.app.Activity.RESULT_OK;
import static com.bwei.fangjingdong.utils.Util.TAG;

/**
 * 作者：王建勋
 * 时间：2017-12-05 09:55
 * 类的用途：
 */

public class MineFragment extends Fragment implements View.OnClickListener {
    private View view;
    /**
     * 登录/注册>
     */
    private TextView mMyLogin;
    private UserPresenter userPresenter;
    private String uid;
    private ImageView mMyImg;
    private SimpleDraweeView mMyXimg;
    /**
     * sss
     */
    private TextView mMyNickname;
    private String nickname;

    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_mine, null);
        initView(view);
        into();
        info();
        return view;
    }

    private void initView(View view) {
        mMyLogin = (TextView) view.findViewById(R.id.my_login);
        mMyLogin.setOnClickListener(this);
        mMyImg = (ImageView) view.findViewById(R.id.my_img);
        mMyXimg = (SimpleDraweeView) view.findViewById(R.id.my_ximg);
        mMyNickname = (TextView) view.findViewById(R.id.my_nickname);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_login:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void into() {
        boolean isLogin = MyApp.sp.getBoolean("flag", false);
        if (isLogin) {
            Random random = new Random();
            int i = random.nextInt(100);
            nickname = MyApp.sp.getString("name", "");
            mMyNickname.setText("ZZ_" + i);
            mMyXimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("设置头像");
                    String[] items = {"选择本地照片", "拍照"};
                    builder.setNegativeButton("取消", null);
                    builder.setItems(items, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case CHOOSE_PICTURE: // 选择本地照片
                                    Intent openAlbumIntent = new Intent(
                                            Intent.ACTION_GET_CONTENT);
                                    openAlbumIntent.setType("image/*");
                                    startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                                    break;
                                case TAKE_PICTURE: // 拍照
                                    takePicture();
                                    break;
                            }
                        }
                    });
                    builder.create().show();
                }
            });
        }
    }

    public void info() {
        boolean isLogin = MyApp.sp.getBoolean("flag", false);

        Log.e("-----", isLogin + "");
        if (isLogin) {
            mMyLogin.setVisibility(View.GONE);
            mMyImg.setVisibility(View.GONE);
            mMyNickname.setVisibility(View.VISIBLE);
            mMyXimg.setVisibility(View.VISIBLE);
            mMyNickname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), MyActivity.class);
                    intent.putExtra("name", nickname);
                    startActivity(intent);
                }
            });

        } else {
            mMyLogin.setVisibility(View.VISIBLE);
            mMyImg.setVisibility(View.VISIBLE);
            mMyNickname.setVisibility(View.GONE);
            mMyXimg.setVisibility(View.GONE);
            mMyLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
            mMyImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        into();
        info();
    }

    private void takePicture() {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= 23) {
            // 需要申请动态权限
            int check = ContextCompat.checkSelfPermission(getActivity(), permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (check != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        Intent openCameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment
                .getExternalStorageDirectory(), "image.jpg");
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= 24) {
            openCameraIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            tempUri = FileProvider.getUriForFile(getActivity(), "com.lt.uploadpicdemo.fileProvider", file);
        } else {
            tempUri = Uri.fromFile(new File(Environment
                    .getExternalStorageDirectory(), "image.jpg"));
        }
        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Log.d(TAG, "setImageToView:" + photo);
            photo = ImageUtils.toRoundBitmap(photo); // 这个时候的图片已经被处理成圆形的了
            mMyXimg.setImageBitmap(photo);
            uploadPic(photo);
        }
    }

    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了
        String imagePath = ImageUtils.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()));
        Log.e("imagePath", imagePath + "");
        if (imagePath != null) {
            // 拿着imagePath上传了
            // ...
            Log.d(TAG, "imagePath:" + imagePath);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        } else {
            // 没有获取 到权限，从新请求，或者关闭app
            Toast.makeText(getActivity(), "需要存储权限", Toast.LENGTH_SHORT).show();
        }
    }

}
