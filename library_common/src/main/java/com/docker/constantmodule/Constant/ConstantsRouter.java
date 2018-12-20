package com.docker.constantmodule.Constant;

/**
 * Created by zhangxindang on 2018/11/21.
 */

public interface ConstantsRouter {


    interface ModuleAccount {

        String ACTIVITY_ACCOUNT = "/ModuleAccount/ui/account";

        String LOGINVO = "loginvo"; // 登录后的实体类

    }

    interface ModuleGank {

        String ACTIVITY_GANK = "/ModuleGank/ui/gank";

    }

    interface ModulePlayer {
        String ACTIVITY_MAIN = "/ModulePlayer/ui/main";
    }


}
