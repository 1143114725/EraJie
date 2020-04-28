package com.erajiezhang.amap.location;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.erajie.rxutils.RxLogTool;

/**
 * 高德地图定位工具类
 * @author EraJieZhang
 * @data 2020/4/29
 */
public class AMapLocationUtil {

    /**
     * 唯一单例模式
     * @return
     */
    private static AMapLocationUtil mInstance;
    /**
     * 声明AMapLocationClient类对象
     */
    private static AMapLocationClient mLocationClient = null;

    public synchronized static AMapLocationUtil getInstance(Context context, AMapLocationListener mLocationListener) {

        if (mInstance == null) {
            mInstance = new AMapLocationUtil(context, mLocationListener);
        } else {
            //每次进来都重新设置回调
            setLocationListener(mLocationListener);
        }
        return mInstance;
    }

    public AMapLocationUtil(Context context, AMapLocationListener mLocationListener) {
        //初始化定位
        mLocationClient = new AMapLocationClient(context);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
    }

    /**
     * 设置回调函数
     * @param listener
     */
    public static void setLocationListener(AMapLocationListener listener) {

        if (listener == null) {
            listener = listenerDome;
        }
        if (mLocationClient != null) {
            mLocationClient.setLocationListener(listener);
        } else {
            throw new IllegalArgumentException("era---->> mLocationClient不能为null");
        }

    }


    /**
     * 获取定位连接
     * @return
     */
    public AMapLocationClient getAMapLocatonClient() {

        return mLocationClient;
    }

    /**
     * 发动单次的定位
     */
    public void srartOneClickLocation(AMapLocationClientOption option) {

        if (option == null) {
            //初始化AMapLocationClientOption对象
            option = new AMapLocationClientOption();
            /**
             * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
             * SignIn - 签到
             * Transport - 出行
             * Sport - 运动
             */
            option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        }

        if (null != mLocationClient) {
            mLocationClient.setLocationOption(option);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }
    }

    /**
     * 发动持续定位
     */
    public void startContinuedLocation(AMapLocationClientOption option) {

        if (option == null) {
            //初始化AMapLocationClientOption对象
            option = new AMapLocationClientOption();
            /**
             * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
             * SignIn - 签到
             * Transport - 出行
             * Sport - 运动
             */
            option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.Sport);
        }

        if (null != mLocationClient) {
            mLocationClient.setLocationOption(option);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }
    }


    /**
     * 销毁定位
     */
    public static void onDestroy(){
        if (null != mLocationClient) {
            mLocationClient.onDestroy();
            mInstance = null;
        }
    }

    /**
     * dome-定位回调监听器
     */
    private static AMapLocationListener listenerDome = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {

            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    RxLogTool.v("定位成功：" + aMapLocation.toString());
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    RxLogTool.v("定位失败：" + "location Error, ErrCode:" + aMapLocation.getErrorCode() + ", errInfo:" + aMapLocation.getErrorInfo());
                }
            }
        }
    };

    /**
     * dome-AMapLocationClientOption  核心方法
     */
    private void getAMapLocationClientOption() {

        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();


        /**
         * locationMode是定位类型AMapLocationMode的对象，提供三个枚举常量分别代表三种定位模式。
         *         Hight_Accuracy：高精度定位模式；
         *         Device_Sensors：仅设备定位模式；
         *         Battery_Saving：低功耗定位模式；
         *         返回AMapLocationClientOption类对象
         *        默认高精度模式 Hight_Accuracy
         */
        mLocationOption.setLocationMode((AMapLocationClientOption.AMapLocationMode.Hight_Accuracy));

        /**
         * isLocationCacheEnable是布尔型参数，true表示使用定位缓存策略；false表示不使用。
         * 启用缓存策略，SDK将在设备位置未改变时返回之前相同位置的缓存结果。
         * 默认启用缓存策略 true
         */
        mLocationOption.setLocationCacheEnable(true);


        /**
         *         interval是长整型参数，用于设定连续定位间隔，毫秒级参数。
         *         返回AMapLocationClientOption类对象
         *         例如向方法传1000，连续定位启动后会以1s为间隔时间返回定位结果。
         *         默认 2000
         */
        mLocationOption.setInterval(2000);


        /**
         *         isOnceLocation是布尔型参数，true表示启动单次定位，false表示使用默认的连续定位策略。
         *         返回AMapLocationClientOption类对象
         *         传入true，启动定位，AmapLocationClient将会返回一次定位结果。
         *         false
         */
        mLocationOption.setOnceLocation(true);

        /**
         * isOnceLocationLatest是布尔型参数，true表示获取最近3s内精度最高的一次定位结果；false表示使用默认的连续定位策略。
         *
         *         返回AMapLocationClientOption类对象
         *
         *         出入true，启动定位，AmapLocationClient将会最近3s内精度最高的一次定位结果。
         *
         *         false
         */
        mLocationOption.setOnceLocationLatest(false);


        /**
         * isNeedAddress是布尔型参数，true表示定位返回经纬度同时返回地址描述（定位类型是网络定位的会返回）；false表示不返回地址描述。
         *         返回AMapLocationClientOption类对象
         *         传入true，启动定位，AmapLocationClient返回经纬度的同时会返回地址描述。注意：模式为仅设备模式(Device_Sensors) 时无效。
         *         true
         */
        mLocationOption.setNeedAddress(true);


        /**
         * isMockEnable是布尔型参数，true表示允许外界在定位SDK通过GPS定位时模拟位置，false表示不允许模拟GPS位置。
         *         void
         *         传入true，启动定位，可以通过外界第三方软件对GPS位置进行模拟。注意：模式为低功耗模式(Battery_Saving) 时无效。
         *         false
         */
        mLocationOption.setMockEnable(false);


        /**
         * isWifiActiveScan是布尔型参数，true表示会主动刷新设备wifi模块，获取到最新鲜的wifi列表（wifi新鲜程度决定定位精度）；false表示不主动刷新。
         *         void
         *         传入true，启动定位，AmapLocationClient会驱动设备扫描周边wifi，获取最新的wifi列表（相比设备被动刷新会多消耗一些电量），从而获取更精准的定位结果。注意：
         *         模式为仅设备模式(Device_Sensors) 时无效
         *         false
         */
        mLocationOption.setWifiActiveScan(false);
        /**
         * httpTimeOut是长整型参数，用于设定通过网络定位获取结果的超时时间，毫秒级。
         *         void
         *         传入20000，代表网络定位超时时间为20秒。
         *         30000
         */
        mLocationOption.setHttpTimeOut(30000);

    }

    /**
     * dome-AMapLocation 核心方法
     * @param location
     */
    private void getResult(AMapLocation location){

        /**
         * double 获取纬度
         */
        location.getLatitude();

        /**
         * double 获取经度
         */
        location.getLongitude();

        /**
         * float 获取定位精度 单位:米
         */
        location.getAccuracy();

        /**
         * double 获取海拔高度信息
         */
        location.getAltitude();

        /**
         * float 获取速度 单位：米/秒
         */
        location.getSpeed();

        /**
         * float 获取方向角信息
         */
        location.getBearing();

        /**
         * String 获取室内定位建筑物Id
         */
        location.getBuildingId();

        /**
         * String 获取室内定位楼层
         */
        location.getFloor();

        /**
         * String 获取地址描述  模式为仅设备模式(Device_Sensors)时无此信息
         */
        location.getAddress();

        /**
         * String 获取国家名称 模式为仅设备模式(Device_Sensors)时无此信息
         */
        location.getCountry();

        /**
         *  String 获取省名称 模式为仅设备模式(Device_Sensors)时无此信息
         */
        location.getProvince();

        /**
         * String 获取城市名称 模式为仅设备模式(Device_Sensors)时无此信息
         */
        location.getCity();

        /**
         * String 获取城区名称 模式为仅设备模式(Device_Sensors)时无此信息
         */
        location.getDistrict();

        /**
         *  String 获取街道名称 模式为仅设备模式(Device_Sensors)时无此信息
         */
        location.getStreet();

        /**
         *  String  获取街道门牌号信息 模式为仅设备模式(Device_Sensors)时无此信息
         */
        location.getStreetNum();

        /**
         *  String 获取城市编码信息 模式为仅设备模式(Device_Sensors)时无此信息
         */
        location.getCityCode();

        /**
         * String 获取区域编码信息  模式为仅设备模式(Device_Sensors)时无此信息
         */
        location.getAdCode();

        /**
         *  String 获取当前位置的POI名称 模式为仅设备模式(Device_Sensors)时无此信息
         */
        location.getPoiName();

        /**
         *  String 获取当前位置所处AOI名称 模式为仅设备模式(Device_Sensors)时无此信息
         */
        location.getAoiName();

        /**
         * int 获取GPS当前状态，返回值可参考AMapLocation类提供的常量 模式为仅设备模式(Device_Sensors)时提供此信息
         */
        location.getGpsAccuracyStatus();

        /**
         *  int 获取定位结果来源
         * 0 定位失败 请通过AMapLocation.getErrorCode()方法获取错误码，并参考错误码对照表进行问题排查。
         * 1 GPS定位结果 通过设备GPS定位模块返回的定位结果，精度较高，在10米－100米左右
         * 2 前次定位结果 网络定位请求低于1秒、或两次定位之间设备位置变化非常小时返回，设备位移通过传感器感知。
         * 4 缓存定位结果 返回一段时间前设备在同样的位置缓存下来的网络定位结果
         * 5 Wifi定位结果 属于网络定位，定位精度相对基站定位会更好，定位精度较高，在5米－200米之间。
         * 6 基站定位结果 纯粹依赖移动、联通、电信等移动网络定位，定位精度在500米-5000米之间。
         * 8 离线定位结果
         * 9 最后位置缓存
         */
        location.getLocationType();

        /**
         * String 定位信息描述 用于问题排查
         */
        location.getLocationDetail();

        /**
         * String 定位出现异常的描述
         * 0 定位成功。
         * 可以在定位回调里判断定位返回成功后再进行业务逻辑运算。
         *
         * 1 一些重要参数为空，如context；
         * 请对定位传递的参数进行非空判断。
         *
         * 2 定位失败，由于仅扫描到单个wifi，且没有基站信息。
         * 请重新尝试。
         *
         * 3 获取到的请求参数为空，可能获取过程中出现异常。
         * 请对所连接网络进行全面检查，请求可能被篡改。
         *
         * 4 请求服务器过程中的异常，多为网络情况差，链路不通导致
         * 请检查设备网络是否通畅，检查通过接口设置的网络访问超时时间，建议采用默认的30秒。
         *
         * 5 请求被恶意劫持，定位结果解析失败。
         * 您可以稍后再试，或检查网络链路是否存在异常。
         *
         * 6 定位服务返回定位失败。
         * 请获取errorDetail（通过getLocationDetail()方法获取）信息并参考定位常见问题进行解决。
         *
         * 7 KEY鉴权失败。
         * 请仔细检查key绑定的sha1值与apk签名sha1值是否对应，或通过高频问题查找相关解决办法。
         *
         * 8 Android exception常规错误
         * 请将errordetail（通过getLocationDetail()方法获取）信息通过工单系统反馈给我们。
         *
         * 9 定位初始化时出现异常。
         * 请重新启动定位。
         *
         * 10 定位客户端启动失败。
         * 请检查AndroidManifest.xml文件是否配置了APSService定位服务
         *
         * 11 定位时的基站信息错误。
         * 请检查是否安装SIM卡，设备很有可能连入了伪基站网络。
         *
         * 12 缺少定位权限。
         * 请在设备的设置中开启app的定位权限。
         *
         * 13 定位失败，由于未获得WIFI列表和基站信息，且GPS当前不可用。
         * 建议开启设备的WIFI模块，并将设备中插入一张可以正常工作的SIM卡，或者检查GPS是否开启；如果以上都内容都确认无误，请您检查App是否被授予定位权限。
         *
         * 14 GPS 定位失败，由于设备当前 GPS 状态差。
         * 建议持设备到相对开阔的露天场所再次尝试。
         *
         * 15 定位结果被模拟导致定位失败
         * 如果您希望位置被模拟，请通过setMockEnable(true);方法开启允许位置模拟
         *
         * 16 当前POI检索条件、行政区划检索条件下，无可用地理围栏
         * 建议调整检索条件后重新尝试，例如调整POI关键字，调整POI类型，调整周边搜区域，调整行政区关键字等。
         *
         * 18 定位失败，由于手机WIFI功能被关闭同时设置为飞行模式
         * 建议手机关闭飞行模式，并打开WIFI开关
         *
         * 19 定位失败，由于手机没插sim卡且WIFI功能被关闭
         * 建议手机插上sim卡，打开WIFI开关
         ************************ v1.x版定位SDK错误码对照表
         ************************ 定位SDK v1.x版本（版本号请查询jar包名称或通过SDK提供的getVersion()方法获取）错误码对照表。
         ************************ 由于v1.x版本定位SDK稳定性较差，建议您尽快进行更新。
         *
         * 21 IO 操作异常
         *
         * 22 连接异常
         *
         * 23 连接超时
         *
         * 24 无效的参数
         *
         * 25 空指针异常
         *
         * 26 URL异常
         *
         * 27 未知主机
         *
         * 28 连接服务器失败
         *
         * 29 通信协议解析错误
         *
         * 30 http 连接失败
         *
         * 31 未知的错误
         *
         * 32 Key鉴权验证失败，请检查key绑定的sha1值、包名与apk信息是否对应，或通过高频问题查找相关解决办法。
         *
         * 33 没有获取到设备的定位权限
         *
         * 34 无法获取城市信息
         *
         * 35 当前ip请求次数超过配额
         */
        location.getErrorInfo();

        /**
         *  String 定位出现异常时的编码
         * 0 定位成功。
         * 可以在定位回调里判断定位返回成功后再进行业务逻辑运算。
         *
         * 1 一些重要参数为空，如context；
         * 请对定位传递的参数进行非空判断。
         *
         * 2 定位失败，由于仅扫描到单个wifi，且没有基站信息。
         * 请重新尝试。
         *
         * 3 获取到的请求参数为空，可能获取过程中出现异常。
         * 请对所连接网络进行全面检查，请求可能被篡改。
         *
         * 4 请求服务器过程中的异常，多为网络情况差，链路不通导致
         * 请检查设备网络是否通畅，检查通过接口设置的网络访问超时时间，建议采用默认的30秒。
         *
         * 5 请求被恶意劫持，定位结果解析失败。
         * 您可以稍后再试，或检查网络链路是否存在异常。
         *
         * 6 定位服务返回定位失败。
         * 请获取errorDetail（通过getLocationDetail()方法获取）信息并参考定位常见问题进行解决。
         *
         * 7 KEY鉴权失败。
         * 请仔细检查key绑定的sha1值与apk签名sha1值是否对应，或通过高频问题查找相关解决办法。
         *
         * 8 Android exception常规错误
         * 请将errordetail（通过getLocationDetail()方法获取）信息通过工单系统反馈给我们。
         *
         * 9 定位初始化时出现异常。
         * 请重新启动定位。
         *
         * 10 定位客户端启动失败。
         * 请检查AndroidManifest.xml文件是否配置了APSService定位服务
         *
         * 11 定位时的基站信息错误。
         * 请检查是否安装SIM卡，设备很有可能连入了伪基站网络。
         *
         * 12 缺少定位权限。
         * 请在设备的设置中开启app的定位权限。
         *
         * 13 定位失败，由于未获得WIFI列表和基站信息，且GPS当前不可用。
         * 建议开启设备的WIFI模块，并将设备中插入一张可以正常工作的SIM卡，或者检查GPS是否开启；如果以上都内容都确认无误，请您检查App是否被授予定位权限。
         *
         * 14 GPS 定位失败，由于设备当前 GPS 状态差。
         * 建议持设备到相对开阔的露天场所再次尝试。
         *
         * 15 定位结果被模拟导致定位失败
         * 如果您希望位置被模拟，请通过setMockEnable(true);方法开启允许位置模拟
         *
         * 16 当前POI检索条件、行政区划检索条件下，无可用地理围栏
         * 建议调整检索条件后重新尝试，例如调整POI关键字，调整POI类型，调整周边搜区域，调整行政区关键字等。
         *
         * 18 定位失败，由于手机WIFI功能被关闭同时设置为飞行模式
         * 建议手机关闭飞行模式，并打开WIFI开关
         *
         * 19 定位失败，由于手机没插sim卡且WIFI功能被关闭
         * 建议手机插上sim卡，打开WIFI开关
         *
         ******* v1.x版定位SDK错误码对照表
         ******* 定位SDK v1.x版本（版本号请查询jar包名称或通过SDK提供的getVersion()方法获取）错误码对照表。
         ******* 由于v1.x版本定位SDK稳定性较差，建议您尽快进行更新。
         *
         * 21 IO 操作异常
         *
         * 22 连接异常
         *
         * 23 连接超时
         *
         * 24 无效的参数
         *
         * 25 空指针异常
         *
         * 26 URL异常
         *
         * 27 未知主机
         *
         * 28 连接服务器失败
         *
         * 29 通信协议解析错误
         *
         * 30 http 连接失败
         *
         * 31 未知的错误
         *
         * 32 Key鉴权验证失败，请检查key绑定的sha1值、包名与apk信息是否对应，或通过高频问题查找相关解决办法。
         *
         * 33 没有获取到设备的定位权限
         *
         * 34 无法获取城市信息
         *
         * 35 当前ip请求次数超过配额
         */
        location.getErrorCode();

    }

}
