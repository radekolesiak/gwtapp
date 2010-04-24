<%@tag pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@attribute name="tag" required="false" type="java.lang.String"%>
<%@attribute name="style" required="false" type="java.lang.String"%>
<%@attribute name="styleclass" required="false" type="java.lang.String"%>
<%@attribute name="templating" required="false" type="java.lang.String"%>

<c:if test="${empty tag}" ><c:set var="tag" value="${param.tag}" /></c:if>
<c:if test="${empty style}" ><c:set var="style" value="${param.style}" /></c:if>
<c:if test="${empty styleclass}" ><c:set var="styleclass" value="${param.styleclass}" /></c:if>
<c:if test="${empty templating}" ><c:set var="templating" value="${param.templating}" /></c:if>
<c:if test="${empty tag}" ><c:set var="tag" value="div" /></c:if>

<c:choose>
	<c:when test="${empty templating}">
		<jsp:useBean id="templateHeader" class="org.gwtapp.template.server.Header" scope="request"/>
		<c:set target="${templateHeader}" property="response" value="${response}"/>
		<c:set target="${templateHeader}" property="tag" value="${tag}"/>
		<c:set target="${templateHeader}" property="style" value="${style}"/>
		<c:set target="${templateHeader}" property="styleclass" value="${styleclass}"/>
		<jsp:doBody/>	
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${templating == 'tag'}">
				<c:out value="${tag}"/>
			</c:when>
			<c:when test="${templating == 'style'}">
				<c:out value="${style}"/>
			</c:when>
			<c:when test="${templating == 'styleclass'}">
				<c:out value="${styleclass}"/>
			</c:when>
			<c:when test="${templating == 'body'}">
				<jsp:doBody/>
			</c:when>
			<c:otherwise>
				<${tag} <c:if test="${not empty style}">style="${style}"</c:if> <c:if test="${not empty styleclass}">class="${styleclass}"</c:if> >
				<jsp:doBody/>
				</${tag}>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>