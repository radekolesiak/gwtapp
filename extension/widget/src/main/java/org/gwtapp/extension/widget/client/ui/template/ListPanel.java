package org.gwtapp.extension.widget.client.ui.template;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import org.gwtapp.template.client.Template;
import org.gwtapp.template.client.UiHandler;
import org.gwtapp.template.client.callback.SimpleTemplateCallback;
import org.gwtapp.template.client.ui.TemplatePanel;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.ListBox;
import com.google.inject.BindingAnnotation;
import com.google.inject.Inject;

public class ListPanel<T> extends TemplatePanel<T> {

	@Retention(RetentionPolicy.RUNTIME)
	@BindingAnnotation
	public static @interface AFieldName {
	}

	@Retention(RetentionPolicy.RUNTIME)
	@BindingAnnotation
	public static @interface ATemplateCallback {
	}

	@Retention(RetentionPolicy.RUNTIME)
	@BindingAnnotation
	public static @interface AListBox {
	}

	@Retention(RetentionPolicy.RUNTIME)
	@BindingAnnotation
	public static @interface AFormatter {
	}

	@Retention(RetentionPolicy.RUNTIME)
	@BindingAnnotation
	public static @interface AItems {
	}

	public static interface Formatter<T> {
		String format(ListPanel<T> owner, T item, int index);
	}

	public static class DefaultFormatter<T> implements Formatter<T> {
		@Override
		public String format(ListPanel<T> owner, T item, int index) {
			return "" + item;
		}
	};

	private Formatter<T> formatter;

	private List<T> items;

	private final UiHandler<ListBox> listBox;

	public ListPanel() {
		this(new SimpleTemplateCallback(new Template("div", "", "list-panel",
				"<select t:field=\"listBox\"></select>")), new ListBox(), "listBox",
				new DefaultFormatter<T>(), new ArrayList<T>());
	}

	@Inject
	public ListPanel(@ATemplateCallback TemplateCallback callback,
			final @AListBox ListBox listBox, @AFieldName String widgetName,
			@AFormatter Formatter<T> formatter, @AItems List<T> items) {
		super(callback);
		this.listBox = add(widgetName, new UiHandler<ListBox>(listBox));
		this.formatter = formatter;
		listBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				int selected = listBox.getSelectedIndex();
				setValue(ListPanel.this.items.get(selected), true);
			}
		});
		if (items != null) {
			setItems(items);
		}
	}

	public void setItems(final List<T> items) {
		setItems(items, -1);
	}

	public void setItems(final List<T> items, int selected) {
		this.items = items;
		listBox.getWidget().clear();
		T selectedItem = null;
		if (items != null) {
			for (int i = 0; i < items.size(); i++) {
				T item = items.get(i);
				if (i == selected) {
					selectedItem = item;
				}
				String label = formatter.format(this, item, i);
				listBox.getWidget().addItem(label, i + "");
			}
		}
		setValue(selectedItem);
	}

	public List<T> getItems() {
		return items;
	}

	public Formatter<T> getFormatter() {
		return formatter;
	}

	public void setFormatter(Formatter<T> formatter) {
		this.formatter = formatter;
		setItems(getItems());
	}

	public int getSelectedIndex() {
		return listBox.getWidget().getSelectedIndex();
	}

	@Override
	public void setValue(T value, boolean fireEvents) {
		selectTo(value);
		super.setValue(value, fireEvents);
	}

	private void selectTo(T value) {
		if (value != null) {
			for (int i = 0; i < items.size(); i++) {
				T item = items.get(i);
				if (value == item || value.equals(item)) {
					listBox.getWidget().setSelectedIndex(i);
					break;
				}
			}
		} else {
			listBox.getWidget().setSelectedIndex(-1);
		}
	}
}
