package org.example.demo.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Array;
import java.util.Arrays;

// custom dependency injection realisation
@Component
public class InjectBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        // read each object field
        Arrays.stream(bean.getClass().getDeclaredFields())
                // filter fields with @InjectBean annotation
                .filter(field->field.isAnnotationPresent(InjectBean.class))
                .forEach(field -> {
                    // read type of fields and get bean of this type from context
                    var objectToInject = applicationContext.getBean(field.getType());

                    // some advanced thing to make field accessible and set bean value to the field
                    ReflectionUtils.makeAccessible(field);
//                    field.setAccessible(true);
                    ReflectionUtils.setField(field,bean,objectToInject);
//                    field.set(bean,objectToInject);
                });

        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
