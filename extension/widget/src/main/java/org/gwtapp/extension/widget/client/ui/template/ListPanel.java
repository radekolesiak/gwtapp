package org.gwtapp.extension.widget.client.ui.template;

import java.util.List;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.inject.Inject;

public class ListPanel<T> extends HorizontalPanel implements HasValue<T> {

	public static class Style {
		public final static String FIELD = "field";
		public final static String LIST_FIELD = "listField";
		public final static String LIST_BOX = "listBox";
	}

	@Inject
	private ListBox listBox = new ListBox();

	private List<T> items;
	private T value;

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
	public T getValue() {
		return value;
	}

	@Override
	public void setValue(T value) {
		setValue(value, false);
	}

	@Override
	public void setValue(T value, boolean fireEvents) {
		this.value = value;
		selectTo(value);
		if (fireEvents) {
			ValueChangeEvent.fire(this, value);
		}
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<T> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}

	public void setItems(List<T> items) {
		this.items = items;
		listBox.clear();
		for (int i = 0; i < items.size(); i++) {
			listBox.addItem(items.get(i) + "", i + "");
		}
		if (!items.isEmpty()) {
			setValue(items.get(0));
		}
	}

	public List<T> getItems() {
		return items;
	}
}
