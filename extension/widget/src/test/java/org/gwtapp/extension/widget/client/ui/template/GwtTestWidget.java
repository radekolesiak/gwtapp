package org.gwtapp.extension.widget.client.ui.template;

import java.util.ArrayList;
import java.util.List;

import org.gwtapp.extension.widget.client.WidgetTestCase;
import org.gwtapp.extension.widget.client.ui.template.ListPanel.DefaultFormatter;
import org.junit.Test;

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
}
