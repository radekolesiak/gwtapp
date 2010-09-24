<t:template styleclass="user-panel">
	<div class="validation-group user-login">
		<div>Login: <input type="text" t:field="login"></input></div>
		<div class="validation user-login-empty">Empty Login</div>
		<div class="validation user-login-too-short">Too Short Login, minimum 3 characters</div>
		<div class="validation user-login-too-long">Too Long Login, maximum 25 characters</div>
		<div class="validation user-login-already-exists">Already Exists Login</div>
		<div class="validation user-login-not-letters-only">Letters are allowed only</div>
		<div class="validation user-login-not-lower-case">Lower case is allowed only</div>
	</div>
	<div class="validation-group user-email">
		<div>Email: <input type="text" t:field="email"></input></div>
		<div class="validation user-email-empty">Empty e-mail</div>
		<div class="validation user-email-invalid">Invalid e-mail</div>
		<div class="validation user-email-already-exists">Already Exists Email</div>
	</div>
	<%--<div>Name: <input type="text" t:field="name"></input></div>--%>
</t:template>