package com.lay.designMode;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/12/20 17:37
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/12/20 lei.yue 1.0 create file
 */
//使用嵌套类实现单例
public class Singleton2 {

    private Singleton2(){System.out.println("实例化Holder内部类");}

    static {
        System.out.println("Singleton2类初始化");
    }

    public static String str = "我是一个字符串";

    private static class Holder{
        private static final Singleton2 SINGLETON = new Singleton2();
    }

    public static Singleton2 getInstance(){
        return Holder.SINGLETON;
    }
}
