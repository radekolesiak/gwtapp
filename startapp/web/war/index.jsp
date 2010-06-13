<!doctype html>
<%@page pageEncoding="utf-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta name="keywords" content="GWT,html,templating,html templating,upload,download,requests,request,single request,rpc,wrap,wrapping,wrapper,ajax,javascript,ria,mvc,java,reflection" />
		<title>StartApp</title>
		<style type="text/css"> 
			@import "startapp.css";
		</style>
		<script type="text/javascript" src="gwt.startappentry/gwt.startappentry.nocache.js?rand=<%=Math.random()%>"></script>
		<t:include repository="/templates/" template="startapp.jsp"/>
		<t:include repository="/templates/" template="login.jsp"/>
		<t:include repository="/templates/" template="feedback.jsp"/>
	</head>
	<body>
		<!--[if lt IE 8]>
		<h3 style="color:orange;">Please use Internet Explorer 8.0 or newer version to display this page properly.</h3>
		<![endif]-->
	    <noscript>
      		<div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
        		Your web browser must have JavaScript enabled in order for this application to display correctly.
      		</div>
    	</noscript>
    	<div id="laodingindicator"><div >Loading application ... <img src="images/indicator.gif"/></div></div>	
		<div class="table main">
			<div class="row">
				<div class="cell">
    				<div style="width:680px;margin-left:auto;margin-right:auto;">
    					<div style="margin-left:25px;margin-top:10px;"><b>GWT App</b> provides several features like:</div>
	    				<ul>
							<li>Declarative HTML templating and internationalization at runtime without application recompiling.</li>
							<li>MetaField for auto bind and simulate annotation of RPC data fields.</li>
							<li>Strong typing in MVC including Java generics.</li>
							<li>Modular structure in Maven.</li>
							<li>Passing serialized RPC data into HTML main page for no delay access of initial data <i>(planning)</i>.</li>
						</ul>
    				</div>
    			</div>
    		</div> 
    	</div> 
		<div class="table main">
			<div class="row header">
				<div class="cell">HTML Templating sample<br /><small>Template is generated on server side outside of GWT<br />and included as JavaScript data of this page</small></div>
				<div class="cell">Upload and download sample<br /><small><c:out value="Converting a file from/to RPC Java object at single HTTP request-response"></c:out></small></div>
				<div class="cell">Transformation of two models with different types in UI panel to keep strong typing in MVC</div>
			</div>
			<div class="row">
				<div class="cell">
					<div id="templates" ></div>
				</div>
				<div class="cell">
					<div id="ud" >
						<!-- This is upload/download form template sample BEGIN-->
						<div class="rowdiv">
							<div class="celldiv1">Login:</div>
							<div class="celldiv2"><input t:field="login" type="text" style="width: 250px;" class="style-t2"/></div>
						</div>
						<div class="clear"></div>
						<div class="rowdiv">
							<div class="celldiv1">Password:</div>
							<div class="celldiv2"><input t:field="password" type="text" style="width: 250px;" class="style-t2"/></div>
						</div>
						<div class="clear"></div>
						<div class="rowdiv">
							<div class="celldiv1">E-mail:</div>
							<div class="celldiv2"><input t:field="email" type="text" style="width: 250px;" class="style-t2"/></div>
						</div>
						<div class="clear"></div>
						<br />
						<div class="rowdiv">
							<div class="celldiv1">File:</div>
							<div class="celldiv2">
								<div class="left" style="margin-right:10px;"><a	t:field="clear-btn" href="javascript:void(0);"><img id="clear-btn" src="images/clear.png" alt="Clear form"/></a></div>
								<div class="left" style="margin-right:10px;"><a	t:field="download-btn" href="javascript:void(0);"><img id="download-btn" src="images/download.png" alt="Download"/></a></div>	
								<div class="left"><form t:field="upload-form" action="/gwt.startappentry/upload.rpc" class="upload"></form></div>
								<div class="left"><a t:field="upload-btn" href="javascript:void(0);"><img id="upload-btn" src="images/upload.png" alt="Upload"/></a></div>
								<div class="clear"></div>
							</div>
						</div>
						<div class="clear"></div>
						<!-- This is upload/download form template sample END-->
					</div>
					<br />
					Source code files that are used to download the form as a file on single HTTP request. <b><c:out value="Java object in input and text file as output"/></b>.
					<ul>
					<li>A download HTML template embedded in the main page- <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/web/war/index.jsp?view=markup">index.jsp</a></li>
					<li>Remote service interface - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/rpc/src/main/java/org/gwtapp/startapp/rpc/api/DownloadService.java?view=markup">DownloadService.java</a></li>
					<li>Remote service implementation - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/srv/src/main/java/org/gwtapp/startapp/server/DownloadServiceImpl.java?view=markup">DownloadServiceImpl.java</a></li>
					<li>UI to download a form - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/app/src/main/java/org/gwtapp/startapp/client/ui/user/register/UploadDownloadTemplatePanel.java?view=markup">UploadDownloadTemplatePanel.java</a></li>
					<li>The web.xml file - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/web/war/WEB-INF/web.xml?view=markup">web.xml</a></li>
					</ul>
					Source code files that are used to upload the form from a file on single HTTP request. <b><c:out value="Text file as input and Java object in output by AsyncCallback<T>"/></b> .
					<ul>
					<li>An upload HTML template embedded in the main page- <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/web/war/index.jsp?view=markup">index.jsp</a></li>
					<li>Service implementation - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/srv/src/main/java/org/gwtapp/startapp/server/UploadServiceImpl.java?view=markup">UploadServiceImpl.java</a></li>
					<li>UI-1 to upload a form - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/app/src/main/java/org/gwtapp/startapp/client/ui/user/register/UploadDownloadTemplatePanel.java?view=markup">UploadDownloadTemplatePanel.java</a></li>
					<li>UI-2 to upload a form - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/app/src/main/java/org/gwtapp/startapp/client/ui/user/register/UploadFormWrapper.java?view=markup">UploadFormWrapper.java</a></li>
					<li>The web.xml file - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/web/war/WEB-INF/web.xml?view=markup">web.xml</a></li>
					</ul>
				</div>
				<div class="cell">
					Source code file
					<ul>
					<li>UI Panel - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/core/src/main/java/org/gwtapp/core/client/ui/DelegatedPanel.java?view=markup">DelegatedPanel</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="table main" style="width:100%;border: 1px solid #666666;background-color: #ccc;">
			<div class="row">
				<div class="cell">
					<b>Articles related to the GWT:</b><br />
					<a href="http://techzone.enterra-inc.com/gwt-improving-performance/">GWT Improving performance</a><br />
					<a href="http://www.techhui.com/profiles/blogs/simpler-and-speedier-gwt-with">Simpler and Speedier GWT with Server Side RPC Serialization</a><br />
				</div>
			</div>
			<div class="row">
				<div class="cell">
					<b>Applications that use GWTApp:</b><br />
					<a href="http://ccalc-web.appspot.com/">FIFO Currency Calculator</a><br />
				</div>
			</div>
			<div class="row">
				<div class="cell">
					Get a <a href="http://sourceforge.net/projects/gwtapp/develop/">source code.</a>
					<br />					
					Get a maven repository <a href="http://mvn.gwtapp.org/org/gwtapp/">http://mvn.gwtapp.org/</a> 
					<br />
					<small>Author: <a href="mailto:r.olesiak@gmail.com">Radek Olesiak</a></small>
					<div id="feedbackanchor"></div>
				</div>
			</div>		
		</div>
		<p>
			<a href="http://validator.w3.org/check?uri=referer">
				<img src="http://www.w3.org/Icons/valid-xhtml10" alt="Valid XHTML 1.0 Strict" height="31" width="88" />
			</a>
			<a href="http://jigsaw.w3.org/css-validator/check/referer">
		        <img style="border:0;width:88px;height:31px" src="http://jigsaw.w3.org/css-validator/images/vcss" alt="Valid CSS!" />
		    </a>
		    <br />
		    <small>Ignore <b>t:field</b> for now, XSD will be soon.</small>
		</p>
		<%if (request.getServerName()!=null && request.getServerName().contains("gwtapp.org")){ %>
			<!-- GA  -->
			<script type="text/javascript">
			var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
			document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
			</script>
			<script type="text/javascript">
			try {
			var pageTracker = _gat._getTracker("UA-11571468-2");
			pageTracker._trackPageview();
			} catch(err) {}
			</script>
			<!-- GA  -->
		<%}%>
	</body>
</html>