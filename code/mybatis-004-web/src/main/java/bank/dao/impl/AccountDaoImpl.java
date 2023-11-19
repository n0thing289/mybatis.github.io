package bank.dao.impl;

import bank.dao.AccountDao;
import bank.pojo.Account;
import bank.utils.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * 基本的增删改查的
 * 学习了mybatis框架,我们用mybatis来做
 */
public class AccountDaoImpl implements AccountDao {


    @Override
    public Account selectByActno(String Actno) throws IOException {
        //使用mybatis
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //执行sql语句
        Account act = sqlSession.selectOne("AccountMapper.selectByActno", Actno);
        return act;
    }

    @Override
    public int updateByActno(Account act) throws IOException {
        //使用mybatis
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //执行sql
        int count = sqlSession.update("updateByActno", act);
        return count;
    }
}
