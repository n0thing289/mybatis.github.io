package bank.web;

import bank.exceptions.AppException;
import bank.exceptions.NotEnoughMoneyException;
import bank.service.AccountService;
import bank.service.impl.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/transfer"})
public class AccountServlet extends HttpServlet {

    private final AccountService accountService = new AccountServiceImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取前端传过来的参数
        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");
        double money = Double.parseDouble(request.getParameter("money"));

        //控制事务
        //处理转账业务
        try {
            accountService.transfer(fromActno, toActno, money);
            //程序可以走到这里说明转账成功了
            //调用视图层,完成展示结果
            response.sendRedirect(request.getContextPath() + "/success.html");
        }
        catch (NotEnoughMoneyException e) {

            response.sendRedirect(request.getContextPath() + "/snotenoughmoney.html");
        } catch (AppException e) {

            response.sendRedirect(request.getContextPath() + "/apperror.html");
        }


    }

}
