package com.yxq.mybatis.session;

/**
 * @Description:
 * @Author: yxq
 * @Date: 2023/8/31
 */
public interface SqlSession {

    <T> T selectOne(String statement,Object parameter);

    <T> T getMapper(Class<T> classType);
}
