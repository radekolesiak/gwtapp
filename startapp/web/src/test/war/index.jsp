<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:t="">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<t:include repository="/templates/" template="user/ReCaptchaUserPanel.jsp"/>
<t:include repository="/templates/" template="user/UserPanel.jsp"/>
<t:include repository="/templates/" template="ListPanel.jsp"/>
<title>StartApp Manual Testing</title>
<style type="text/css"> 
@import "/css/recaptchauserregister.css";
@import "/css/startapp.css";
@import "/css/validation-by-group.css?separator=.validation-group.&style=background-color:orange;border:2px solid red;margin:1px;";
@import "/css/validation-by-enum.css?separator=.validation.&style=display:block;";
</style>
<script type="text/javascript" src="http://www.google.com/recaptcha/api/js/recaptcha_ajax.js"></script>
<script type="text/javascript">
function showReCaptcha(element, gwtCallback) {
	return Recaptcha.create("6LexqMESAAAAAEG-wSaclbA_SC03JMA2Mn8GAuc9", element, 
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