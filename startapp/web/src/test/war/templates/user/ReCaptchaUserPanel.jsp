<t:template styleclass="recaptcha-user-panel">
<div>Login: <input type="text" t:field="login"></input></div>
<div>Email: <input type="text" t:field="email"></input></div>
<div>Name: <input type="text" t:field="name"></input></div>
<div t:field="reCaptchaContainer"></div>
<button class="register" t:field="register" t:msg="reCaptchaStyle:recaptcha-loaded;">Register</button>
</t:template>