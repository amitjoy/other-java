/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.schneider.amit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.enterprise.inject.Stereotype;

@Log
@TimeStamp
@Stereotype
@Retention(RUNTIME)
@Target(ElementType.TYPE)
public @interface MessageBean {
}
