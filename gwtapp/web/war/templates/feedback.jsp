<t:template styleclass="feedback">
	<i>Send feedback:</i>
	<div style="width:1px;height:7px;"></div>
	<div>
		<small class="label">Your e-mail:</small>
		<input t:field="email" type="text" class="input" />
	</div>
	<div>
		<small class="label">Your message:</small>
		<textarea t:field="message" class="input" ></textarea>
	</div>
	<div class="right">
		<img t:field="indicator" src="images/indicator.gif" class="indicator" style="display:none;"></img>
		<button t:field="send" class="send" >Send</button>
	</div>
	<div class="clear"></div>
	<small t:field="sent" style="display:none;">Thank you for your feedback.</small>
	<small t:field="error" style="display:none;">There was a problem while sending feedback.</small>
</t:template>