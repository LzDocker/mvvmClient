# mvvmClient


1. 项目架构

模块之间解耦使用arouter,每个模块可以自己单独运行起来。代码解藕使用dagger2。

使用mvvm架构，整体严格按照Architecture Component规范，统一数据源


2. 用到的第三方库

            "arouter"                 : 'com.alibaba:arouter-api:1.2.4',
            "arouter-compiler"        : 'com.alibaba:arouter-compiler:1.1.4',
            "multidex"                : 'com.android.support:multidex:1.0.2',
            "lifecycle-runtime"       : 'android.arch.lifecycle:runtime:1.0.0-rc1',
            "lifecycle-extensions"    : 'android.arch.lifecycle:extensions:1.0.0-rc1',
            "lifecycle-compiler"      : 'android.arch.lifecycle:compiler:1.0.0-rc1',
            "common-java8"            : 'android.arch.lifecycle:common-java8:1.0.0-rc1',
            "room-runtime"            : 'android.arch.persistence.room:runtime:1.0.0-rc1',
            "room-compiler"           : 'android.arch.persistence.room:compiler:1.0.0-rc1',
            "timber"                  : 'com.jakewharton.timber:timber:4.5.1',
            "glide"                   : 'com.github.bumptech.glide:glide:3.7.0',
            "design"                  : 'com.android.support:design:26.1.0',
            "recyclerview"            : 'com.android.support:recyclerview-v7:26.1.0',
            "xrecyclerview"           : 'com.jcodecraeer:xrecyclerview:1.5.9',
            "appcompat"               : 'com.android.support:appcompat-v7:26.1.0',
            "cardview"                : 'com.android.support:cardview-v7:26.1.0',
            "constraint-layout"       : 'com.android.support.constraint:constraint-layout:1.0.2',

            "dagger"                  : 'com.google.dagger:dagger:2.11',
            "dagger-android"          : 'com.google.dagger:dagger-android:2.11',
            "dagger-android-support"  : 'com.google.dagger:dagger-android-support:2.11',
            "dagger-android-processor": 'com.google.dagger:dagger-android-processor:2.11',
            "dagger-compiler"         : 'com.google.dagger:dagger-compiler:2.11',
            "dagger-android-compiler" : 'com.google.dagger:dagger-android-processor:2.11',

            "leakcanary-android"      : 'com.squareup.leakcanary:leakcanary-android:1.5.1', //内存泄露自动探测神器
            "leakcanary-android-no-op": 'com.squareup.leakcanary:leakcanary-android-no-op:1.5.1',
            "rxjava"                  : 'io.reactivex.rxjava2:rxjava:2.0.8',
            "rxandroid"               : 'io.reactivex.rxjava2:rxandroid:2.0.1',
            "rxrelay"                 : 'com.jakewharton.rxrelay2:rxrelay:2.0.0',
            "rxbinding"               : 'com.jakewharton.rxbinding2:rxbinding:2.0.0',
            "retrofit"                : 'com.squareup.retrofit2:retrofit:2.2.0',
            "converter-gson"          : 'com.squareup.retrofit2:converter-gson:2.2.0',
            "adapter-rxjava2"         : 'com.squareup.retrofit2:adapter-rxjava2:2.2.0',
            "okhttp"                  : 'com.squareup.okhttp3:okhttp:3.0.1',
            "logging-interceptor"     : 'com.squareup.okhttp3:logging-interceptor:3.0.1',
            "rxpermissions"           : 'com.tbruyelle.rxpermissions2:rxpermissions:0.9.4@aar',
            "fastjson"                : 'com.alibaba:fastjson:1.2.48',
            "SmartRefreshLayout"      : 'com.scwang.smartrefresh:SmartRefreshLayout:1.0.5.1'
            
3.项目目录结构

app                      // 壳app
library_core             // 核心库 提供基础的网络操作
library_common           // 公共库
module_account           // 登录模块
module_player            // 玩Android api 简单实现


4. 数据流转具体逻辑

repository层
根据本次请求的请求策略来决定从网络还是从数据库（缓存，使用room）获取数据，提供给viewmodel 层的是livedata
viewmodel层
从repository中拿到数据后处理方式有很多种。 
a. 直接把数据提供给UI层，UI层来渲染对应的view(databinding)
b. 处理和加工数据，最后把一个livedata数据转化为 MediatorLiveData，通过MediatorLiveData把数据拆分为多个更加具体的livedata传递给UI（推荐用法）
UI层
负责展示和渲染（databinding）

5. 解决的问题

使用dagger2模块化开发的一次尝试，dagger2解藕后的多个模块单独运行的一个思路
遵守Android arch 规范的实现
适合敏捷开发和项目规模比较大的开发模式









            
