<%@tag pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="cleanup" class="org.gwtapp.template.server.CleanUpBean"/> 
<c:set var="books" value="1933988290,1933988231,1590599756,0132344815,0321501969" />
<c:set target="${cleanup}" property="text">
	&nbsp;
	<c:forEach var="book" items="${books}">
		<c:url var="src" value="http://rcm.amazon.com/e/cm">
			<c:param name="t" value="g097a-20"></c:param>
			<c:param name="o" value="1"></c:param>
			<c:param name="p" value="8"></c:param>
			<c:param name="l" value="as1"></c:param>
			<c:param name="fc1" value="000000"></c:param>
			<c:param name="IS2" value="1"></c:param>
			<c:param name="lt1" value="_blank"></c:param>
			<c:param name="m" value="amazon"></c:param>
			<c:param name="lc1" value="0000FF"></c:param>
			<c:param name="bc1" value="000000"></c:param>
			<c:param name="bg1" value="FFFFFF"></c:param>
			<c:param name="f" value="ifr"></c:param>
			<c:param name="asins" value="${book}"></c:param>
		</c:url>
		<div><iframe src="<c:out value="${src}" escapeXml="true"></c:out>" style="width:120px;height:240px;" scrolling="no" marginwidth="0" marginheight="0" frameborder="0"></iframe></div>
		<br />
	</c:forEach>
</c:set>
<div id="amazon-books"></div>
<script type="text/javascript">
//<![CDATA[ 
	document.getElementById('amazon-books').innerHTML='${cleanup.text}';
//]]> 
</script>