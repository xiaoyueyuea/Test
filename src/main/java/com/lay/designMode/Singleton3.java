package com.lay.designMode;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2022/1/6 11:55
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2022/1/6 lei.yue 1.0 create file
 */
//双重校验锁
public class Singleton3 {

    //volatile禁止指令重排
    private static volatile Singleton3 INSTANCE = null;

    private Singleton3(){}

    public static Singleton3 getInstance(){
        //未实例化才进入加锁代码
        if(INSTANCE == null){//提高效率
            synchronized (Singleton3.class){
                if(INSTANCE == null){//避免多次实例化
                    //1.为INSTANCE分配内存空间;2.初始化INSTANCE；3.将INSTANCE指向分配的内存地址
                    INSTANCE = new Singleton3();//这行代码实际包含3个指令，在多线程环境下如果没有加volatile可能导致线程不安全,导致未完全初始化的对象被获取到
                }
            }
        }
        return INSTANCE;
    }
}