<t:template styleclass="recaptcha-user-panel">
<div t:field="validator">
	<div t:field="user"></div>
	<div class="validation-group password">
		<div>Password: <input type="password" t:field="password"></input></div>
		<div class="validation password-empty">Password is empty</div>
		<div class="validation password-not-between-range">Password length must between 6 and 20 characters</div>
		<div>Password (verify): <input type="password" t:field="password-verify"></input></div>
		<div class="validation password-not-equals">Password verify invalid</div>
	</div>
	<div class="validation-group recaptcha">
		<div t:field="reCaptchaContainer"></div>
		<div class="validation recaptcha-invalid">Invalid Re-Captcha</div>
	</div>
	<button class="register" t:field="register" t:msg="reCaptchaStyle:recaptcha-loaded;">Register</button>
</div>
</t:template>