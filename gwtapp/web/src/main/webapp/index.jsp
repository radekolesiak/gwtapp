<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.gwtapp.core.server.html.*"%>
<%@page import="org.gwtapp.core.client.html.ui.*"%>
<%@page import="org.gwtapp.startapp.client.ui.user.register.*"%>
<%@page import="org.gwtapp.startapp.client.data.UserRegisterModel"%>
<%@page import="org.gwtapp.core.rebind.Util"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>StartApp</title>
<script language='javascript'
	src='startappentry/startappentry.nocache.js'></script>
</head>
<body>
<%
UserRegisterModel model = null;//Util.bind(UserRegisterModel.class);
HUserRegisterPanel widget = null;//new HUserRegisterPanel(model);
HGenerator g = null;//new HGenerator(widget);
%>
</body>
</html>