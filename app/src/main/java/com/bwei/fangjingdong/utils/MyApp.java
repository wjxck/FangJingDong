package com.bwei.fangjingdong.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.io.File;

/**
 * 作者：王建勋
 * 时间：2017-12-13 10:55
 * 类的用途：
 */

public class MyApp extends Application {
    public static SharedPreferences sp;
    public static SharedPreferences.Editor edit;

    @Override
    public void onCreate() {
        super.onCreate();

        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                .build();
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig)//配置磁盘缓存
                .build();
        /**
         * 初始化Fresco
         */
        Fresco.initialize(this, config);

        sp = getSharedPreferences("user", MODE_PRIVATE);
        edit = sp.edit();
    }

}
