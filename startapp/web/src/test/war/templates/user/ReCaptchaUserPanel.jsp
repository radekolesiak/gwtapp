<t:template styleclass="recaptcha-user-panel">
<div t:field="user"></div>
<div>Password: <input type="password" t:field="password"></input></div>
<div>Password (verify): <input type="password" t:field="password-verify"></input></div>
<div t:field="reCaptchaContainer"></div>
<button class="register" t:field="register" t:msg="reCaptchaStyle:recaptcha-loaded;">Register</button>
</t:template>