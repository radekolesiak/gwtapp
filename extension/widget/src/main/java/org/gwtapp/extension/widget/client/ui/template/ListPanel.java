package org.gwtapp.extension.widget.client.ui.template;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.gwtapp.core.rpc.data.HasValues;
import org.gwtapp.template.client.UiHandler;
import org.gwtapp.template.client.callback.SimpleTemplateCallback;
import org.gwtapp.template.client.ui.TemplatePanel;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.ListBox;
import com.google.inject.BindingAnnotation;
import com.google.inject.Inject;

public class ListPanel<T> extends TemplatePanel<T> implements HasValues<T> {

	@Retention(RetentionPolicy.RUNTIME)
	@BindingAnnotation
	public static @interface Bind {
	}

	public static interface Formatter<T> {
		String format(ListPanel<T> owner, T item, int index);
	}

	public static class DefaultFormatter<T> implements Formatter<T> {
		@Override
		public String format(ListPanel<T> owner, T item, int index) {
			if (item == null) {
				return "";
			} else {
				return item.toString();
			}
		}
	};

	public static class Provider<T> {
		@Inject
		@Bind
		public TemplateCallback callback;
		@Inject
		@Bind
		public ListBox listBox;
		@Inject
		@Bind
		public String widgetName;
		@Inject
		@Bind
		public Formatter<T> formatter;
		@Inject
		@Bind
		public List<T> items;
	}

	private Formatter<T> formatter;

	private List<T> items;

	private final UiHandler<ListBox> listBox;

	private final ChangeHandler handler = new ChangeHandler() {
		@Override
		public void onChange(ChangeEvent event) {
			int selected = listBox.getWidget().getSelectedIndex();
			if (selected >= 0) {
				setValue(ListPanel.this.items.get(selected), true);
			} else if (getValue() != null) {
				setValue(null);
			}
		}
	};

	private HandlerRegistration registration = null;

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
		if (items != null) {
			setValues(items);
		}
		if (fixedTemplate) {
			addWidgetsCallback(new WidgetsCallback() {
				@Override
				public void onAddWidgets() {
					add(listBox);
				}
			});
		}
		addWidgetsCallback(new WidgetsCallback() {
			@Override
			public void onAddWidgets() {
				selectTo(getValue());
			}
		});
		setHandlerConnected(true);
	}

	@Override
	public Collection<T> getValues() {
		return items;
	}

	@Override
	public void setValues(Collection<T> items) {
		setValues(items, -1);
	}

	public void setValues(final Collection<T> items, int selected) {
		this.items = new ArrayList<T>(items);
		listBox.getWidget().clear();
		T selectedItem = null;
		if (items != null) {
			for (int i = 0; i < items.size(); i++) {
				T item = this.items.get(i);
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
		setValues(getValues());
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
		try {
			setHandlerConnected(false);
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
		} finally {
			setHandlerConnected(true);
		}
	}

	private void setHandlerConnected(boolean connect) {
		if (connect) {
			if (registration == null) {
				registration = listBox.getWidget().addChangeHandler(handler);
			}
		} else {
			if (registration != null) {
				registration.removeHandler();
				registration = null;
			}
		}
	}
}
