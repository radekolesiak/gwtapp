package org.gwtapp.core.client.ui;

import org.gwtapp.core.client.CoreTest;
import org.junit.Test;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;

public class GwtTestDelegatedPanel extends CoreTest {

	class DoubleBox extends DelegatedPanel<Double, String> {

		private final static double eps = 1e-3;

		public final TextBox delegated = new TextBox();

		public int thisCount = 0;
		public int delegatedCount = 0;

		public DoubleBox() {
			add(delegated);
			addValueChangeHandler(new ValueChangeHandler<Double>() {
				@Override
				public void onValueChange(ValueChangeEvent<Double> event) {
					++thisCount;
				}
			});
			delegated.addValueChangeHandler(new ValueChangeHandler<String>() {
				@Override
				public void onValueChange(ValueChangeEvent<String> event) {
					++delegatedCount;
				}
			});
		}

		@Override
		public HasValue<String> getDelegated() {
			return delegated;
		}

		@Override
		public Double convertToX(String value) {
			Double doubleValue = null;
			try {
				doubleValue = Double.parseDouble(value);
			} catch (Exception e) {
			}
			return doubleValue;
		}

		@Override
		public String convertToY(Double value) {
			return "" + value;
		}
		

		@Override
		public boolean isDelegatedToUpdate(Double oldValue, Double newValue) {
			if (oldValue == null ^ newValue == null) {
				return true;
			} else if (oldValue == null && newValue == null) {
				return false;
			} else {
				return Math.abs(oldValue - newValue) > eps;
			}
		}		
	}

	@Test
	public void testNullValue() {
		DoubleBox db = new DoubleBox();
		assertNull(db.getValue());
		assertEquals("", db.delegated.getValue());
		db.setValue(null);
		assertNull(db.getValue());
		assertEquals("null", db.delegated.getValue());
	}

	@Test
	public void testValue1dot11() {
		DoubleBox db = new DoubleBox();
		double value = 1.11;
		db.setValue(value);
		assertEquals(value, db.getValue());
		assertEquals("" + value, db.delegated.getValue());
	}

	@Test
	public void testValue1dot11And2dot22() {
		DoubleBox db = new DoubleBox();
		double value1 = 1.11;
		db.setValue(value1);
		assertEquals(value1, db.getValue());
		assertEquals("" + value1, db.delegated.getValue());
		double value2 = 2.22;
		db.setValue(value2);
		assertEquals(value2, db.getValue());
		assertEquals("" + value2, db.delegated.getValue());
	}
}
