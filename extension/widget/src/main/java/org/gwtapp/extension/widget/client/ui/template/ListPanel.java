package org.gwtapp.extension.widget.client.ui.template;

import java.util.List;

import org.gwtapp.core.rpc.data.IndexedItem;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

public class ListPanel<T extends IndexedItem<?>> extends HorizontalPanel
		implements HasValue<T> {

	public static class Style {
		public final static String FIELD = "field";
		public final static String LIST_FIELD = "listField";
		public final static String LABEL = "label";
		public final static String LIST_BOX = "listBox";
	}

	private final Label label = new Label();
	private final ListBox listBox = new ListBox();

	private T value;

	public ListPanel(List<T> items) {
		this("", items);
	}

	public ListPanel(String title, final List<T> items) {
		addStyleName(Style.FIELD);
		addStyleName(Style.LIST_FIELD);
		label.addStyleName(Style.LABEL);
		listBox.addStyleName(Style.LIST_BOX);

		label.setText(title);
		for (T item : items) {
			listBox.addItem(item.getLabel(), item.getIndex() + "");
		}
		listBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				int selected = listBox.getSelectedIndex();
				setValue(items.get(selected), true);
			}
		});
		if (!items.isEmpty()) {
			setValue(items.get(0));
		}
		add(label);
		add(listBox);
	}

	private void selectTo(T value) {
		if (value != null) {
			for (int i = 0; i < listBox.getItemCount(); i++) {
				if (listBox.getValue(i).equals(value.getIndex() + "")) {
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

}
