package com.docker.core.repository.cache;

public enum CacheStrategy {

    DEFAULT,
    NO_CACHE,//：不使用缓存，
    REQUEST_FAILED_READ_CACHE,//：先请求网络，如果请求网络失败，则读取缓存，缓存不保证一定会有
    IF_NONE_CACHE_REQUEST,//：如果缓存不存在才请求网络，否则使用缓存。
    FIRST_CACHE_THEN_REQUEST,//：先使用缓存，不管是否存在，仍然请求网络。
}
