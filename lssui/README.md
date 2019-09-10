lssui 库是基于LSS SDK 实现的带ui播放器封装；以便开发者能快速实现完整播放器功能；
## 快速接入方式
### 带UI直播播放器
```Java

/**
 * XML中定义，代码展示
 */
<com.vhall.ui.VHLiveUiView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/play_view"
    android:layout_width="match_parent"
    android:layout_height="300dp" />
/**
 * 初始化 设置监听
 */
    liveUiView.init(roomId, accessToken);
        liveUiView.setUiPlayerLister(new VHUiPlayerLister() {
            @Override
            public void onError(int errorCode, int i1, String msg) {
              Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();
            }
        });
        
    @Override
    protected void onDestroy() {
        super.onDestroy();
        liveUiView.release();
    }
/**
 * 设置 最小数值小于这个数值 将只展示播放器不显示其他按钮（默认屏幕的1/2）
 */
    setMinSize(int minSize)
/**
 * 可以获取到播放器 VHLivePlayer
 */
    getPlayerView()
```

### 带UI点播播放器
```Java

/**
 * XML中定义，代码展示
 */
<com.vhall.ui.VHVodPlayUiView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/play_view"
    android:layout_width="match_parent"
    android:layout_height="300dp" />
/**
 * 初始化 设置监听
 */
    playUiView(recordId, accessToken);
         playUiView.setUiPlayerLister(new VHUiPlayerLister() {
            @Override
            public void onError(int errorCode, int i1, String msg) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        });
        
    @Override
    protected void onDestroy() {
        super.onDestroy();
        playUiView.release();
    }
/**
 * 设置 最小数值小于这个数值 将只展示播放器不显示其他按钮（默认屏幕的1/2）
 */
    setMinSize(int minSize)
/**
 * 可以获取到播放器 VHVodPlayer
 */
    getPlayerView()
```


