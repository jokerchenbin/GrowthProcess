package com.cst.growthprocess;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.cst.growthprocess.Utils.CrashHandler;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.util.ArrayList;

/**
 * Copyright2012-2016  CST.All Rights Reserved
 *
 * Comments：
 *
 * @author chenb
 *
 *         Time: 2016/11/30 0030
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public class App extends Application {

    public static ArrayList<String> Pro_List = new ArrayList<>();

    @Override
    public void onCreate() {
        initListViewData();
        initImageLoader(getApplicationContext());
        // 将“12345678”替换成您申请的APPID，申请地址：http://open.voicecloud.cn
        SpeechUtility.createUtility(getApplicationContext(), SpeechConstant.APPID + "=5847d535");
        CrashHandler.getInstance().init();
        super.onCreate();
    }


    /**
     * 初始化ListView数据
     */
    private void initListViewData() {
        Pro_List.add("自定义View");
        Pro_List.add("Recyclerview 横竖、九宫格、分割线等使用");
        Pro_List.add("fragment + viewpager 集合使用");
        Pro_List.add("TextView 特殊 点击+下划线 spanable");
        Pro_List.add("EventBus + okhttp + volley");
        Pro_List.add("AIDL + server 后台播放音乐");
        Pro_List.add("html5(暂定)");
        Pro_List.add("聚划算 LuaView ");
        Pro_List.add("AIDL + server 后台播放广播 + IntentServer ");
        Pro_List.add("retrofit + Okhttp网络通信");
        Pro_List.add("pinyin4j的熟悉和使用");
        Pro_List.add("第三方的视频播放控件Vitamio的使用");
        Pro_List.add("第三方科大讯飞语音");
        Pro_List.add("jobscheduler 保证进程存活");
        Pro_List.add("文件的上传和下载");
    }


    public ArrayList<String> getPro_List() {
        return Pro_List;
    }


    /**
     * 初始化ImageLoader
     */
    private void initImageLoader(Context context) {
        //自定义的方式
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800)//内存缓存文件的最大长宽
                .discCacheExtraOptions(480, 800, null)//本地缓存的详细信息
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)//设置当前线程的优先级
                .tasksProcessingOrder(QueueProcessingType.FIFO)//设置任务的处理顺序
                .denyCacheImageMultipleSizesInMemory()//防止内存中图片重复
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))//设置自己的内存缓存大小   2M
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13)//内存缓存百分比
                .discCacheSize(50 * 1024 * 1024)//硬盘缓存大小    50M
                .discCacheFileCount(100)//硬盘缓存文件个数
                .discCacheFileNameGenerator(new Md5FileNameGenerator())//md5加密的方式，或new HashCodeFileNameGenerator()
                .imageDownloader(new BaseImageDownloader(this))
                .imageDecoder(new BaseImageDecoder(true))//图片解码
                .defaultDisplayImageOptions(getOption())//是否使用默认的图片加载配置，null表示不使用
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(configuration);

    }


    public static DisplayImageOptions getOption() {
        DisplayImageOptions option = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.app_load_fail_icon_dp_60)//设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.app_load_fail_icon_dp_60)//设置图片uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.app_load_fail_icon_dp_60)//设置图片加载或解码过程中发生错误显示的图片
                .resetViewBeforeLoading(false)//设置图片在加载前是否重置、复位
//.delayBeforeLoading(1000)//下载前的延迟时间
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在sd卡中
                .considerExifParams(false)//思考可交换的参数
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)//设置图片的显示比例
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                .displayer(new RoundedBitmapDisplayer(40))//设置图片的圆角半径
                .displayer(new FadeInBitmapDisplayer(3000))//设置图片显示的透明度过程的时间
                .build();

        return option;
    }
}
