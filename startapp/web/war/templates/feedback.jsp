<t:template styleclass="feedback">
	<i>Send feedback:</i>
	<div style="width:1px;height:7px;"></div>
	<div class="left">
		<div>
			<small class="label">Your email:</small>
			<input t:field="email" type="text" class="input" />
		</div>
		<div>
			<small class="label">Your message:</small>
			<textarea t:field="message" class="input" ></textarea>
		</div>
	</div>
	<div class="left" style="margin-left:15px;margin-top:15px;">
		<img t:field="indicator" src="images/indicator.gif" class="indicator" style="display:none;"></img>
		<a 	t:field="send" 
			href="javascript:void(0);" 
			onmouseover="document.getElementById('feedbackbtn').src='images/feedback.png'" 
			onmouseout="document.getElementById('feedbackbtn').src='images/feedback.png'" 
		><img id="feedbackbtn" src="images/feedback.png"><br >Send</br></img></a>		
	</div>
	<div class="clear"></div>
	<small t:field="sent" style="display:none;">Thank you for your feedback.</small>
	<small t:field="error" style="display:none;">There was a problem while sending feedback.</small>
</t:template>