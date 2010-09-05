<t:template styleclass="user-panel">
<div t:field="validator">
	<div class="validation-group login">
		<div>Login: <input type="text" t:field="login"></input></div>
		<div class="validation login-empty">Empty Login</div>
		<div class="validation login-too-short">Too Short Login</div>
		<div class="validation login-already-exists">Already Exists Login</div>
		<div class="validation login-not-letters-only">Letters are allowed only</div>
		<div class="validation login-not-lower-case">Lower case is allowed only</div>
	</div>
	<div class="validation-group email">
		<div>Email: <input type="text" t:field="email"></input></div>
		<div class="validation email-empty">Empty e-mail</div>
		<div class="validation email-invalid">Invalid e-mail</div>
		<div class="validation email-already-exists">Already Exists Email</div>
	</div>
	<div>Name: <input type="text" t:field="name"></input></div>
</div>
</t:template>