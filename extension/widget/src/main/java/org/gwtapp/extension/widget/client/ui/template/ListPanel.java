package org.gwtapp.extension.widget.client.ui.template;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import org.gwtapp.core.rpc.data.HasItems;
import org.gwtapp.template.client.UiHandler;
import org.gwtapp.template.client.callback.SimpleTemplateCallback;
import org.gwtapp.template.client.ui.TemplatePanel;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.ListBox;
import com.google.inject.BindingAnnotation;
import com.google.inject.Inject;

public class ListPanel<T> extends TemplatePanel<T> implements HasItems<T> {

	@Retention(RetentionPolicy.RUNTIME)
	@BindingAnnotation
	public static @interface ProviderAnnotation {
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

	public static class Provider<T> {
		@Inject
		@ProviderAnnotation
		public TemplateCallback callback;
		@Inject
		@ProviderAnnotation
		public ListBox listBox;
		@Inject
		@ProviderAnnotation
		public String widgetName;
		@Inject
		@ProviderAnnotation
		public Formatter<T> formatter;
		@Inject
		@ProviderAnnotation
		public List<T> items;
	}

	private Formatter<T> formatter;

	private List<T> items;

	private final UiHandler<ListBox> listBox;

	private boolean fixedTemplate;

	public ListPanel() {
		this(new SimpleTemplateCallback(), new ListBox(), "listBox",
				new DefaultFormatter<T>(), new ArrayList<T>(), true);
	}

	@Inject
	public ListPanel(Provider<T> provider) {
		this(provider.callback, provider.listBox, provider.widgetName,
				provider.formatter, provider.items, false);
	}

	protected ListPanel(TemplateCallback callback, final ListBox listBox,
			String widgetName, Formatter<T> formatter, List<T> items,
			boolean fixedTemplate) {
		super(callback);
		this.listBox = add(widgetName, new UiHandler<ListBox>(listBox));
		this.formatter = formatter;
		this.fixedTemplate = fixedTemplate;
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

	@Override
	public final void onAddWidgets() {
		if (fixedTemplate) {
			add(listBox.getWidget());
		}
		onAddListPanelWidgets();
	}

	public void onAddListPanelWidgets() {
	}

	@Override
	public List<T> getItems() {
		return items;
	}

	@Override
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

	protected void selectTo(T value) {
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
