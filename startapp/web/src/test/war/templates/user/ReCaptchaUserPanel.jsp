<t:template styleclass="recaptcha-user-panel">
<div t:field="validator">
	<div t:field="user"></div>
	<div>Password: <input type="password" t:field="password"></input></div>
	<div>Password (verify): <input type="password" t:field="password-verify"></input></div>
		<div class="validation-group recaptcha">
			<div t:field="reCaptchaContainer"></div>
			<div class="validation recaptcha-invalid">Invalid Re-Captcha</div>
		</div>
	<button class="register" t:field="register" t:msg="reCaptchaStyle:recaptcha-loaded;">Register</button>
</div>
</t:template>