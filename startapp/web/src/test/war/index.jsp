<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:t="">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<t:include repository="/templates/" template="user/ReCaptchaUserPanel.jsp"/>
<t:include repository="/templates/" template="user/UserPanel.jsp"/>
<title>StartApp Manual Testing</title>
<style type="text/css"> 
@import "recaptchauserregister.css";
</style>
<script type="text/javascript" src="http://www.google.com/recaptcha/api/js/recaptcha_ajax.js"></script>
<script type="text/javascript">
function showReCaptcha(element, gwtCallback) {
	return Recaptcha.create("6LczcLwSAAAAAN_L7K-7iynvj5ResMcKNSskUSO8", element, 
		{
			theme: "red",
			callback: gwtCallback
		}
	);
}
</script>
<script  type="text/javascript" src="gwt.startappmanualtestentry/gwt.startappmanualtestentry.nocache.js"></script>
</head>
<body>
Manual testing
</body>
</html>