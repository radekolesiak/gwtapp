package org.gwtapp.extension.widget.client.ui.template;

import java.util.ArrayList;
import java.util.List;

import org.gwtapp.core.rpc.data.Value;
import org.gwtapp.extension.widget.client.WidgetTestCase;
import org.gwtapp.extension.widget.client.ui.template.ListPanel.DefaultFormatter;
import org.gwtapp.extension.widget.client.ui.template.ListPanel.Formatter;
import org.junit.Test;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.RootPanel;

public class GwtTestWidget extends WidgetTestCase {

	private static class DoubleFormatter implements ListPanel.Formatter<Double> {
		@Override
		public String format(ListPanel<Double> owner, Double item, int index) {
			if (item == null) {
				return "";
			} else {
				return "" + ((int) item.doubleValue());
			}
		}
	};

	@Test
	public void testInitState() {
		ListPanel<Double> panel = new ListPanel<Double>();
		assertNotNull(panel.getFormatter());
		assertNotNull(panel.getItems());
		assertNull(panel.getValue());
		assertTrue(panel.getFormatter() instanceof DefaultFormatter);
		assertTrue(panel.getItems() instanceof ArrayList);
		List<Double> items = new ArrayList<Double>();
		DoubleFormatter formatter = new DoubleFormatter();
		items.add(1.3);
		items.add(null);
		items.add(2.4);
		items.add(null);
		items.add(3.5);
		items.add(null);
		items.add(2.4);
		panel.setItems(items);
		panel.setFormatter(formatter);
		assertEquals(items, panel.getItems());
		assertEquals(formatter, panel.getFormatter());
		assertNull(panel.getValue());
	}

	@Test
	public void testSettersAndGetters() {
		ListPanel<Double> panel = new ListPanel<Double>();
		List<Double> items = new ArrayList<Double>();
		DoubleFormatter formatter = new DoubleFormatter();
		items.add(1.3);
		items.add(null);
		items.add(2.4);
		items.add(null);
		items.add(3.5);
		items.add(null);
		items.add(2.4);
		panel.setItems(items);
		panel.setFormatter(formatter);
		panel.setValue(null);
		assertNull(panel.getValue());
		assertEquals(-1, panel.getSelectedIndex());
		panel.setValue(3.5);
		assertEquals(new Double(3.5), panel.getValue());
		assertEquals(4, panel.getSelectedIndex());
		panel.setValue(null);
		assertNull(panel.getValue());
		assertEquals(-1, panel.getSelectedIndex());
	}

	@Test
	public void testValueChangeHandler() {
		final Value<Boolean> handled = new Value<Boolean>();
		final Value<Double> value = new Value<Double>();
		ListPanel<Double> panel = new ListPanel<Double>();
		List<Double> items = new ArrayList<Double>();
		DoubleFormatter formatter = new DoubleFormatter();
		items.add(1.3);
		items.add(null);
		items.add(2.4);
		items.add(null);
		items.add(3.5);
		items.add(null);
		items.add(2.4);
		panel.setItems(items);
		panel.setFormatter(formatter);
		panel.addValueChangeHandler(new ValueChangeHandler<Double>() {
			@Override
			public void onValueChange(ValueChangeEvent<Double> event) {
				handled.set(true);
				value.set(event.getValue());
			}
		});
		RootPanel.get().add(panel);
		assertNull(handled.get());
		assertNull(value.get());
		panel.setValue(null, true);
		assertTrue(handled.get());
		assertNull(value.get());
		assertNull(panel.getValue());
		assertEquals(-1, panel.getSelectedIndex());
		handled.set(null);
		panel.setValue(3.5, true);
		assertTrue(handled.get());
		assertEquals(new Double(3.5), value.get());
		assertEquals(new Double(3.5), panel.getValue());
		assertEquals(4, panel.getSelectedIndex());
		handled.set(null);
		panel.setValue(null);
		assertNull(handled.get());
		assertEquals(new Double(3.5), value.get());
		assertNull(panel.getValue());
		assertEquals(-1, panel.getSelectedIndex());
		handled.set(null);
		panel.setValue(null, true);
		assertTrue(handled.get());
		assertNull(value.get());
		assertNull(panel.getValue());
		assertEquals(-1, panel.getSelectedIndex());
	}

	@Test
	public void testValueChangeHandlerCount() {
		final Value<Integer> count = new Value<Integer>(0);
		final Value<Double> value = new Value<Double>();
		ListPanel<Double> panel = new ListPanel<Double>();
		List<Double> items = new ArrayList<Double>();
		items.add(1.3);
		items.add(null);
		items.add(2.4);
		items.add(null);
		items.add(3.5);
		items.add(null);
		items.add(2.4);
		panel.setItems(items);
		panel.addValueChangeHandler(new ValueChangeHandler<Double>() {
			@Override
			public void onValueChange(ValueChangeEvent<Double> event) {
				count.set(count.get() + 1);
				value.set(event.getValue());
			}
		});
		RootPanel.get().add(panel);
		panel.setValue(3.5);
		assertEquals(new Integer(0), count.get());
		assertNull(value.get());
		assertEquals(new Double(3.5), panel.getValue());
		count.set(0);
		value.set(null);
		panel.setValue(3.5, true);
		assertEquals(new Integer(1), count.get());
		assertEquals(new Double(3.5), value.get());
		assertEquals(new Double(3.5), panel.getValue());
	}

	@Test
	public void testDefaultFormatter() {
		Formatter<Double> formatter = new ListPanel.DefaultFormatter<Double>();
		assertEquals("null", formatter.format(null, null, -1));
		assertEquals("1.1", formatter.format(null, 1.1, -1));
		assertEquals("-31.31", formatter.format(null, -31.31, -1));
	}
}
