package bank.dao;

import bank.pojo.Account;

import java.io.IOException;

/**
 * 账户的DAO对象,负责表中数据增删改查
 * dao任何一个方法不和业务挂钩, 是没有业务逻辑的
 * 只做crud
 */
public interface AccountDao {
    Account selectByActno(String Actno);
    int updateByActno(Account act);
}
