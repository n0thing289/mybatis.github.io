package bank.service;

import bank.exceptions.AppException;
import bank.exceptions.NotEnoughMoneyException;

import java.io.IOException;

public interface AccountService {
    /**
     * 完成银行账户转账业务
     * @param fromActno
     * @param toActno
     * @param money
     */
    void transfer(String fromActno, String toActno, double money) throws IOException, NotEnoughMoneyException, AppException;
}
