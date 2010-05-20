<t:template styleclass="feedback">
	<i>Send us your feedback:</i>
	<div style="width:1px;height:7px;"></div>
	<div>
		<small class="label">Your e-mail:</small>
		<input t:field="email" type="text" class="input" />
	</div>
	<div>
		<small class="label">Your message:</small>
		<textarea t:field="message" class="input" ></textarea>
	</div>
	<div t:field="send" class="send" >Send</div>
	<div style="clear:both;"></div>
	<small t:field="sent" style="display:none;">Thank your feedback.</small>
	<small t:field="error" style="display:none;">There was a problem with feedback sending.</small>
</t:template>