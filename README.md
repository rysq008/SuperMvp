# SuperMvp
## 使用RxJava+Retrofit+Glide+Material Design使用改进MVP构建应用

###简介
一款遵循**Material Design**风格的新闻，天气，手机号码归属地查询应用
- API来自网络免费API
- MVP模式（使用ViewDelegate解耦）
- leakcanary引入
- Retrofit（+RxJava）网络请求
- Glide加载缓存图片
- 使用RecycleView展示新闻列表


# About me
* Email:[lyyx@outlook.com]（lyyx@outlook.com）

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
  ```
