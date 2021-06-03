# VHYun_SDK_LSS_Android
微吼云 直播、点播 Android SDK  

集成和调用方式，参见官方文档：http://yun.vhall.com/document/document/index

# 版本更新记录

## 版本v2.3.3 跟新时间2021.06.03
* 已知bug修复
* 实时字幕
* 埋点优化

## 版本v2.3.2 跟新时间2021.05.13
* 优化sdk
* 增加版本展示



## 版本v2.3.1 跟新时间2021.03.09
* 优化sdk
* 本地依赖修改为maven依赖


## 版本v2.3.0 跟新时间2020.12.09
* 新增点播打点数据回调
* 新增点播字幕功能
* 支持视频投屏

## 版本V2.1.0 跟新时间2020.05.12
* 修复发直播横屏发起，竖屏预览镜像问题；
* 修复三方推流花屏问题；
* 修复点播文档部分指令不执行问题；
* 优化点播文档自适应缩放；
* 优化播放体验；
* 优化点播自适应适配；

## 版本V2.0.1 更新时间2019.09.10
* 新增播放器皮肤UI模块  

## 版本V2.0.0 更新时间2019.08.21
* 创建独立模块demo；
* 优化录屏直播，修改VHScreenRecordService 位置（com.vhall.lss.push.VHScreenRecordService）；
* 优化美颜直播，修改美颜设置(发直播默认开启美颜，美颜等级1）;  

```Java
    videoCapture.setFilterEnable(true);
    videoCapture.setBeautyLevel(level);
```
* 
