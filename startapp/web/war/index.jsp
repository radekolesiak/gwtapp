<!DOCTYPE html SYSTEM "http://web.gwtapp.org/dtd/gwtapp.dtd"> 
<%@page pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:t="">
	<t:head module="startapp" title="StartApp">
		<t:include repository="/templates/" template="startapp.jsp"/>
		<t:include repository="/templates/" template="login.jsp"/>
		<t:include repository="/templates/" template="feedback.jsp"/>
		
		<!-- UserRegister model serialized into this HTML page -->		
		<jsp:useBean id="userregister" class="org.gwtapp.startapp.server.UserRegisterBean"/>
		<c:set target="${userregister}" property="login" value="Sample Login Value"/>
		<c:set target="${userregister}" property="email" value="Sample Email Value"/>
		<c:set target="${userregister}" property="password" value="Sample Password Value"/>
		<script type="text/javascript">
			var RpcValues = new Array();
			RpcValues["userregister"] = "${userregister.asHtmlRpc}";
		</script>
	</t:head>
	<body>
		<!--[if lt IE 8]>
		<h3 style="color:orange;">Please use Internet Explorer 8.0 or newer version to display this page properly.</h3>
		<![endif]-->
	    <noscript>
      		<div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
        		Your web browser must have JavaScript enabled in order for this application to display correctly.
      		</div>
    	</noscript>
    	<div id="laodingindicator"><div>Loading application ... <img src="images/indicator.gif" alt=""/></div></div>	
		<div class="table main">
			<div class="row">
				<div class="cell">
    				<div style="width:870px;margin-left:auto;margin-right:auto;">
    					<div style="margin-left:25px;margin-top:10px;"><b>GWT App</b> provides several features and benefits like:</div>
	    				<ul>
							<li>Declarative HTML templating at runtime without application recompiling and templates content generated dynamically by JSP and JSTL.</li>
							<li>Holds GWT's primarily as a reliable business logic framework by moving out a view decorations into HTML templates.</li>
							<li>SEO support by means of HTML templates embeddable into the main page.</li>
							<li>MetaField to simulate Java annotation and for RPC data auto binding.</li>
							<li>Strong typing in MVC including Java generics.</li>
							<li>Passing serialized RPC data into HTML main page for no delay access to the initial data.</li>
							<!-- <li>Very simple one-to-one mapping of GWT RPC servlets to REST. The <a href="rest.jsp">sample</a>.</li> -->
							<li>Like-tree hierarchical MVC structure (<a href="http://en.wikipedia.org/wiki/Presentation-abstraction-control">PAC/HMVC</a>).</li>
							<li>Pipe channels to skip over the model flow between units in the like-tree MVC hierarchy.</li>
							<li>Modular acyclic structure in Maven.</li>
						</ul>
    				</div>
    			</div>
    		</div> 
    	</div> 
		<div class="table">
			<div class="row">
				<div class="cell">
					<!--
			    	<div class="center menu" style="width:200px;"><b>&nbsp;</b></div>
					<div class="table main">
						<div class="row header">
							<div class="cell">
								<div>Online Help Providing</div>
								<div style="padding: 15px 0;font-weight: bold;">If you are interested to get a help or improve code in your GWT project please contact <a href="contact.jsp">here</a>.</div>
							</div>
						</div>
					</div>
					-->
			    	<div class="center menu" style="width:200px;"><b>&nbsp;</b></div>
					<div class="table main">
						<div class="row header">
							<div class="cell">
								Sample of HTML Templating.<br />
								<small>The template is generated on the server side<br />
								and included as a JavaScript data for this page.</small>
							</div>
							<div class="cell">
								Sample of upload and download a form.<br />
								<small>It converts a file from/to RPC Java object at a single HTTP request-response.</small><br />
								<small>The template is embedded in the main page as a HTML code.</small>
							</div>
							<div class="cell">
								Sample of transformation of two different type models for UI panel with strong typing in MVC.
							</div>
						</div>
						<div class="row">
							<div class="cell">
								<div id="templates" ></div>
							</div>
							<div class="cell">
								<!-- This is upload/download form template sample BEGIN-->
								<div id="ud" style="border:1px dotted #bbddbb;padding:5px;">
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
										<div class="celldiv1">Form:</div>
										<div class="celldiv2">
											<div class="left" style="margin-right:10px;"><a	t:field="clear-btn" href="javascript:void(0);"><img id="clear-btn" src="images/clear.png" alt="Clear form"/></a></div>
											<div class="left" style="margin-right:10px;"><a	t:field="download-btn" href="javascript:void(0);"><img id="download-btn" src="images/download.png" alt="Download"/></a></div>	
											<div class="left">
												<form t:field="upload-form" action="/gwt.startappentry/upload.rpc" class="upload">
													<fieldset>
														<input t:field="upload-file" type="file" name="StartAppFile"/>
													</fieldset>
												</form>
											</div>
											<div class="left"><a t:field="upload-btn" href="javascript:void(0);"><img id="upload-btn" src="images/upload.png" alt="Upload"/></a></div>
											<div class="clear"></div>
										</div>
									</div>
									<div class="clear"></div>
								</div>
								<!-- This is upload/download form template sample END-->
								<br />
								Source code files that are used to download the form as a file on single HTTP request. <b><c:out value="Java object in input and text file as output"/></b>.
								<ul>
								<li>A download HTML template embedded in the main page - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/web/war/index.jsp?view=markup">index.jsp</a></li>
								<li>Remote service interface - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/rpc/src/main/java/org/gwtapp/startapp/rpc/api/DownloadService.java?view=markup">DownloadService.java</a></li>
								<li>Remote service implementation - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/srv/src/main/java/org/gwtapp/startapp/server/DownloadServiceImpl.java?view=markup">DownloadServiceImpl.java</a></li>
								<li>UI to download a form - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/app/src/main/java/org/gwtapp/startapp/client/ui/user/register/UploadDownloadTemplatePanel.java?view=markup">UploadDownloadTemplatePanel.java</a></li>
								<li>The web.xml file - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/web/war/WEB-INF/web.xml?view=markup">web.xml</a></li>
								</ul>
								Source code files that are used to upload the form from a file on single HTTP request. <b><c:out value="Text file as input and Java object in output by AsyncCallback<T>"/></b> .
								<ul>
								<li>An upload HTML template embedded in the main page - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/web/war/index.jsp?view=markup">index.jsp</a></li>
								<li>Service implementation - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/srv/src/main/java/org/gwtapp/startapp/server/UploadServiceImpl.java?view=markup">UploadServiceImpl.java</a></li>
								<li>UI-1 to upload a form - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/app/src/main/java/org/gwtapp/startapp/client/ui/user/register/UploadDownloadTemplatePanel.java?view=markup">UploadDownloadTemplatePanel.java</a></li>
								<li>UI-2 to upload a form - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/app/src/main/java/org/gwtapp/startapp/client/ui/user/register/UploadFormWrapper.java?view=markup">UploadFormWrapper.java</a></li>
								<li>The web.xml file - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/web/war/WEB-INF/web.xml?view=markup">web.xml</a></li>
								</ul>
							</div>
							<div class="cell">
								<div style="padding:7px;">
									<div class="left">Double data type input box:&nbsp;</div> 
									<div class="left">
										<div id="delegated"></div>
									</div>
									<div class="clear">
										<br />
										<c:out value="The TextBox implements HasValue<String>"></c:out><br />
										<c:out value="The DoubleBox implements HasValue<Double>"></c:out><br />
										<br />
									</div>
								</div>
								Source code files:
								<ul>
								<li>UI abstract delegated panel - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/core/src/main/java/org/gwtapp/core/client/ui/DelegatedPanel.java?view=markup">DelegatedPanel</a></li>
								<li>UI input box of Double type - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/startapp/app/src/main/java/org/gwtapp/startapp/client/ui/delegated/DoubleBox.java?view=markup">DoubleBox</a></li>
								</ul>
							</div>
						</div>
					</div>
			    	<div class="center menu" style="width:200px;"><b>&nbsp;</b></div>
					<div class="table main" style="width:100%;border: 1px solid #666666;background-color: #ccc;">
						<div class="row">
							<div class="cell">
								<b>Like-tree hierarchical MVC UI components structure</b>
								<ul>
									<li>any controller can split, merge and transform a domain model</li>
									<li>domain models are <a href="http://en.wikipedia.org/wiki/Transitive_relation">transitive</a> on any path of tree structure</li>
									<li>domain models are passive and:
									<ul>
										<li>view and controller can operate on local state and domain model of their own component upon events and methods</li>
										<li>controller can return a model and state or send related event to another controller</li>
										<li>controller can update and read a model and state of another UI component unit</li>										
									</ul> 
								</ul>
							</div>
						</div>
					</div>
			    	<div class="center menu" style="width:200px;"><b>&nbsp;</b></div>
					<div class="table main" style="width:100%;border: 1px solid #666666;background-color: #ccc;">
						<div class="row">
							<div class="cell" style="text-align: center;"><b>Module unit and its layers dependency structure</b></div>
							<div class="cell" style="text-align: center;"><b>Modules dependency structure</b></div>
						</div>
						<div class="row">
							<div class="cell">
								<div class="left" style="padding:10px 0 0 15px;"><t:swf src="images/svg/modules.swf" width="270px" height="270px"></t:swf></div>
								<div class="left">
									<ul>
										<li>web - HTML web content layer</li>
										<li>app - client UI side layer</li>
										<li>srv - server side layer</li>
										<li>rpc - shared RPC data and services layer</li>
									</ul>
								</div>
								<div class="clear"></div>
							</div>
							<div class="cell">
								<div class="left" style="padding:10px 0 0 15px;"><t:swf src="images/svg/dependencies.swf" width="500px" height="235px"></t:swf></div>
								<div class="left">
									<ul>
										<li>any <i>rpc</i> layer can use its dependent <i>rpc</i> layers only</li>
										<li>any <i>app</i> layer can use its dependent <i>rpc</i> and <i>app</i> layers only</li>
										<li>any <i>srv</i> layer can use its dependent <i>rpc</i> and <i>srv</i> layers only</li>
										<li>any <i>web</i> layer can use its dependent <i>rpc</i>, <i>app</i>, <i>srv</i> and <i>web</i> (excluding folder <i>/war/</i>) layers</li>
									</ul>
								</div>
								<div class="clear"></div>
							</div>
						</div>
					</div>
			    	<div class="center menu" style="width:200px;"><b>&nbsp;</b></div>
					<div class="table main" style="width:100%;border: 1px solid #666666;background-color: #ccc;">
						<div class="row">
							<div class="cell">
								<b>GWT App code repositories:</b>
								<ul>
									<li>Subversion repository of <a href="http://sourceforge.net/projects/gwtapp/develop/">source code</a></li>
									<li>Maven repository on <a href="http://mvn.gwtapp.org/org/gwtapp/">http://mvn.gwtapp.org/</a></li>
									<!-- <li>JIRA tasks <a href="https://jira.imdapi.com/browse/GWTAPP">repository</a></li> -->
								</ul> 
							</div>
						</div>		
						<div class="row">
							<div class="cell">
								<b>Applications that use GWTApp:</b>
								<ul>
									<li><a href="http://ccalc-web.appspot.com/">FIFO Currency Calculator</a></li>
								</ul>
							</div>
						</div>
						<div class="row">
							<div class="cell">
								<b>Articles related to the GWT:</b>
								<ul>
									<li><a href="http://techzone.enterra-inc.com/gwt-improving-performance/">GWT Improving performance</a></li>
									<li><a href="http://www.techhui.com/profiles/blogs/simpler-and-speedier-gwt-with">Simpler and Speedier GWT with Server Side RPC Serialization</a></li>
								</ul>
								<b>GWT features and benefits:</b>
								<ul>
									<li>Java language</li>
									<li>RPC api, data structures and exceptions Java single code shared on client and server sides</li>
									<li><a href="http://code.google.com/p/google-gin/">Gin</a> dependency injection</li>
									<li>Debugging</li>
									<li>JUnit testing</li>
									<li>Cross browser, optimal and obfuscated JavaScript code generation</li>
									<li>Code splitting</li>
									<li>UI widgets quite similar to Swing MVC</li>
									<li>Integration with IDE like Eclipse, IntelliJ IDEA, NetBeans</li>
									<li>Easy and reliable code refactorization</li>
								</ul> 
							</div>
						</div>
					</div>
			    	<div class="center menu" style="width:200px;"><b>&nbsp;</b></div>
					<div class="table main" style="width:100%;border: 1px solid #666666;background-color: #ccc;">
						<div class="row">
							<div class="cell">		
								<div id="feedbackanchor"></div>
							</div>
						</div>
					</div>
			    	<div class="center menu" style="width:200px;"><b>&nbsp;</b></div>
					<div class="table main" style="width:100%;border: 1px solid #666666;background-color: #ccc;">
						<div class="row">
							<div class="cell">		
								<div style="padding:10px 0 10px 0">Author: <a href="mailto:radek@olesiak.biz">Radek Olesiak</a></div>
							</div>
						</div>
					</div>
					<t:w3org></t:w3org>
				</div>
				<div class="cell"><t:amazon></t:amazon></div>
			</div>
		</div>
		<t:footer></t:footer>
	</body>
</html>