<%@page contentType="text/html;charset=UTF-8"%>
<html>
<body>
<h2>银行转账</h2>
<hr />

<form action="/bank/transfer" method="POST">
    转出账户: <input type="text" name="fromActno"/> <br>
    转入账户: <input type="text" name="toActno"> <br>
    转账金额: <input type="text" name="money"> <br>
    <input type="submit" value="转账">
</form>
</body>
</html>
