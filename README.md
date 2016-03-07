#  SuperMvp
## 使用RxJava+Retrofit+Glide+Material Design使用改进MVP构建应用

###简介
一款遵循**Material Design**风格的新闻，天气，手机号码归属地查询应用
- 的Android一些新的技术的一个合集示例，我也是正在摸索，大家一起学习进步
- API来自网络免费API
- MVP模式（使用ViewDelegate解耦，非常感谢**kymjs**提供的mvp思想，虽然有些地方我有点不太理解，但是决定在这个应用上做个最佳实践）@kymjs(https://github.com/kymjs/TheMVP)
- leakcanary引入(仅仅是引入)
- Retrofit（+RxJava）网络请求
- Glide加载缓存图片
- 使用RecycleView展示新闻列表

###版本
####V0.2
- 整体框架搭建完毕，新闻列表功能已经实现，各功能正常
- 下拉刷新与加载更多的解耦

####V0.3
- 天气预报查询，弹窗提醒
- 修改新闻列表为cardview展示
- 支持新闻查看详情，CollapsingToolbarLayout +　NestedScrollView
- 支持右滑返回

###截图
####新闻

![](http://7xrdzx.com1.z0.glb.clouddn.com/mvp_news.jpg)
####天气预报

![](http://7xrdzx.com1.z0.glb.clouddn.com/mvp_weather.gif)

### About me
* 如果不能运行或者有问题请Email: [lyyx@outlook.com]
* WeiBo:[http://weibo.com/liuyang6]

###引入的第三方库
```
    /*squareup library*/
    compile 'com.squareup.retrofit:retrofit:2.0.0-beta2'
    compile 'com.squareup.retrofit:converter-gson:2.0.0-beta2'
    compile 'com.squareup.retrofit:adapter-rxjava:2.0.0-beta2'
    debugCompile 'com.squareup.leakcanary:leakcanary-android:1.3.1'
    releaseCompile 'com.squareup.leakcanary:leakcanary-android-no-op:1.3.1'
    compile 'com.android.support:support-v13:23.1.1'
    /*rx library*/
    compile 'io.reactivex:rxandroid:1.1.0'
    compile 'io.reactivex:rxjava:1.1.0'
    compile 'com.jakewharton.rxbinding:rxbinding:0.3.0'
    compile 'com.jakewharton.rxbinding:rxbinding-support-v4:0.3.0'
    compile 'com.jakewharton.rxbinding:rxbinding-appcompat-v7:0.3.0'
    compile 'com.jakewharton.rxbinding:rxbinding-recyclerview-v7:0.3.0'
    compile 'com.jakewharton.rxbinding:rxbinding-design:0.3.0'
    /*other library*/
    compile 'com.github.bumptech.glide:glide:3.6.1'
    compile 'com.jakewharton:butterknife:7.0.1'
    compile 'com.github.orhanobut:logger:1.12'
    compile 'com.github.rey5137:material:1.2.2'
    compile 'me.imid.swipebacklayout.lib:library:1.0.0'
  ```
