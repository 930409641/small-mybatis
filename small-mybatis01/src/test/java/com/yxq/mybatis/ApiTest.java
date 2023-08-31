package com.yxq.mybatis;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import com.yxq.mybatis.binding.MapperProxyFactory;
import com.yxq.mybatis.test.IUserDao;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: yxq
 * @Date: 2023/8/31
 */
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);


    @Test
    public void test_MapperProxyFactory() {
        MapperProxyFactory<IUserDao> proxyFactory = new MapperProxyFactory<>(IUserDao.class);
        Map<String,String> sqlSession = new HashMap<>();
        sqlSession.put("com.yxq.mybatis.test.IUserDao.queryUserName","模拟Mapper.xml 中SQL语句的操作，查询名字");
        IUserDao userDao = (IUserDao) proxyFactory.newInstance(sqlSession);
        System.out.println(userDao.queryUserName(111L));
    }

    @Test
    public void test_proxy_class() {
        IUserDao userDao = (IUserDao) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{IUserDao.class},
                ((ptoxy, method, args) -> "你被代理了")
        );

        System.out.println(userDao.queryUserName(111L));

    }

}
