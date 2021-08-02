package com.lay.designMode;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/4/8 11:37
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/4/8 lei.yue 1.0 create file
 */
//单例模式
public class Singleton {

    //实例声明为静态的私有的
    private static Singleton instance = new Singleton();

    //构造函数设为私有
    private Singleton(){}

    //静态工厂方法，供外部访问
    public static Singleton getInstance(){
        return instance;
    }
}
