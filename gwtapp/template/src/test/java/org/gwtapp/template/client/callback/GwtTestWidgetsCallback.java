package org.gwtapp.template.client.callback;

import java.util.Map;

import org.gwtapp.core.rpc.data.Value;
import org.gwtapp.template.client.Template;
import org.gwtapp.template.client.TemplateTest;
import org.gwtapp.template.client.handler.TemplateHandler;
import org.gwtapp.template.client.ui.TemplatePanel;
import org.gwtapp.template.client.ui.TemplatePanel.WidgetsCallback;
import org.junit.Test;

import com.google.gwt.user.client.ui.RootPanel;

public class GwtTestWidgetsCallback extends TemplateTest {
	@Test
	public void test() {
		final Value<Boolean> onAddWidgets = new Value<Boolean>();
		final Value<Boolean> onWidgetsCallback1 = new Value<Boolean>();
		final Value<Boolean> onWidgetsCallback2 = new Value<Boolean>();
		TemplatePanel<Void> panel = new TemplatePanel<Void>(
				loadTemplateCallback(new Template(), new TFieldCallback())) {
			@Override
			public void onWidgets() {
				assertNull(onAddWidgets.get());
				assertNull(onWidgetsCallback1.get());
				assertNull(onWidgetsCallback2.get());
				onAddWidgets.set(true);
			}
		};
		panel.addWidgetsCallback(new WidgetsCallback() {
			@Override
			public void onWidgets() {
				assertTrue(onAddWidgets.get());
				assertNull(onWidgetsCallback1.get());
				assertNull(onWidgetsCallback2.get());
				onWidgetsCallback1.set(true);
			}
		});
		panel.addWidgetsCallback(new WidgetsCallback() {
			@Override
			public void onWidgets() {
				assertTrue(onAddWidgets.get());
				assertTrue(onWidgetsCallback1.get());
				assertNull(onWidgetsCallback2.get());
				onWidgetsCallback2.set(true);
			}
		});
		RootPanel.get().add(panel);
		assertTrue(onAddWidgets.get());
		assertTrue(onWidgetsCallback1.get());
		assertTrue(onWidgetsCallback2.get());
	}

	private TemplatePanel.TemplateCallback loadTemplateCallback(
			final Template template, final TemplatePanel.Callback callback) {
		return new TemplatePanel.TemplateCallback() {
			@Override
			public Template getTemplate() {
				return template;
			}

			@Override
			public void template(TemplatePanel<?> owner,
					Map<String, TemplateHandler> widgetHandlers) {
				callback.template(owner, widgetHandlers);
			}
		};
	}
}
