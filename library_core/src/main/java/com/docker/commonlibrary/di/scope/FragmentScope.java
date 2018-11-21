package com.docker.commonlibrary.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by GLooory on 17/5/15.
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScope {
}
