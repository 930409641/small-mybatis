package com.yxq.mybatis.binding;

import com.sun.javafx.collections.MappingChange;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Description:
 * @Author: yxq
 * @Date: 2023/8/31
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = 2143399282597467990L;

    private Map<String,String> sqlSession;
    private Class<T> mapperInterface;

    public MapperProxy(Map<String, String> sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this,args);
        } else {
            return "你被代理了" + sqlSession.get(mapperInterface.getName()+"."+method.getName());
        }
    }
}
