<%@page import="org.gwtapp.core.server.template.Template"%>
<div style="background-color:#efefef;">
	@This is the HTML template - begin<br />
	<a href="/templates/startapp.jsp?uid=xyz">Template's source</a>
	<div>
	<input <%=Template.add(request, "t1")%> type="text" style="width:250px;" class="style-t1" value="it should be replaced to the empty field"/>
	</div>
	<div>
		BBB
		<div>
			CCC
			<input <%=Template.add(request, "t2")%> type="text" style="width:250px;" class="style-t2" value="it should be replaced to the empty field"/>
		</div>
	</div>
	@This is the HTML template - end
</div>
