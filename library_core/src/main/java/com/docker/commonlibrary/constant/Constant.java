package com.docker.commonlibrary.constant;

public interface Constant {

    /*
    * viewmodel --> ui 层的通用回调的KEY
    * */
    interface VmtoUIconstant{

        int KEY_NETWORK_COMPLETE = 0;   // 请求成功

        int KEY_NETWORK_ERROR = 1;   // 请求失败（网络无连接）

        int KEY_NETWORK_BUSSINESS_ERROR = 2;   // 业务失败（网络连接正常）

        int KEY_TOAST = 3;   // Toast

    }


}
