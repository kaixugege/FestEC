package com.kaixugege.latte_compiler.compiler;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.prefs.AbstractPreferences;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

/**
 * @Author: KaixuGege
 * Time:           2019/4/11
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class LatteProcessor extends AbstractProcessor {


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        final Set<String> types = new LinkedHashSet<>();
//        final Set<Class<? extends Annotation>> supportAnnotations;

        return false;
    }

//    private Set<Class<? extends Annotation>> getSupportedAnnotations(){
//
//    }
}
