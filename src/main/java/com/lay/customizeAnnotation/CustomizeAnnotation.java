package com.lay.customizeAnnotation;

import java.lang.annotation.*;

/**
 * 自定义注解其实就是一种标记，可以在程序代码中的关键节点（类、方法、变量、参数、包）上打上这些标记，然后程序在编译时或运行时可以检测到这些标记从而执行一些特殊操作
 * 1.定义注解：相当于定义标记；
 * 2.配置注解：把标记打在需要用到的程序代码中；
 * 3.解析注解：在编译期或运行时检测到标记，并进行特殊操作。
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomizeAnnotation {
    public String name();
    int age() default 18;
}
/**
 * 1.访问修饰符必须为public，不写默认为public；
 * 2.该元素的类型只能是基本数据类型、String、Class、枚举类型、注解类型（体现了注解的嵌套效果）以及上述类型的一位数组；
 * 3.该元素的名称一般定义为名词，如果注解中只有一个元素，请把名字起为value（后面使用会带来便利操作）；
 * 4.()不是定义方法参数的地方，也不能在括号中定义任何参数，仅仅只是一个特殊的语法；
 * 5.default代表默认值，值必须和第2点定义的类型一致；
 * 6.如果没有默认值，代表后续使用注解时必须给该类型元素赋值。
 * 7.只有使用@Retention(RetentionPolicy.RUNTIME)修饰注解时，才能在JVM运行时，检测到注解，并进行一系列特殊操作
*/