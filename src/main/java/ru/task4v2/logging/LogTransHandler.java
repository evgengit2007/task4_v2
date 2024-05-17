package ru.task4v2.logging;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Calendar;

@Component
public class LogTransHandler implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!bean.getClass().isAnnotationPresent(LogTransformation.class)) {
            return bean;
        }
        ;
//        System.out.println("beanName: " + beanName);
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(bean.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                String logFileName = (bean.getClass().getAnnotation(LogTransformation.class)).logName();
//                System.out.println("logFileName: " + logFileName);
                FileWriter fw = new FileWriter(logFileName, true);
                fw.write("Bean: " + beanName + ", Method: " + method + ", DateTime: " + Calendar.getInstance().getTime() + "\n");
                Object result = method.invoke(bean, args);
                Object[] objects = Arrays.stream(args).toArray();
                for (int i = 0; i < objects.length; i++) {
                    fw.write("args[" + i + "]: " + objects[i].getClass() + " = " + objects[i].toString() + "\n");
                }
                if (result != null) {
                    fw.write("Result: " + result.toString() + "\n");
                }
                fw.close();
                return result;
            }
        });
        Constructor cons = bean.getClass().getConstructors()[0];
        Object[] arr = new Object[cons.getParameterCount()];
        return enhancer.create(cons.getParameterTypes(), arr);
    }
}
