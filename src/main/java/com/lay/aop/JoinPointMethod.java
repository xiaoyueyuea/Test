package com.lay.aop;


import org.springframework.stereotype.Component;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/7/2 11:06
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/7/2 lei.yue 1.0 create file
 */
@Component
public class JoinPointMethod {

    public void printSomething(String args){
        System.out.println("目标方法");
    }

}
