package com.hy.aspectdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.hy.aspectdemo.annotation.ClickBehavior;
import com.hy.aspectdemo.annotation.LoginCheck;

/**
 * @Name: AsPectDemo
 * @Description: 描述信息
 * @Author: Created by heyong on 2019-10-30
 */
public class AsPectJActivity extends AppCompatActivity {
    private static final String TAG = "AsPectJActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspect);
    }

    // 用户行为统计
    @ClickBehavior("优惠券")
    @LoginCheck
    public void myCoupon(View view) {
        // 开始统计
        Log.e(TAG, "开始跳转到我的优惠券Activity");
        startActivity(new Intent(this, BusinessActivity.class));
        // 结束统计
    }

    // 用户行为统计
    @ClickBehavior("专区")
    @LoginCheck
    public void myArea(View view) {
        Log.e(TAG, "开始跳转到我的专区Activity");
        startActivity(new Intent(this, BusinessActivity.class));
    }

    // 用户行为统计
    @ClickBehavior("积分")
    @LoginCheck
    public void myScore(View view) {
        Log.e(TAG, "开始跳转到我的积分Activity");
        startActivity(new Intent(this, BusinessActivity.class));
    }

    // 用户行为统计
    @ClickBehavior("登录")
    public void login(View view) {
        Log.e(TAG, "开始跳转到我的登录Activity");
    }
}
