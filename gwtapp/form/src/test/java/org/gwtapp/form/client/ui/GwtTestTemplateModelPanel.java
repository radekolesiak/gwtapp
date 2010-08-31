package org.gwtapp.form.client.ui;

import java.util.Map;

import org.gwtapp.core.client.ui.DelegatedPanel;
import org.gwtapp.core.rpc.data.HashModelData;
import org.gwtapp.core.rpc.data.MetaField;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.core.rpc.data.Value;
import org.gwtapp.form.client.FormTest;
import org.gwtapp.template.client.Template;
import org.gwtapp.template.client.TemplateHandler;
import org.gwtapp.template.client.UiHandler;
import org.gwtapp.template.client.ui.TemplatePanel;
import org.junit.Test;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class GwtTestTemplateModelPanel extends FormTest {

	class LongBox extends DelegatedPanel<Long, String> {

		public final TextBox delegated = new TextBox();

		public LongBox() {
			add(delegated);
		}

		@Override
		public HasValue<String> getDelegated() {
			return delegated;
		}

		@Override
		public Long convertToX(String value) {
			Long longValue = null;
			try {
				longValue = Long.parseLong(value);
			} catch (Exception e) {
			}
			return longValue;
		}

		@Override
		public String convertToY(Long value) {
			return "" + value;
		}
	}

	private static class CustomTemplateCallback implements
			TemplatePanel.TemplateCallback {

		private final Template template;

		public CustomTemplateCallback() {
			this(new Template());
		}

		public CustomTemplateCallback(Template template) {
			this.template = template;
		}

		@Override
		public Template getTemplate() {
			return template;
		}

		@Override
		public void template(TemplatePanel<?> owner,
				Map<String, TemplateHandler> widgetHandlers) {
			for (TemplateHandler handler : widgetHandlers.values()) {
				owner.add(handler.onWidget(null));
			}
		}
	}

	private static class TestModel extends HashModelData {

		public static MetaField<TestModel, String> A = new MetaFieldAdapter<TestModel, String>(
				"a") {

			@Override
			public String get(TestModel model) {
				return model.getA();
			}

			@Override
			public void set(TestModel model, String value) {
				model.setA(value);
			}

			public String def() {
				return "xyz";
			};
		};

		public static MetaField<TestModel, Long> B = new MetaFieldAdapter<TestModel, Long>(
				"b") {

			@Override
			public Long get(TestModel model) {
				return model.getB();
			}

			@Override
			public void set(TestModel model, Long value) {
				model.setB(value);
			}

		};

		private String a = A.add(this).def();
		private Long b = B.add(this).def();

		public void setA(String a) {
			this.a = a;
		}

		public String getA() {
			return a;
		}

		public void setB(Long b) {
			this.b = b;
		}

		public Long getB() {
			return b;
		}
	}

	@Test
	public void testInitStateByTemplate() {
		TemplateModelPanel<TestModel> panel = new TemplateModelPanel<TestModel>(
				new CustomTemplateCallback());
		assertNull(panel.getValue());
	}

	@Test
	public void testSetModelAndUiHandlers() {
		TemplateModelPanel<TestModel> panel = new TemplateModelPanel<TestModel>(
				new CustomTemplateCallback());
		assertNull(panel.getValue());
		UiHandler<TextBox> a = new UiHandler<TextBox>(new TextBox());
		UiHandler<LongBox> b = new UiHandler<LongBox>(new LongBox());
		TestModel model = new TestModel();
		panel.add(TestModel.A, a);
		panel.add(TestModel.B, b);
		panel.setValue(model);
		assertEquals(model, panel.getValue());
		assertFalse(panel.isAttached());
		assertFalse(a.getWidget().isAttached());
		assertFalse(b.getWidget().isAttached());
		RootPanel.get().add(panel);
		assertTrue(panel.isAttached());
		assertTrue(a.getWidget().isAttached());
		assertTrue(b.getWidget().isAttached());
	}

	@Test
	public void testValueChangeHandlers() {
		TemplateModelPanel<TestModel> panel = new TemplateModelPanel<TestModel>(
				new CustomTemplateCallback());
		UiHandler<TextBox> a = new UiHandler<TextBox>(new TextBox());
		UiHandler<LongBox> b = new UiHandler<LongBox>(new LongBox());
		TestModel model = new TestModel();
		panel.add(TestModel.A, a);
		panel.add(TestModel.B, b);
		assertNull(panel.getValue());
		panel.setValue(model);
		assertEquals(model, panel.getValue());
		RootPanel.get().add(panel);
		final Value<Integer> handled = new Value<Integer>(0);
		panel.addValueChangeHandler(new ValueChangeHandler<TestModel>() {
			@Override
			public void onValueChange(ValueChangeEvent<TestModel> event) {
				handled.set(handled.get() + 1);
			}
		});
		{
			panel.setValue(model, true);
			assertEquals(new Integer(1), handled.get());
		}
		{
			a.getWidget().setValue("012", true);
			assertEquals(new Integer(2), handled.get());
			assertEquals("012", panel.getValue().getA());
		}
		{
			a.getWidget().setValue("012", true);
			assertEquals(new Integer(2), handled.get());
			assertEquals("012", panel.getValue().getA());
		}
		{
			a.getWidget().setValue("011", true);
			assertEquals(new Integer(3), handled.get());
			assertEquals("011", panel.getValue().getA());
		}
		{
			b.getWidget().delegated.setValue("1117", true);
			assertEquals(new Integer(4), handled.get());
			assertEquals(new Long(1117L), panel.getValue().getB());
		}
		{
			b.getWidget().setValue(11117L, true);
			assertEquals(new Integer(5), handled.get());
			assertEquals(new Long(11117L), panel.getValue().getB());
		}

	}
}
