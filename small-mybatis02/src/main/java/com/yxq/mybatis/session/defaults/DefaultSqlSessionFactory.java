package com.yxq.mybatis.session.defaults;

import com.yxq.mybatis.binding.MapperRegistry;
import com.yxq.mybatis.session.SqlSession;
import com.yxq.mybatis.session.SqlSessionFactory;

/**
 * @Description:
 * @Author: yxq
 * @Date: 2023/9/3
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }



    @Override
    public SqlSession openSession() {

        return new DefaultSqlSession(mapperRegistry);
    }
}
