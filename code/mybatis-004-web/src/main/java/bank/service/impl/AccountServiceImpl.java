package bank.service.impl;

import bank.dao.AccountDao;
import bank.dao.impl.AccountDaoImpl;
import bank.exceptions.AppException;
import bank.exceptions.NotEnoughMoneyException;
import bank.pojo.Account;
import bank.service.AccountService;
import bank.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

/**
 * 处理账户业务的业务类
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao = new AccountDaoImpl();

    /**
     * 完成转账业务
     *
     * @param fromActno
     * @param toActno
     * @param money
     */
    public void transfer(String fromActno, String toActno, double money) throws IOException, NotEnoughMoneyException, AppException {
        //事务的控制一定要放在服务层，就需要使用代理
        //这个工具类使用mybatis和threadlocal类与线程绑定了, 同一线程(同一用户的sqlSession是相同地),默认开启了事务,只要不提交, 即使出现异常也不影响数据
        SqlSession sqlSession = SqlSessionUtil.openSession();

        //查询转出账户的金额, 判断是否有足够的钱
        Account fromAct = accountDao.selectByActno(fromActno);
        if (fromAct.getBalance() < money) {//如果余额不足抛出NotEnoughMoneyException异常
            throw new NotEnoughMoneyException("对不起, 余额不足");
        }
        // 如果有足够的钱, 修改对象的余额
        Account toAct = accountDao.selectByActno(toActno);
        fromAct.setBalance(fromAct.getBalance() - money);
        toAct.setBalance(toAct.getBalance() + money);
        // 将对象的数据 更新到数据库
        int count = 0;
        count += accountDao.updateByActno(fromAct);
//        String s =null;
//        s.toString();
        count += accountDao.updateByActno(toAct);
        // 如果转账成功条数不对,抛出异常
        if(count != 2){
            throw new AppException("app转账异常!");
        }
        //能走到这里说明修改数据库都成功了,这时候才提交
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }
}
