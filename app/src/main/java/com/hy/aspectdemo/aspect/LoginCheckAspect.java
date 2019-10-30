package com.hy.aspectdemo.aspect;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.hy.aspectdemo.LoginActivity;
import com.hy.aspectdemo.annotation.ClickBehavior;
import com.hy.aspectdemo.annotation.LoginCheck;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @Name: AsPectDemo
 * @Description: 描述信息
 * @Author: Created by heyong on 2019-10-30
 */
@Aspect //定义一个切面类
public class LoginCheckAspect {
    private static final String TAG = "aspect >>>> ";

    // 1. 应用中用到了哪些注解，放到当前的切入点进行处理（找到切入点）
    // execution 以方法执行作为切入点，触发Aspect类
    // * *(..) 可以处理ClickBehavior这个类所有的方法（通配符）
    @Pointcut("execution(@com.hy.aspectdemo.annotation.LoginCheck * *(..))")
    public void methodPointCut() {

    }

    // 2. 对这些切入点如何处理
    @Around("methodPointCut()")
    public Object joinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Context context = (Context) joinPoint.getThis();
        if (true) { // 从SharedPreferences中读取登录状态
            Log.e(TAG, "检测到已登录！");
            return joinPoint.proceed();
        } else {
            Log.e(TAG, "检测到未登录！");
            Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, LoginActivity.class));
            return null; // 不在执行方法（切入点的方法）
        }
    }
}
