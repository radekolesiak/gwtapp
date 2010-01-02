<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.gwtapp.core.server.html.core.*"%>
<%@page import="org.gwtapp.core.client.html.ui.form.*"%>
<%@page import="org.gwtapp.core.client.html.ui.core.*"%>
<%@page import="org.gwtapp.startapp.client.ui.*"%>
<%@page import="org.gwtapp.startapp.client.ui.user.register.*"%>
<%@page import="org.gwtapp.startapp.client.data.user.register.*"%>
<%@page import="org.gwtapp.startapp.client.HWidgets"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>StartApp</title>
<script language='javascript'
	src='gwt.startappentry/gwt.startappentry.nocache.js?rand=<%=Math.random()%>'></script>
</head>
<body>
<a href="http://sourceforge.net/projects/gwtapp/develop/">Source code</a><br />
<br />
Applications that use GWTApp:<br />
<a href="http://ccalc-web.appspot.com/">FIFO Currency Calculator</a><br />
<br />
<table>
<tr>
<td>
<%
	UserRegisterModel model = new UserRegisterModelImpl();
	model.setLogin("login_value");
	HUserRegisterPanel userRegisterPanel = new HUserRegisterPanel(model);
	userRegisterPanel.setName(HWidgets.HUserRegisterPanel);
	HWidgetGenerator gUserRegister = new HWidgetGenerator(HWidgets.DICTIONARY, userRegisterPanel);
%>
<%=gUserRegister.getHTML()%>
</td>
<td>
<%
	HTabPanel tabPanel = new HTabPanel();
	tabPanel.setName(HWidgets.HTabPanel);
	HWidgetGenerator gTab = new HWidgetGenerator(HWidgets.DICTIONARY, tabPanel);
%>
<%=gTab.getHTML()%>
<br />
Source code files that are used to download the form as a file on single HTTP request (GWT's serializable Java object -> file):
<ul>
<li>Remote service definition - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/rpc/src/main/java/org/gwtapp/startapp/client/api/DownloadService.java?view=markup">DownloadService.java</a></li>
<li>Remote service implementation - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/srv/src/main/java/org/gwtapp/startapp/server/DownloadServiceImpl.java?view=markup">DownloadServiceImpl.java</a></li>
<li>UI to download a form - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/src/main/java/org/gwtapp/startapp/client/StartApp.java?view=markup">StartApp.java</a></li>
<li>The web.xml file - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/war/WEB-INF/web.xml?view=markup">web.xml</a></li>
</ul>
Source code files that are used to upload the form from a file on single HTTP request (file -> GWT's serializable Java object):
<ul>
<li>Service implementation - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/srv/src/main/java/org/gwtapp/startapp/server/UploadServiceImpl.java?view=markup">UploadServiceImpl.java</a></li>
<li>UI-1 to upload a form - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/src/main/java/org/gwtapp/startapp/client/ui/UploadPanel.java?view=markup">UploadPanel.java</a></li>
<li>UI-2 to upload a form - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/src/main/java/org/gwtapp/startapp/client/StartApp.java?view=markup">StartApp.java</a></li>
<li>The web.xml file - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/war/WEB-INF/web.xml?view=markup">web.xml</a></li>
</ul>
</td>
</tr>
</table>

<%if ("gwtapp-web.appspot.com".equals(request.getServerName())){ %>
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
<%}%>
</body>
</html>