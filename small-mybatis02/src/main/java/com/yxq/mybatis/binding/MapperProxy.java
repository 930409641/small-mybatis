package com.yxq.mybatis.binding;

import com.yxq.mybatis.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: yxq
 * @Date: 2023/8/31
 */
public class MapperProxy<T>  implements InvocationHandler, Serializable {

    private static final long serialVersionUID = 2143399282597467990L;

    private SqlSession sqlSession;

    //mapper接口
    private Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this,args);
        } else {
            return sqlSession.selectOne(method.getName(),args);
        }
    }
}
