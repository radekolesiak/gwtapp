<%@tag pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="repository" required="true" type="java.lang.String"%>
<%@attribute name="template" required="true" type="java.lang.String"%>

[script type="text/javascript"]
var Templates; 
if(!Templates){Templates = new Array();}
if(!Templates["${repository}"]){Templates["${repository}"] = new Array();}
<c:set var="body"><jsp:include page="${repository}${template}"><jsp:param name="dictionary" value="true"/></jsp:include></c:set>
Templates["${repository}"]["${template}"] = "<c:out value="${body}"/>";
[/script]