package com.erajiezhang.activity;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.erajie.arout.BaseArouteUtil;
import com.erajie.base.BaseActivity;
import com.erajie.global.ARouterPath;
import com.erajie.rxutils.RxLogTool;
import com.erajiezhang.R;
import com.erajiezhang.bean.ReturnBean;
import com.erajiezhang.util.AppManager;
import com.erajiezhang.util.ImageFactory;
import com.hacknife.carouselbanner.Banner;
import com.hacknife.carouselbanner.CoolCarouselBanner;
import com.hacknife.carouselbanner.interfaces.OnCarouselItemChangeListener;
import com.hacknife.carouselbanner.interfaces.OnCarouselItemClickListener;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 主页面
 * @author EraJieZhang
 * @data 2019/5/18
 */
@Route(path = ARouterPath.Mainacitvity, group = ARouterPath.GROUP_MAIN)
public class Mainacitvity extends BaseActivity {

    @BindView(R.id.toarouter)
    Button mToarouter;
    @BindView(R.id.img_show)
    ImageView imgshow;

    @BindView(R.id.ll_main_interceptor)
    LinearLayout mInterceptor;

    @BindView(R.id.main_banner)
    CoolCarouselBanner mMainBanner;

    @BindView(R.id.bar_img_back)
    ImageView barImgBack;
    @BindView(R.id.bar_tv_title)
    TextView barTvTitle;
    @BindView(R.id.bar_tv_menu)
    TextView barTvMenu;
    @BindView(R.id.bar_img_menu)
    ImageView barImgMenu;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //设置黑色状态栏
        QMUIStatusBarHelper.translucent(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(mActivity);
        setBanner();
        setbar();

        //测试多渠道打包
        ApplicationInfo appInfo = null;
        try {
            appInfo = getApplication().getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String app_version = appInfo.metaData.getString("ATMAN_CHANNEL");
        RxLogTool.i("我试试看吧当前是什么渠道：：：：", app_version + "=========");

    }

    /**
     * 设置titleBar状态
     */
    private void setbar() {
        barImgBack.setVisibility(View.GONE);
        barTvMenu.setVisibility(View.GONE);
        barImgMenu.setVisibility(View.GONE);
        barTvTitle.setText("首页");
        barTvTitle.setTextSize(24);
    }

    /**
     * 设置轮播banner
     */
    private void setBanner() {

        Banner.init(new ImageFactory());
        List<String> bannerList = new ArrayList<>();
        mMainBanner.setOnCarouselItemChangeListener(new OnCarouselItemChangeListener() {
            @Override
            public void onItemChange(int position) {
                //                RxLogTool.v("banner 滚动监听：" + position);
            }
        });//滚动监听
        mMainBanner.setOnCarouselItemClickListener(new OnCarouselItemClickListener() {
            @Override
            public void onItemClick(int position, String url) {
                //                RxLogTool.v("banner 点击监听：" + position + ">>>URL = " + url);
            }
        });//点击监听
        bannerList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587302233050&di=d49d307e18a34dade37e1c68f6776aba&imgtype=0&src=http%3A%2F%2Fimg.app178.com%2Ftu%2F201410%2Fyhrgcry32t4.jpg");
        bannerList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587302233050&di=65865d302ada5c0f003396eeb806e8fd&imgtype=0&src=http%3A%2F%2Ffile.mumayi.com%2Fforum%2F201401%2F16%2F231735cfp4046206r4i705.jpg");
        bannerList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587302233050&di=fe3f9e9d9f8e023bd270b245c649a390&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2F201301%2F29%2F125722eh9nj87bq20eq2e8.jpg");
        bannerList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587302233050&di=8a8472620c71fd2e929f93dd851b1b47&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201304%2F25%2F195151szk8umd8or8fmfa5.jpg");

        mMainBanner.initBanner(bannerList);
    }

    @OnClick({R.id.toarouter, R.id.button2, R.id.button3, R.id.button7, R.id.button8, R.id.ll_main_interceptor,
            R.id.ll_main_powerSurvey,R.id.bar_img_back, R.id.bar_tv_title, R.id.bar_tv_menu, R.id.bar_img_menu})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.toarouter:
//                AMapLocationUtil.getInstance(mActivity, new AMapLocationListener() {
//                    @Override
//                    public void onLocationChanged(AMapLocation aMapLocation) {
//
//                        RxLogTool.v("aMapLocation:---->>" + aMapLocation.toString());
//                    }
//                }).srartOneClickLocation(null);
                MMKV mmkv = MMKV.mmkvWithID("MyID");
                mmkv.encode("bool", false);
                boolean bValue1 = mmkv.decodeBool("bool");
                RxLogTool.v("bValue:" +bValue1);
                break;
            case R.id.button2:
//                BaseArouteUtil.returnActivity(ARouterPath.GameContentActivity, ARouterPath.GROUP_SCHULTEGRIL);

                MMKV kv = MMKV.defaultMMKV();

                kv.encode("bool", true);
                boolean bValue = kv.decodeBool("bool");
                RxLogTool.v("bValue:" +bValue);

                kv.encode("int", Integer.MIN_VALUE);
                int iValue = kv.decodeInt("int");
                RxLogTool.v("iValue:" +iValue);

                kv.encode("string", "Hello from mmkv");
                String str = kv.decodeString("string");
                RxLogTool.v("str:" +str);

                RxLogTool.v("bValue:" +bValue);
                break;
            case R.id.button3:
                MMKV kv2 = MMKV.defaultMMKV();
                kv2.removeValueForKey("bool");
                boolean bValue2 = kv2.decodeBool("bool");
                RxLogTool.v("bValue2:" +bValue2);

                MMKV mmkv3 = MMKV.mmkvWithID("MyID");
                boolean bValue13 = mmkv3.decodeBool("bool");
                RxLogTool.v("bValue3:" +bValue13);
//                BaseArouteUtil.returnActivity(ARouterPath.GobangActivity);
                break;
            case R.id.button7:
                BaseArouteUtil.getPostcard(ARouterPath.ThreelevellinkageActivity).
                        withString("rr", "jie").
                        withInt("z", 1).
                        withBoolean("b", true).
                        withParcelable("bean", (Parcelable) new ReturnBean("张世杰", "17090313417", "25")).
                        navigation(mActivity, 123);
                break;
            case R.id.button8:
                BaseArouteUtil.returnActivity(ARouterPath.ShowImageActivity);
                break;
            case R.id.ll_main_interceptor:
                BaseArouteUtil.returnActivity(ARouterPath.GobangActivity);
                break;
            case R.id.ll_main_powerSurvey:
                BaseArouteUtil.returnActivity(ARouterPath.PowerSurveyMainActivity, ARouterPath.GROUP_MAIN);
                break;
                //标题
            case R.id.bar_img_back:

                break;
            case R.id.bar_tv_title:
                break;
            case R.id.bar_tv_menu:

                break;
            case R.id.bar_img_menu:
                break;
            default:
                break;
        }
    }




    // 拦截的回调
    public class LoginNavigationCallbackImpl implements NavigationCallback {

        @Override
        public void onFound(Postcard postcard) {
            //找到了 onFound
            RxLogTool.v("找到了 onFound");
        }

        @Override
        public void onLost(Postcard postcard) {
            //找不到了 onLost
            RxLogTool.v("找不到了 onLost");
        }

        @Override
        public void onArrival(Postcard postcard) {
            //跳转成功了 onArrival
            RxLogTool.v("跳转成功了 onArrival");
        }

        @Override
        public void onInterrupt(Postcard postcard) {
            //被拦截了 onInterrupt
            RxLogTool.v("被拦截了 onInterrupt");
        }
    }


    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                AppManager.getAppManager().AppExit();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
