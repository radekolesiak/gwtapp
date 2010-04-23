<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page pageEncoding="utf-8"%>        
<%@page import="org.gwtapp.core.server.html.core.*"%>
<%@page import="org.gwtapp.core.rpc.html.ui.form.*"%>
<%@page import="org.gwtapp.core.rpc.html.ui.core.*"%>
<%@page import="org.gwtapp.startapp.rpc.ui.*"%>
<%@page import="org.gwtapp.startapp.rpc.ui.user.register.*"%>
<%@page import="org.gwtapp.startapp.rpc.data.user.register.*"%>
<%@page import="org.gwtapp.startapp.client.HWidgets"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>StartApp</title>
		<style type="text/css"> 
			@import "startapp.css";
		</style>
		<script type="text/javascript" src="gwt.startappentry/gwt.startappentry.nocache.js?rand=<%=Math.random()%>"></script>
		<t:include repository="/templates/" template="sync-startapp.jsp"/>
	</head>
	<body>
	    <noscript>
      		<div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
        		Your web browser must have JavaScript enabled in order for this application to display correctly.
      		</div>
    	</noscript>
	
		<table border="border" cellspacing="5" cellpadding="15">
		<tr>
		<th>Wrap and remove sample</th>
		<th>HTML Templating sample</th>
		<th>Download/upload sample</th>
		<th>Sample of MVC between two panels with different model types</th>
		</tr>
		<tr>
		<td valign="top">
		<%
			UserRegisterModel model = new UserRegisterModelImpl();
			model.setLogin("static html field to wrap");
			HUserRegisterPanel userRegisterPanel = new HUserRegisterPanel(model);
			userRegisterPanel.setName(HWidgets.HUserRegisterPanel);
			HWidgetGenerator gUserRegister = new HWidgetGenerator(HWidgets.DICTIONARY, userRegisterPanel);
		%>
		<%=gUserRegister.getHTML()%>
		</td>
		<td valign="top">
			<div id="templates" />
		</td>
		<td valign="top">
		<%
			HTabPanel tabPanel = new HTabPanel();
			tabPanel.setName(HWidgets.HTabPanel);
			HWidgetGenerator gTab = new HWidgetGenerator(HWidgets.DICTIONARY, tabPanel);
		%>
		<%=gTab.getHTML()%>
		<br />
		Source code files that are used to download the form as a file on single HTTP request. <b><c:out value="Java object in input and text file as output"/></b>.
		<ul>
		<li>Remote service interface - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/rpc/src/main/java/org/gwtapp/startapp/client/api/DownloadService.java?view=markup">DownloadService.java</a> (input descriptor)</li>
		<li>Remote service implementation - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/srv/src/main/java/org/gwtapp/startapp/server/DownloadServiceImpl.java?view=markup">DownloadServiceImpl.java</a></li>
		<li>UI to download a form - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/src/main/java/org/gwtapp/startapp/client/StartApp.java?view=markup">StartApp.java</a></li>
		<li>The web.xml file - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/war/WEB-INF/web.xml?view=markup">web.xml</a></li>
		</ul>
		Source code files that are used to upload the form from a file on single HTTP request. <b><c:out value="Text file as input and Java object in output by AsyncCallback<T>"/></b> .
		<ul>
		<li>Service implementation - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/srv/src/main/java/org/gwtapp/startapp/server/UploadServiceImpl.java?view=markup">UploadServiceImpl.java</a></li>
		<li>UI-1 to upload a form - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/src/main/java/org/gwtapp/startapp/client/ui/UploadPanel.java?view=markup">UploadPanel.java</a></li>
		<li>UI-2 to upload a form - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/src/main/java/org/gwtapp/startapp/client/StartApp.java?view=markup">StartApp.java</a></li>
		<li>The web.xml file - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/war/WEB-INF/web.xml?view=markup">web.xml</a></li>
		</ul>
		</td>
		<td valign="top">
		Source code files
		<ul>
		<li>Abstract UI Panel - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/core/src/main/java/org/gwtapp/core/client/ui/DelegatedPanel.java?view=markup">DelegatedPanel</a></li>
		</ul>
		</td>
		</tr>
		<tr><td colspan="4">
		<b>Applications that use GWTApp:</b><br />
		<br />
		<a href="http://ccalc-web.appspot.com/">FIFO Currency Calculator</a><br />
		</td></tr>
		<tr><td colspan="4">
			<a href="http://sourceforge.net/projects/gwtapp/develop/">Source code</a><br />
			<small>Radek Olesiak</small>
		</td></tr>
		</table>		
		<p>
			<a href="http://validator.w3.org/check?uri=referer">
				<img src="http://www.w3.org/Icons/valid-xhtml10" alt="Valid XHTML 1.0 Strict" height="31" width="88" />
			</a>
			<a href="http://jigsaw.w3.org/css-validator/check/referer">
		        <img style="border:0;width:88px;height:31px" src="http://jigsaw.w3.org/css-validator/images/vcss" alt="Valid CSS!" />
		    </a>
		</p>
		<%if (request.getServerName()!=null && request.getServerName().contains("gwtapp.org")){ %>
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