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

	@Inject
	private Formatter<T> formatter = new DefaultFormatter<T>();

	@Inject
	private ListBox listBox = new ListBox();

	private List<T> items;

	@Inject
	public ListPanel() {
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

	@Override
	public void setValue(T value, boolean fireEvents) {
		selectTo(value);
		super.setValue(value, fireEvents);
	}

	public void setItems(List<T> items) {
		this.items = items;
		listBox.clear();
		for (int i = 0; i < items.size(); i++) {
			String label = formatter.format(this, items.get(i), i);
			listBox.addItem(label, i + "");
		}
		if (!items.isEmpty()) {
			setValue(items.get(0));
		}
	}

	public List<T> getItems() {
		return items;
	}
}
