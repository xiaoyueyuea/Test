package com.lay.customizeAnnotation;

import java.lang.reflect.Method;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/1/11 14:48
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/1/11 lei.yue 1.0 create file
 */
public class CustomizeAnnotationTest {

    @CustomizeAnnotation(name = "Lay",age = 22)
    public void getPerson(){
        System.out.println("just do it");
    }


    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class testClass = Class.forName("com.lay.customizeAnnotation.CustomizeAnnotationTest");
        Method testMethod = testClass.getMethod("getPerson");
        //如果我们要获得的注解是配置在方法上的，那么我们要从Method对象上获取；如果是配置在属性上，就需要从该属性对应的Field对象上去获取，如果是配置在类型上，需要从Class对象上去获取。总之在谁身上，就从谁身上去获取！
        if(testMethod.isAnnotationPresent(CustomizeAnnotation.class)){
            CustomizeAnnotation customizeAnnotation = testMethod.getAnnotation(CustomizeAnnotation.class);
            System.out.println(customizeAnnotation.name() + "：" + customizeAnnotation.age());
        }else {
            System.out.println("没有配置CustomizeAnnotation注解");
        }
    }
}
