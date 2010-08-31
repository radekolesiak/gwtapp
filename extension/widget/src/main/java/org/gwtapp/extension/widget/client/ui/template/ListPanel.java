package org.gwtapp.extension.widget.client.ui.template;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import org.gwtapp.extension.widget.client.ui.ValuePanel;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.ListBox;
import com.google.inject.BindingAnnotation;
import com.google.inject.Inject;

// TODO extend by TemplatePanel<T>
public class ListPanel<T> extends ValuePanel<T> {

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

	private final ListBox listBox;

	public ListPanel() {
		this(new ListBox(), new DefaultFormatter<T>(), new ArrayList<T>());
	}

	@Inject
	public ListPanel(final @AListBox ListBox listBox,
			@AFormatter Formatter<T> formatter, @AItems List<T> items) {
		this.listBox = listBox;
		this.formatter = formatter;
		listBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				int selected = listBox.getSelectedIndex();
				setValue(ListPanel.this.items.get(selected), true);
			}
		});
		add(listBox);
		if (items != null) {
			setItems(items);
		}
	}

	public void setItems(final List<T> items) {
		this.items = items;
		listBox.clear();
		if (items != null) {
			for (int i = 0; i < items.size(); i++) {
				String label = formatter.format(this, items.get(i), i);
				listBox.addItem(label, i + "");
			}
			if (!items.isEmpty()) {
				setValue(items.get(0));
			}
		}
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
		return listBox.getSelectedIndex();
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
					listBox.setSelectedIndex(i);
					break;
				}
			}
		} else {
			listBox.setSelectedIndex(-1);
		}
	}
}
