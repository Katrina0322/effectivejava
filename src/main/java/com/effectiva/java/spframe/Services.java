/**
 * Copyright 2015 EmbraceSource
 * All Right Reserved
 * Author：jason.tian
 * Create Date：Dec 14, 2015
 */
package com.effectiva.java.spframe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  静态工厂方法
 *  服务提供者框架
 *
 */

public class Services {

  private Services(){}
  
  private static final Map<String,Provider> providers = new ConcurrentHashMap<String,Provider>();
  
  private static final String DEAFAULT_PROVIDER_NAME = "<def>";
  
  public static void registerDeafaultProvider(Provider p){
    registerProvider(DEAFAULT_PROVIDER_NAME,p);
    
  }
  
  public static void registerProvider(String name,Provider p){
    providers.put(name, p);     
  }
  
  public static Service newInstance(){
    return newInstance(DEAFAULT_PROVIDER_NAME);
  }
  
  public static Service newInstance(String name){
    Provider p = providers.get(name);
    if(p == null){
      throw new IllegalArgumentException("No provider registered with name:" +name);
    }
    return p.newService();
  }
  
}
