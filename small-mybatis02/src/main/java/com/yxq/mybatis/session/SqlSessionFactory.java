package com.yxq.mybatis.session;

/**
 * @Description:
 * @Author: yxq
 * @Date: 2023/8/31
 */
public interface SqlSessionFactory {

    SqlSession openSession();
}
