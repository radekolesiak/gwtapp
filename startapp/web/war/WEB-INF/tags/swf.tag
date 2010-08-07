<%@tag pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="src" required="true" type="java.lang.String"%>
<%@attribute name="id" required="false" type="java.lang.String"%>
<%@attribute name="width" required="false" type="java.lang.String"%>
<%@attribute name="height" required="false" type="java.lang.String"%>
<c:if test="${not empty id}"><c:set var="width">width="${id}"</c:set></c:if>
<c:if test="${not empty width}"><c:set var="width">width="${width}"</c:set></c:if>
<c:if test="${not empty height}"><c:set var="width">height="${height}"</c:set></c:if>
<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0"
	${id} ${width} ${height}>
	<param name="movie" value="${src}" />
	<embed type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer"
		src="${src}" ${width} ${height}
	></embed>
</object>

