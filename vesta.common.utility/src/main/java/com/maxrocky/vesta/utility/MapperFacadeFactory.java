package com.maxrocky.vesta.utility;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * Created by Tom on 2016/1/14 11:38.
 * Describe:MapperFactory初始化加载
 */
@Component
public class MapperFacadeFactory implements FactoryBean<MapperFacade> {

    public MapperFacade getObject() throws Exception {
        return new DefaultMapperFactory.Builder().build().getMapperFacade();
    }

    public Class<?> getObjectType() {
        return MapperFacade.class;
    }

    public boolean isSingleton() {
        return true;
    }

}
