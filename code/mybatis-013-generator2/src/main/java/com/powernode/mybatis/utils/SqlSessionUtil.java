package com.powernode.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * 用于提供SqlSession对象, 关闭SqlSession对象的工具类
 */
public class SqlSessionUtil {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final ThreadLocal<SqlSession> local = new ThreadLocal<>();

    public static SqlSession openSession() {
        SqlSession sqlSession = local.get();
        if (sqlSession == null) {
            //如果进来这里,说明是第一次创建
            sqlSession = sqlSessionFactory.openSession();
            //绑定线程
            local.set(sqlSession);
        }
        return sqlSession;
    }

    public static void close(SqlSession sqlSession) {
        if (sqlSession != null) {
            //关闭
            sqlSession.close();
            //解绑
            local.remove();
        }
    }

    public static void close() {
        SqlSession sqlSession = local.get();
        if (sqlSession != null) {
            //关闭
            sqlSession.close();
            //解绑
            local.remove();
        }
    }
}
