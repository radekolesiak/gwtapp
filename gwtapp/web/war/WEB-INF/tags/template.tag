<%@tag pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@tag import="org.apache.commons.lang.StringUtils"%>
<%@tag import="org.gwtapp.template.client.Template"%>
<%@attribute name="tag" required="false" type="java.lang.String"%>
<%@attribute name="style" required="false" type="java.lang.String"%>
<%@attribute name="styleclass" required="false" type="java.lang.String"%>
<%@attribute name="templating" required="false" type="java.lang.String"%>

<c:if test="${empty tag}" >
	<c:set var="tag" value="div" />
</c:if>

<c:choose>
	<c:when test="${empty templating}">
		<%
		response.addHeader(Template.Header.TAG, StringUtils.defaultString((String)jspContext.getAttribute("tag")));
		response.addHeader(Template.Header.STYLE, StringUtils.defaultString((String)jspContext.getAttribute("style")));
		response.addHeader(Template.Header.STYLE_CLASS, StringUtils.defaultString((String)jspContext.getAttribute("styleclass")));
		%>
		<jsp:doBody/>	
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${templating == 'tag'}">
				<c:out value="${tag}" escapeXml="true" />
			</c:when>
			<c:when test="${templating == 'style'}">
				<c:out value="${style}" escapeXml="true" />
			</c:when>
			<c:when test="${templating == 'styleclass'}">
				<c:out value="${styleclass}" escapeXml="true" />
			</c:when>
			<c:otherwise>
				<${tag} <c:if test="${not empty style}">style="${style}"</c:if> <c:if test="${not empty styleclass}">class="${styleclass}"</c:if> >
				<jsp:doBody/>
				</${tag}>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>