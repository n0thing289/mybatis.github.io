import bank.exceptions.AppException;
import bank.exceptions.NotEnoughMoneyException;
import bank.service.AccountService;
import bank.service.impl.AccountServiceImpl;
import org.junit.Test;

import java.io.IOException;

public class TestAccountServiceImpl {
    @Test
    public void testTransfer() throws AppException, NotEnoughMoneyException, IOException {
        AccountService accountService = new AccountServiceImpl();
        accountService.transfer("act001","act002",10000);
    }
}
