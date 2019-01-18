package com.docker.core.repository.cache;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class CacheEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long cid;

    @ColumnInfo(name = "cachekey")
    private String key;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] data;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }


}
