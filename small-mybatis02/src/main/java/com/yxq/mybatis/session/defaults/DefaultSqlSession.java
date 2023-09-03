package com.yxq.mybatis.session.defaults;

import com.yxq.mybatis.binding.MapperRegistry;
import com.yxq.mybatis.session.SqlSession;

/**
 * @Description:
 * @Author: yxq
 * @Date: 2023/9/3
 */
public class DefaultSqlSession implements SqlSession {

    //mapperRegistry中存有所有接口的MapperProxyFactory工厂
    private MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {

        return  (T) ("你被代理了！" + "方法：" + statement + " 入参：" + parameter);
    }

    /**
     * 通过调用mapperRegistry的getMapper会创建该接口的MapperProxy
     * @param classType
     * @return
     * @param <T>
     */
    @Override
    public <T> T getMapper(Class<T> classType) {
        return mapperRegistry.getMapper(classType,this);
    }
}
