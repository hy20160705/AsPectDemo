package com.hy.aspectdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hy.aspectdemo.api.ILoginInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MainActivity extends AppCompatActivity implements ILoginInterface {
    private final String TAG = getClass().getSimpleName();
    private ILoginInterface loginInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginInterface = (ILoginInterface) Proxy.newProxyInstance(ILoginInterface.class.getClassLoader(), new Class[]{ILoginInterface.class}, new ILoginHandler(this));
    }

    @Override
    public boolean checkLogin() {
        Log.e(TAG, "检测是否登录");
        return false;
    }

    @Override
    public void query() {
        Log.e(TAG, "执行查询操作");
    }

    @Override
    public void jumpLogin() {
        Log.e(TAG, "执行跳转到登录操作");
    }

    public void jump(View view) {
//        checkLogin();
////        query();
        loginInterface.query();
    }

    public void jumpAspect(View view) {
        startActivity(new Intent(this,AsPectJActivity.class));
    }

    private class ILoginHandler implements InvocationHandler {
        private ILoginInterface loginInterface;

        public ILoginHandler(ILoginInterface loginInterface) {
            this.loginInterface = loginInterface;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (loginInterface != null) {
                Log.e(TAG, "查询数据之前检查是否登录");
                if (loginInterface.checkLogin()) {
                    Log.e(TAG, "当前是登录状态,可以查询数据");
                    return method.invoke(loginInterface, args);
                } else {
                    Log.e(TAG, "当前未登录,跳转到登录页面");
                    loginInterface.jumpLogin();
                }

            }
            return null;
        }
    }
}
