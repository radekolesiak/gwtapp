<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title></title>
<style type="text/css">
.tablediv {
	display: table;
	width: 600px;
	background-color: #eee;
	border: 1px solid #666666;
	border-spacing: 5px; /*cellspacing:poor IE support for  this*/
	border-collapse: separate;
}

.celldiv1 {
/*	float: left; /*fix for  buggy browsers*/
	display: table-cell;
	width: 50px;
	background-color: #ccc;
}

.celldiv2 {
/*	float: left; /*fix for  buggy browsers*/
	display: table-cell;
	width: auto;
	background-color: #ccc;
}

.rowdiv {
	display: table-row;
	width: auto;
}
</style>
</head>
<body>
	<div class="tablediv">
		<div class="rowdiv">
			<div class="celldiv1">cell <b>#1#1</b> of content in a table (30% width)</div>
			<div class="celldiv2"><div style="width:100%;">cell <b>#1#2</b> of content in a table (70% width)</div></div>
		</div>
		<div class="rowdiv">
			<div class="celldiv1">cell <b>#2#1</b> of content in a table (30% width)</div>
			<div class="celldiv2">cell <b>#2#2</b> of content in a table (70% width)</div>
		</div>
	</div>
</body>
</html>
