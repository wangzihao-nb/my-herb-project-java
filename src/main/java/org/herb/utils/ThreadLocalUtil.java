package org.herb.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal 工具类，提供线程局部变量，使用ThreadLocal存储的数据，线程安全。
 * ThreadLocal可以减少参数的传递，在同一个线程执行的代码可以共享数据。
 */
@SuppressWarnings("all")
public class ThreadLocalUtil {
    //提供ThreadLocal对象,
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();

    //根据键获取值
    public static <T> T get(){
        return (T) THREAD_LOCAL.get();
    }
	
    //存储键值对
    public static void set(Object value){
        THREAD_LOCAL.set(value);
    }

    //清除ThreadLocal 防止内存泄漏
    public static void remove(){
        THREAD_LOCAL.remove();
    }
}
