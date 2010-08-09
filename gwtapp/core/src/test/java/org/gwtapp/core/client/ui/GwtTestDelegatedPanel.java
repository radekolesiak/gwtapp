package org.gwtapp.core.client.ui;

import org.gwtapp.core.client.CoreTest;
import org.junit.Test;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;

public class GwtTestDelegatedPanel extends CoreTest {
	
	class DoubleBox extends DelegatedPanel<Double, String> {

		public final TextBox delegated = new TextBox();

		public int thisCount = 0;
		public int delegatedCount = 0;

		public DoubleBox(String id) {
			super(DOM.getElementById(id));
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
	}

	@Test
	public void testDelegatedPanel() {
		assertTrue(true);
	}
}
