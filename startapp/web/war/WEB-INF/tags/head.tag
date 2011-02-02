<%@tag pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="title" required="false" type="java.lang.String"%>
<%@attribute name="module" required="false" type="java.lang.String"%>
<head>
	<title>${title}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta name="keywords" content="GWT,html,templating,html templating,upload,download,requests,request,single request,rpc,wrap,wrapping,wrapper,ajax,javascript,ria,mvc,java,reflection" />
	<style type="text/css"> 
		@import "startapp.css";
	</style>
	<meta name="google-site-verification" content="MQgpkCJ4iFxqkOgblvs97GQ0z95dpCep6IuF_lFScaM" />
	<!-- StartApp GWT application -->
	<c:if test="${not empty module}" >
		<jsp:useBean id="ae" class="org.gwtapp.startapp.server.AppEngineBean"/>
		<script type="text/javascript" src="gwt.startappentry/gwt.startappentry.nocache.js?t=${ae.deployVersion}"></script>
	</c:if>
	<jsp:doBody/>
</head>
