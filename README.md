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



6.整体流程

如果关注点是使用该框架，那么其实整体用法还是固定的，先说说用法吧

a. 新建功能模块module,名字假如为moduleTest目录结构按照 moduleplayer的就可以，当然需要你在project 目录下，创建debug 目录，这是为了使你的moduleTest
能够自己起飞，单独作为一个项目来运行， 在debug目录下新建你的application,继承baseapplication实现initDi(); 把相关的依赖配置好，然后component（这里假如你会使用dagger2）

b. 到项目真正功能的开发了,那就说说分包
api        -- retorfit的数据接口
db         -- 数据库相关（如果你要自己维护缓存，那这个地方需要你自己来写，用的room,如果缓存策略能满足你的开发，那这个地方可以忽略，当然后期会加入etag cache-control等默认服务端缓存策略）
di         -- 注入 api下的数据接口，ui相关的activity fragment viewmodel 都是在这个地方注入的，至于是怎么注入的这个问题后面讲
repository -- 仓库，我们不提供数据，我们只是数据的搬运工。数据的获取是在这里，因此所有请求的响应你都能在这里看到她的身影
ui         -- 这个不说了，我知道你肯定是Android开发 emmm... 还是说下吧 activity fragment 中的职责就是控制view层，响应用户操作，所以不要把处理业务的逻辑放在这里，
可以放到repository中来处理，然后再通过具体的livedata驱动UI层的view作出响应，所以他们应该是简介的，只监听livedata的变化，把这变化传递给view，xml使用databinding,（我这里没有使用太多，原则
上讲可以多用一些bindingadapter,但是我总觉得有点本末倒置了，数据驱动就要在xml中处理过多的逻辑，这让我想到了java的jsp,和php，我们是为了维护访方便，解藕清晰才框架的，不是吗？这个就自己考虑吧,没有定义binding包是因为我到现在也没说服自己）
viewmodel  -- 这一层和mvp的p层有点像，但是更加丝滑（个人感觉），数据处理不再考虑生命周期的限制，从repository拿到数据可以加工也可直接使用（建议加工后成为具体有意义的数据再给UI层，应为不加工的后果可能是把你的架构变成了mvc,哈哈哈，我就是从这走过来的）
vo         -- 这个就是请求的实体类，当然如果自己维护缓存，也可以当作room的entity层，使用提供的缓存的话，就把你要缓存的实体实现Serializable
widget     -- 控件

c. 框架的构建流程

d.依赖关系
从builde.gradle(app)中能看的出来 整体的依赖规则

   if (!IsBuildMudle.toBoolean()) {
        implementation project(':moduleAccount')
        implementation project(':moduleGank')
        implementation project(':moduleplayer')
    } else {
        implementation project(':library_core')
    }

每个模块的都依赖   api project(':library_core')

library_core依赖library_common

e.  library_core 的职责

library_core 提供基础的网络请求模块，基础的注入模块，一些工具类（后期放到common中），可以看到没有components ，这是因为踩坑dagger2的时候（有时间再探探这个坑，或者有dagger2大神给解决下，总觉得目前的不完美）没能使用dependencies和subcomponent这些姿势来解决多个components之间的依赖
，所以用了不是很完美的一个components 多个module的实现方式，还算丝滑,使用dagger-android来做的四大组件的注入，在baseappliction中暴露initDi方法供继承者注入自己的四大组件，当然了需要实现baseactitivity 或者 basefragment,因为他们的注入是在activity的oncreate中注入了，这时候你可能会问，为什么不再baseactivity中统一注入呢，
毕竟baseactivity 的handactivity 会holader住所有activity的创建，这个问题我也想过，这姿势肯定很爽，但是我没能实现，一直报错让我不得已用了现在这个姿势，至于出错的原因是什么呢？我想是跟上面说的 dagger2的dependencies和subcomponent有关，这个留着以后探索。至于注入这方面的就不多讲了
网络请求使用的retorfit 加 LiveDataCallAdapterFactory，livedatacalladapter显得有些脆弱，这里是用的网上找来的，后续会自己动手让她strong一点，至于拦截器这些就不多说了。
好了关键的网络请求和注入大致就是上面这些了

f. library_common 的职责
这个就随意些了，common都很熟悉，目前只是把arouter的跳转path的定义和一些工具类放在这里了。存在的意义是我觉从一开始觉得library_core在开发的时候不要频繁的动，common来处理一些公共资源

g. 看完上面这些如果还是迷茫的，那不是你的错，我的。。好吧，自己撸一遍登录注册就明白了(嘿嘿嘿)

h. 未完待续..





