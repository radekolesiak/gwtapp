<t:template styleclass="user-panel">
<div t:field="validator">
	<div>Login: <input type="text" t:field="login"></input></div>
	<div class="validation login-invalid">Invalid Login</div>
	<div class="validation login-too-short">Too Short Login</div>
	<div>Email: <input type="text" t:field="email"></input></div>
	<div>Name: <input type="text" t:field="name"></input></div>
</div>
</t:template>