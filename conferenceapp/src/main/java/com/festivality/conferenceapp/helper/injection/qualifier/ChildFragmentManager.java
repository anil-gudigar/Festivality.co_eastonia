package com.festivality.conferenceapp.helper.injection.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;


/**
 * Child fragment manager qualifier
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ChildFragmentManager {}
