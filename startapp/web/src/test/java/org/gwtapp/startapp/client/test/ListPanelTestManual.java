package org.gwtapp.startapp.client.test;

import java.util.ArrayList;
import java.util.List;

import org.gwtapp.extension.widget.client.ui.template.ListPanel;
import org.gwtapp.startapp.client.StartAppManualTestEntryPoint;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Provider;

public class ListPanelTestManual extends FlowPanel {

	public static class Item {
		public Item() {
		}

		public Item(String name, String surname) {
			this.name = name;
			this.surname = surname;
		}

		public String name;
		public String surname;
	}

	public static class Formatter implements ListPanel.Formatter<Item> {
		@Override
		public String format(ListPanel<Item> owner, Item item, int index) {
			return "Name: " + item.name + "; Surname: " + item.surname;
		}
	}

	public static class ItemsProvider implements Provider<List<Item>> {
		public List<Item> get() {
			List<Item> items = new ArrayList<Item>();
			items.add(new Item("ab", "AB"));
			items.add(new Item("01", ")!"));
			items.add(new Item("23", "@#"));
			items.add(new Item("xyz", "XYZ"));
			items.add(new Item("+-*/", "=_*?"));
			return items;
		}
	}

	public ListPanelTestManual() {
		ListPanel<Item> listPanel = new ListPanel<Item>();
		listPanel.setFormatter(new Formatter());
		listPanel.setItems(new ItemsProvider().get());
		addListPanel(StartAppManualTestEntryPoint.gin.getListPanel());
		addListPanel(listPanel);
	}

	private void addListPanel(final ListPanel<Item> listPanel) {
		final SimplePanel status = new SimplePanel();
		listPanel.addValueChangeHandler(new ValueChangeHandler<Item>() {
			@Override
			public void onValueChange(ValueChangeEvent<Item> event) {
				status.setWidget(new Label("value change event: "
						+ renderSelected(listPanel, event.getValue())));
			}
		});
		add(listPanel);
		for (final Item item : listPanel.getItems()) {
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
		Button select = new Button("Get selected");
		select.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				status.setWidget(new Label("selected value: "
						+ renderSelected(listPanel, listPanel.getValue())));
			}
		});
		Button empty = new Button("Unselect");
		empty.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				listPanel.setValue(null, true);
			}
		});
		add(select);
		add(empty);
		add(status);
	}

	private String renderSelected(ListPanel<Item> listPanel, Item item) {
		if (item != null) {
			return "index=" + listPanel.getSelectedIndex() + " value={"
					+ "name=" + item.name + " " + "surname=" + item.surname
					+ "}";
		} else {
			return "null";
		}
	}
}
