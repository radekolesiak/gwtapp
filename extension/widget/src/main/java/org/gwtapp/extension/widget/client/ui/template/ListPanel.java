package org.gwtapp.extension.widget.client.ui.template;

import java.util.List;

import org.gwtapp.extension.widget.client.ui.ValuePanel;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.ListBox;
import com.google.inject.Inject;

// TODO extend by TemplatePanel<T>
public class ListPanel<T> extends ValuePanel<T> {

	public static class Style {
		public final static String FIELD = "field";
		public final static String LIST_FIELD = "listField";
		public final static String LIST_BOX = "listBox";
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
		this(new ListBox(), new DefaultFormatter<T>());
	}

	@Inject
	public ListPanel(final ListBox listBox, Formatter<T> formatter) {
		this.listBox = listBox;
		this.formatter = formatter;
		addStyleName(Style.FIELD);
		addStyleName(Style.LIST_FIELD);
		listBox.addStyleName(Style.LIST_BOX);
		listBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				int selected = listBox.getSelectedIndex();
				setValue(items.get(selected), true);
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
