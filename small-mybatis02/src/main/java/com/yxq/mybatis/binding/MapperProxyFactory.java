package com.yxq.mybatis.binding;

import com.yxq.mybatis.session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * @Description:
 * @Author: yxq
 * @Date: 2023/9/3
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(SqlSession sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession,mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(),new Class[]{mapperInterface},mapperProxy);
    }
}
