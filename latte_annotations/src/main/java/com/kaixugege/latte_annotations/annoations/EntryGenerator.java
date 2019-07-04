package com.kaixugege.latte_annotations.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: KaixuGege
 * Time:           2019/4/11
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */

@Target(ElementType.TYPE)//告诉这个编译器这个注解是用在类上面的
@Retention(RetentionPolicy.SOURCE)//告诉编译器，在源码阶段处理的，在打包后不使用了
public @interface EntryGenerator {

    String packageName();

    Class<?>  entryTemeplate();
}
