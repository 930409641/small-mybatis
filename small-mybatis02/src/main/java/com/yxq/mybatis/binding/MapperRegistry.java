package com.yxq.mybatis.binding;

import cn.hutool.core.lang.ClassScanner;
import com.yxq.mybatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description:
 * @Author: yxq
 * @Date: 2023/9/3
 */
public class MapperRegistry {

    private final Map<Class<?>,MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);

        if(mapperProxyFactory == null) {
            throw new RuntimeException("Type \" + type + \" is not known to the MapperRegistry.");
        }
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
        }

    }


    public <T> void addMapper(Class<T> type) {
        /* Mapper 必须是接口才会注册 */
        if(type.isInterface()) {
            if(hasMapper(type)) {
                //防止重复注册
                throw new RuntimeException("Type \" + type + \" is already known to the MapperRegistry.");
            }
            // 注册映射器代理工厂
            knownMappers.put(type,new MapperProxyFactory<>(type));
        }
    }

    private <T> boolean hasMapper(Class<T> classType) {
        return knownMappers.containsKey(classType);
    }

    public void addMappers(String pageName) {
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(pageName);
        for (Class<?> classMapper: mapperSet) {
            addMapper(classMapper);
        }
    }

}
