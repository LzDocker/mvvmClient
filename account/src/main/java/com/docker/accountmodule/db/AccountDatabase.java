package com.docker.accountmodule.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.docker.accountmodule.vo.LoginVo;

/**
 * Created by zhangxindang on 2018/12/25.
 */

@Database(entities = {LoginVo.class},version = 1)
public abstract class AccountDatabase extends RoomDatabase {

     public abstract AccountDao accountDao();

}
