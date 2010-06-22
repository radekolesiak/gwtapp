<t:template tag="b" style="color:#336633;" styleclass="startapp-template">
	<div style="background-color: #e0e0e0;width:400px;">
		@This is HTML template - begin ${param.name}<br />		
		<ul>
			<li>Form Panel Template - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/web/war/templates/startapp.jsp?view=markup">startapp.jsp</a> (<a href="/templates/startapp.jsp">view</a>)</li>
			<li>Login Field Template - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/web/war/templates/login.jsp?view=markup">login.jsp</a> (<a href="/templates/login.jsp">view</a>)</li>
			<li>UI Panel 1 - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/web/src/main/java/org/gwtapp/startapp/client/StartApp.java?view=markup">StartApp.java</a></li>
			<li>UI Panel 2 - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/app/src/main/java/org/gwtapp/startapp/client/ui/user/register/UserRegisterTemplatePanel.java?view=markup">UserRegisterTemplatePanel.java</a></li>
			<li>UserRegister Model - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/rpc/src/main/java/org/gwtapp/startapp/rpc/data/user/register/UserRegister.java?view=markup">UserRegister.java</a></li>
			<li>UserRegister Bean - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/rpc/src/main/java/org/gwtapp/startapp/rpc/data/user/register/UserRegisterModelImpl.java?view=markup">UserRegisterModelImpl.java</a>
			<small>
				<ul>
					<li>Login MetaField - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/rpc/src/main/java/org/gwtapp/startapp/rpc/data/user/register/metafields/LoginMetaField.java?view=markup">LoginMetaField.java</a></li>
					<li>Password MetaField - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/rpc/src/main/java/org/gwtapp/startapp/rpc/data/user/register/metafields/PasswordMetaField.java?view=markup">PasswordMetaField.java</a></li>
					<li>Email MetaField - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/rpc/src/main/java/org/gwtapp/startapp/rpc/data/user/register/metafields/EmailMetaField.java?view=markup">EmailMetaField.java</a></li>
				</ul>
			</small>
			</li>
		</ul>
		<div class="tablediv">
			<div class="rowdiv" t:field="login"></div>
			<div class="rowdiv">
				<div class="celldiv1 left">Password:</div>
				<div class="celldiv2 left"><input t:field="password" type="text" style="width: 200px;" class="style-t2"/></div>
				<div class="clear"></div>
			</div>
			<div class="rowdiv">
				<div class="celldiv1 left">E-mail:</div>
				<div class="celldiv2 left"><input t:field="email" type="text" style="width: 200px;" class="style-t2"/></div>
				<div class="clear"></div>
			</div>
			<div style="color:#3333bb;font-style: normal;" t:field="summary" t:msg="empty:Form is empty. Enter any value to change this message.;any:Form value is {login} {password} {email};login:login=<i>{1}</i>;password:password=<i>{1}</i>;email:email=<i>{1}</i>;"></div>
		</div>
		@This is HTML template - end
	</div>
</t:template>