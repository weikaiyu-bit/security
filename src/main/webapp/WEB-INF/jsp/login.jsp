<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form action="login" method="post">
用户名：<input type="text" name="username"><br>
    密码：<input type="text" name="password"><br>
    <button type="submit">登录</button>
</form>