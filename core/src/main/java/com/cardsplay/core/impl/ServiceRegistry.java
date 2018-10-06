package com.cardsplay.core.impl;

import com.cardsplay.core.api.LifeCycleService;
import com.cardsplay.core.exception.ServiceException;
import com.cardsplay.util.ResponseCode;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ServiceRegistry {
    public  final static Logger log = LoggerFactory
            .getLogger(ServiceRegistry.class);
    public  Map<Class, LifeCycleService> serviceMap = Maps.newConcurrentMap();

    private static ServiceRegistry instance = new ServiceRegistry();

    private  ServiceRegistry(){

    }

    public void registryService(Class key, LifeCycleService service){
        serviceMap.put(key, service);
    }

    public LifeCycleService getService(Class key) throws ServiceException{
        if (serviceMap.containsKey(key)){
             return serviceMap.get(key);
        } else {
            log.error("Player {} do not exist", key);
            throw new ServiceException(ResponseCode.badRequest, "服务不存在");
        }
    }

    public Iterable<LifeCycleService> getServices(){
        return serviceMap.values();
    }
    public static ServiceRegistry getInstance(){
        return instance;
    }

}
