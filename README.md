# VHYun_SDK_LSS_Android
微吼云 直播、点播 Android SDK  

集成和调用方式，参见官方文档：http://yun.vhall.com/document/document/index

# 版本更新记录

## 版本V2.0.0 更新时间2019.08.21
* 创建独立模块demo；
* 优化录屏直播，修改VHScreenRecordService 位置（com.vhall.lss.push.VHScreenRecordService）；
* 优化美颜直播，修改美颜设置(发直播默认开启美颜，美颜等级1）;  

```Java
    videoCapture.setFilterEnable(true);
    videoCapture.setBeautyLevel(level);
```
* 