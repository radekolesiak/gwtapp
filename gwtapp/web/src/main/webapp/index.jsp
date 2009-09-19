<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.gwtapp.core.server.html.*"%>
<%@page import="org.gwtapp.core.client.html.ui.form.*"%>
<%@page import="org.gwtapp.core.client.html.ui.core.*"%>
<%@page import="org.gwtapp.startapp.client.ui.user.register.*"%>
<%@page import="org.gwtapp.startapp.client.data.*"%>
<%@page import="org.gwtapp.core.rebind.Util"%>
<%@page import="org.gwtapp.startapp.client.HWidgets"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>StartApp</title>
<script language='javascript'
	src='startappentry/startappentry.nocache.js'></script>
</head>
<body>
<%
	UserRegisterModel model = new UserRegisterModelImpl();//Util.bind(UserRegisterModel.class);
	model.setLogin("login_value");
	HUserRegisterPanel widget = new HUserRegisterPanel(model);
	widget.setName(HWidgets.HUserRegisterPanel);
	HGenerator g = new HGenerator(HWidgets.DICTIONARY, widget);
%>
<%=g.getRPC()%>
<%=g.getHTML()%>
</body>
</html>