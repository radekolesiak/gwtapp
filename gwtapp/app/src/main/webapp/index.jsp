<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>StartApp</title>
<script>
	function clone (deep) {
		  var objectClone = new this.constructor();
		  for (var property in this)
		    if (!deep)
		      objectClone[property] = this[property];
		    else if (typeof this[property] == 'object')
		      objectClone[property] = this[property].clone(deep);
		    else
		      objectClone[property] = this[property];
		  return objectClone;
		}
	Object.prototype.clone = clone;					
</script>
<script language='javascript'
	src='org.gwtapp.startapp.StartApp/org.gwtapp.startapp.StartApp.nocache.js'></script>
</head>
<body>
</body>
</html>