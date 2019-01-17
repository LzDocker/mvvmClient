package com.docker.accountmodule.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.docker.accountmodule.vo.LoginVo;
import com.docker.commonlibrary.api.BaseResponse;

import java.util.List;

/**
 * Created by zhangxindang on 2018/12/25.
 */

@Dao
public interface AccountDao {

    @Query("SELECT * FROM LoginVo where username =:name")
    LiveData<LoginVo> LoadAccount(String name);

    @Insert
    void insertAll(LoginVo... loginVos);

    @Query("SELECT * FROM LoginVo WHERE username = :username")
    LoginVo findByUsername(String username);

    @Delete
    void delete(LoginVo loginVo);

    @Update
    void update(LoginVo loginVo);

}
