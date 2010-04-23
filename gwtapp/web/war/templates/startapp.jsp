<%@taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:template tag="b" style="color:#663333;" styleclass="startapp-template">
	<c:choose>
		<c:when test="${param.type == 'internal'}"><c:set var="style" value="background-color:#efef8f;"/></c:when>
		<c:when test="${param.type == 'external'}"><c:set var="style" value="background-color:#efef8f;"/></c:when>
		<c:otherwise><c:set var="style" value="background-color:#efefef;"/></c:otherwise>
	</c:choose>
	<div style="${style}">
		@This is HTML template - begin<br />
		<ul>
			<li>Template - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/war/templates/startapp.jsp?view=markup">startapp.jsp</a> (<a href="/templates/startapp.jsp">view</a>)</li>
			<li>UI Panel 1 - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/src/main/java/org/gwtapp/startapp/client/StartApp.java?view=markup">StartApp.java</a></li>
			<c:if test="${param.type == 'internal'}"><li>UI Panel 2 - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/src/main/java/org/gwtapp/startapp/client/ui/InternalTemplatingPanel.java?view=markup">InternalTemplatingPanel.java</a></li></c:if>
			<c:if test="${param.type == 'external'}"><li>UI Panel 2 - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/src/main/java/org/gwtapp/startapp/client/ui/ExternalTemplatingPanel.java?view=markup">ExternalTemplatingPanel.java</a></li></c:if>
		</ul>
		<div>
			<input t:template="login" type="text" style="width:250px;" class="style-t1" value="it should be replaced to the empty field"/>
		</div>
		<div>
			BBB
			<div>
				CCC
				<input t:template="password" type="text" style="width:250px;" class="style-t2" value="it should be replaced to the empty field"/>
			</div>
		</div>
		@This is HTML template - end
	</div>
</t:template>