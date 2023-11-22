当前项目的dao存在大量重复代码
    AccountDaoImpl的代码其实很固定, 不想写 -> javassist -> 根据接口,生成对应dao接口的实现类 -> 1. 调哪个方法我不确定
        所有这样sqlSession.getConfiguration().getMappedStatement("sqlId").getSqlCommandType();获取sql命令类型
        -> sql语句id并不是框架开发者编写, 所有具有多变性, -> 规定使用GeneratorDaoProxy框架的人, 遵循一个规范
    能不能有一个东西， 可以帮我们动态生成dao对象



package bank.mapper.impl;

import bank.mapper.AccountDao;
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
    public Account selectByActno(String Actno) {
        //使用mybatis
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //执行sql语句
        Account act = sqlSession.selectOne("AccountMapper.selectByActno", Actno);
        return act;
    }

    @Override
    public int updateByActno(Account act) {
        //使用mybatis
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //执行sql
        int count = sqlSession.update("updateByActno", act);
        return count;
    }
}
