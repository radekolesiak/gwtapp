<%@tag pageEncoding="UTF-8" %>
<%@tag body-content="empty" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@tag import="org.apache.commons.lang.StringUtils"%>
<%@attribute name="repository" required="true" type="java.lang.String"%>
<%@attribute name="template" required="true" type="java.lang.String"%>

<script type="text/javascript">
var Templates; 
if(!Templates){Templates = new Array();}
if(!Templates["${repository}"]){Templates["${repository}"] = new Array();}
if(!Templates["${repository}"]["${template}"]){Templates["${repository}"]["${template}"] = new Array();}
<c:set var="tag"><jsp:include page="${repository}${template}"><jsp:param name="templating" value="tag"/></jsp:include></c:set>
<c:set var="style"><jsp:include page="${repository}${template}"><jsp:param name="templating" value="style"/></jsp:include></c:set>
<c:set var="styleclass"><jsp:include page="${repository}${template}"><jsp:param name="templating" value="styleclass"/></jsp:include></c:set>
<c:set var="body"><jsp:include page="${repository}${template}"><jsp:param name="templating" value="body"/></jsp:include></c:set>
<%
getJspContext().setAttribute("tag",StringUtils.replaceChars((String)getJspContext().getAttribute("tag"),"\r\n","")) ;
getJspContext().setAttribute("style",StringUtils.replaceChars((String)getJspContext().getAttribute("style"),"\r\n","")) ;
getJspContext().setAttribute("styleclass",StringUtils.replaceChars((String)getJspContext().getAttribute("styleclass"),"\r\n","")) ;
getJspContext().setAttribute("body",StringUtils.replaceChars((String)getJspContext().getAttribute("body"),"\r\n","")) ;
%>
Templates["${repository}"]["${template}"]["tag"] = "<c:out value="${tag}" />";
Templates["${repository}"]["${template}"]["style"] = "<c:out value="${style}"/>";
Templates["${repository}"]["${template}"]["styleclass"] = "<c:out value="${styleclass}"/>";
Templates["${repository}"]["${template}"]["body"] = "<c:out value="${body}" />";
</script>
