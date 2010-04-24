<%@tag pageEncoding="UTF-8" %>
<%@tag body-content="empty" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@attribute name="repository" required="true" type="java.lang.String"%>
<%@attribute name="template" required="true" type="java.lang.String"%>

<jsp:useBean id="tag" class="org.gwtapp.template.server.CleanUp" scope="request"/> 
<jsp:useBean id="style" class="org.gwtapp.template.server.CleanUp" scope="request"/> 
<jsp:useBean id="styleclass" class="org.gwtapp.template.server.CleanUp" scope="request"/> 
<jsp:useBean id="body" class="org.gwtapp.template.server.CleanUp" scope="request"/> 

<c:set target="${tag}" property="text"><jsp:include page="${repository}${template}"><jsp:param name="templating" value="tag"/></jsp:include></c:set>
<c:set target="${style}" property="text"><jsp:include page="${repository}${template}"><jsp:param name="templating" value="style"/></jsp:include></c:set>
<c:set target="${styleclass}" property="text"><jsp:include page="${repository}${template}"><jsp:param name="templating" value="styleclass"/></jsp:include></c:set>
<c:set target="${body}" property="text"><jsp:include page="${repository}${template}"><jsp:param name="templating" value="body"/></jsp:include></c:set>

<script type="text/javascript">
var Templates; 
if(!Templates){Templates = new Array();}
if(!Templates["${repository}"]){Templates["${repository}"] = new Array();}
if(!Templates["${repository}"]["${template}"]){Templates["${repository}"]["${template}"] = new Array();}
Templates["${repository}"]["${template}"]["tag"] = "<c:out value="${tag.text}" />";
Templates["${repository}"]["${template}"]["style"] = "<c:out value="${style.text}"/>";
Templates["${repository}"]["${template}"]["styleclass"] = "<c:out value="${styleclass.text}"/>";
Templates["${repository}"]["${template}"]["body"] = "<c:out value="${body.text}" />";
</script>