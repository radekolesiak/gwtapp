package org.gwtapp.form.client.ui;

import java.util.Map;

import org.gwtapp.core.client.ui.DelegatedPanel;
import org.gwtapp.core.rpc.data.HashModelData;
import org.gwtapp.core.rpc.data.MetaField;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.form.client.FormTest;
import org.gwtapp.template.client.Template;
import org.gwtapp.template.client.TemplateHandler;
import org.gwtapp.template.client.UiHandler;
import org.gwtapp.template.client.ui.TemplateFormPanel;
import org.gwtapp.template.client.ui.TemplatePanel;
import org.junit.Test;

import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class GwtTestTemplateModelPanel extends FormTest {

	class DoubleBox extends DelegatedPanel<Double, String> {

		public final TextBox delegated = new TextBox();

		public DoubleBox() {
			add(delegated);
		}

		@Override
		public HasValue<String> getDelegated() {
			return delegated;
		}

		@Override
		public Double convertToX(String value) {
			Double doubleValue = null;
			try {
				doubleValue = Double.parseDouble(value);
			} catch (Exception e) {
			}
			return doubleValue;
		}

		@Override
		public String convertToY(Double value) {
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

	private static class TemplateFormPanelTest<T> extends TemplateFormPanel<T> {
		public TemplateFormPanelTest(TemplateCallback callback) {
			super(callback);
		}

		@SuppressWarnings("unchecked")
		@Override
		public Map getFields() {
			return super.getFields();
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
		UiHandler<DoubleBox> b = new UiHandler<DoubleBox>(new DoubleBox());
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
}
