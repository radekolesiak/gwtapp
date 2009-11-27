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
	src='gwt.startappentry/gwt.startappentry.nocache.js'></script>
</head>
<body>
Applications that use GWTApp:<br />
<a href="http://ccalc-web.appspot.com/">FIFO Currency Calculator</a><br />
<br />
<%
	UserRegisterModel model = new UserRegisterModelImpl();//Util.bind(UserRegisterModel.class);
	model.setLogin("login_value");
	HUserRegisterPanel widget = new HUserRegisterPanel(model);
	widget.setName(HWidgets.HUserRegisterPanel);
	HGenerator g = new HGenerator(HWidgets.DICTIONARY, widget);
%>
<%=g.getRPC()%>
<%=g.getHTML()%>
<!-- GA  -->
<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-11571468-2");
pageTracker._trackPageview();
} catch(err) {}
</script>
<!-- GA  -->
</body>
</html>