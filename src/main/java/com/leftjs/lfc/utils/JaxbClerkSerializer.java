package com.leftjs.lfc.utils;

import com.leftjs.lfc.model.domain.Clerk;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created by jason on 2017/3/13.
 */
@Component
public class JaxbClerkSerializer extends XmlAdapter<String, Clerk> implements ApplicationContextAware{

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Override
    public Clerk unmarshal(String v) throws Exception {
        ClerkHelper helper = context.getAutowireCapableBeanFactory().createBean(ClerkHelper.class);
        return helper.getClerkByClerkId(v);
    }

    @Override
    public String marshal(Clerk v) throws Exception {
        return v.getClerkId();
    }

}
