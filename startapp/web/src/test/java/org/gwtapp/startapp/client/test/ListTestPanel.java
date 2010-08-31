package org.gwtapp.startapp.client.test;

import java.util.ArrayList;
import java.util.List;

import org.gwtapp.extension.widget.client.ui.template.ListPanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

public class ListTestPanel extends FlowPanel {

	class Item {
		public Item() {
		}

		public Item(String name, String surname) {
			this.name = name;
			this.surname = surname;
		}

		public String name;
		public String surname;
	}

	class Formatter implements ListPanel.Formatter<Item> {
		@Override
		public String format(ListPanel<Item> owner, Item item, int index) {
			return "Name: " + item.name + "; Surname: " + item.surname;
		}
	}

	private final List<Item> items = new ArrayList<Item>();
	{
		items.add(new Item("ab", "AB"));
		items.add(new Item("01", ")!"));
		items.add(new Item("23", "@#"));
		items.add(new Item("xyz", "XYZ"));
		items.add(new Item("+-*/", "=_*?"));
	}

	private final ListPanel<Item> listPanel = new ListPanel<Item>();
	private final SimplePanel status = new SimplePanel();

	public ListTestPanel() {
		listPanel.setFormatter(new Formatter());
		listPanel.setItems(items);
		listPanel.addValueChangeHandler(new ValueChangeHandler<Item>() {
			@Override
			public void onValueChange(ValueChangeEvent<Item> event) {
				status.setWidget(new Label("value change event: "
						+ renderSelected(event.getValue())));
			}
		});
		add(listPanel);
		for (final Item item : items) {
			Button btn = new Button("Set item: {" + "name=" + item.name + " "
					+ "surname=" + item.surname + "}");
			btn.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					status.setWidget(new Label());
					listPanel.setValue(item);
				}
			});
			add(btn);
		}
		Button btn = new Button("Get selected");
		btn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				status.setWidget(new Label("selected value: "
						+ renderSelected(listPanel.getValue())));
			}
		});
		add(btn);
		add(status);
	}

	private String renderSelected(Item item) {
		if (item != null) {
			return "index=" + listPanel.getSelectedIndex() + " value={"
					+ "name=" + item.name + " " + "surname=" + item.surname
					+ "}";
		} else {
			return "null";
		}
	}
}
