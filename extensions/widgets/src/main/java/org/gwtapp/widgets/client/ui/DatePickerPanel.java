package org.gwtapp.widgets.client.ui;

import java.util.Date;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.PopupPanel.PositionCallback;
import com.google.gwt.user.datepicker.client.DatePicker;

public class DatePickerPanel extends FlowPanel implements HasValue<Date> {

	public static class Style {
		public final static String DATE_PICKER_PANEL = "datePickerPanel";
		public final static String DATE_PICKER_BOX = "datePickerBox";
		public final static String DATE_PICKER_TEXT = "datePickerText";
		public final static String DATE_PICKER = "datePicker";
	}

	public final static DateTimeFormat DF = DateTimeFormat.getFormat("d/M/y");

	@SuppressWarnings("serial")
	public static class InvalidDateFormatException extends RuntimeException {
	}

	private class DatePickerBox extends DialogBox implements HasValue<Date> {

		private final DatePicker delegated = new DatePicker();

		public DatePickerBox() {
			super(true, false);
			addStyleName(Style.DATE_PICKER_BOX);
			delegated.addStyleName(Style.DATE_PICKER);
			setWidget(delegated);
			sinkEvents(Event.ONMOUSEOUT);
			sinkEvents(Event.ONMOUSEOVER);
		}

		@SuppressWarnings("unused")
		public void setCurrentMonth(Date month) {
			delegated.setCurrentMonth(month);
		}

		@Override
		public Date getValue() {
			return delegated.getValue();
		}

		@Override
		public void setValue(Date value) {
			setValue(value, false);
		}

		@Override
		public void setValue(Date value, boolean fireEvents) {
			if (value != null) {
				delegated.setCurrentMonth(value);
			}
			delegated.setValue(value, fireEvents);
		}

		@Override
		public HandlerRegistration addValueChangeHandler(
				ValueChangeHandler<Date> handler) {
			return delegated.addValueChangeHandler(handler);
		}

		@Override
		public void onBrowserEvent(Event event) {
			super.onBrowserEvent(event);
			updateAutoHide(event);
		}
	}

	private static class DatePickerText extends TextBox {

		private final DatePickerPanel owner;

		public DatePickerText(DatePickerPanel owner) {
			this.owner = owner;
			addStyleName(Style.DATE_PICKER_TEXT);
			sinkEvents(Event.ONMOUSEOUT);
			sinkEvents(Event.ONMOUSEOVER);
		}

		public DatePickerText(DatePickerPanel owner, Element wrap) {
			super(wrap);
			this.owner = owner;
			addStyleName(Style.DATE_PICKER_TEXT);
			sinkEvents(Event.ONMOUSEOUT);
			sinkEvents(Event.ONMOUSEOVER);
		}

		@Override
		public void onBrowserEvent(Event event) {
			super.onBrowserEvent(event);
			owner.updateAutoHide(event);
		}

		public static DatePickerText wrapDatePickerText(DatePickerPanel owner,
				Element element) {
			// Assert that the element is attached.
			assert Document.get().getBody().isOrHasChild(element);
			DatePickerText textBox = new DatePickerText(owner, element);

			// Mark it attached and remember it for cleanup.
			textBox.onAttach();

			return textBox;
		}
	}

	private void updateAutoHide(Event event) {
		int x = event.getClientX();
		int y = event.getClientY();
		boolean ix1 = x > getDatePickerBox().getAbsoluteLeft()
				&& x < getDatePickerBox().getAbsoluteLeft()
						+ getDatePickerBox().getOffsetWidth();
		boolean iy1 = y > getDatePickerBox().getAbsoluteTop()
				&& y < getDatePickerBox().getAbsoluteTop()
						+ getDatePickerBox().getOffsetHeight();
		boolean ix2 = x > dateBox.getAbsoluteLeft()
				&& x < dateBox.getAbsoluteLeft() + dateBox.getOffsetWidth();
		boolean iy2 = y > dateBox.getAbsoluteTop()
				&& y < dateBox.getAbsoluteTop() + dateBox.getOffsetHeight();
		getDatePickerBox().setAutoHideEnabled(!(ix1 && iy1 || ix2 && iy2));
	}

	private boolean showOnTop = true;
	private final DatePickerText dateBox;
	private DatePickerBox dpb = null;
	private static DatePickerBox lastOne = null;

	public DatePickerPanel() {
		this(null);
	}

	public DatePickerPanel(Element wrap) {
		addStyleName(Style.DATE_PICKER_PANEL);
		if (wrap == null) {
			dateBox = new DatePickerText(this);
			add(dateBox);
		} else {
			dateBox = DatePickerText.wrapDatePickerText(this, wrap);
		}
		dateBox.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				value = null;
				try {
					String date = dateBox.getValue();
					if (date != null && !date.isEmpty()) {
						value = DF.parse(dateBox.getValue());
					}
				} catch (Exception e) {
				}
			}
		});
		dateBox.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				setValue(DatePickerPanel.this.getValue(), true);
			}
		});
		if (wrap == null) {
			setValue(new Date());
		}
	}

	private DatePickerBox getDatePickerBox() {
		if (dpb == null) {
			dpb = new DatePickerBox();
			dpb.hide();
			dpb.setValue(getValue());
			final Command cmd = new Command() {
				@Override
				public void execute() {
					if (lastOne != null) {
						lastOne.hide();
						lastOne = null;
					}
					lastOne = dpb;
					dpb.setValue(getValue());
					dpb.setPopupPositionAndShow(new PositionCallback() {
						@Override
						public void setPosition(int offsetWidth,
								int offsetHeight) {
							if (showOnTop) {
								dpb
										.setPopupPosition(dateBox
												.getAbsoluteLeft(), dateBox
												.getAbsoluteTop()
												- offsetHeight);
							} else {
								dpb.setPopupPosition(dateBox.getAbsoluteLeft(),
										dateBox.getAbsoluteTop()
												+ dateBox.getOffsetHeight());
							}
						}
					});
				}
			};
			dateBox.addKeyUpHandler(new KeyUpHandler() {
				@Override
				public void onKeyUp(KeyUpEvent event) {
					dpb.setValue(DatePickerPanel.this.getValue(), false);

				}
			});
			dateBox.addFocusHandler(new FocusHandler() {
				@Override
				public void onFocus(FocusEvent event) {
					cmd.execute();
				}
			});
			dpb.addValueChangeHandler(new ValueChangeHandler<Date>() {
				@Override
				public void onValueChange(ValueChangeEvent<Date> event) {
					setValue(event.getValue(), true);
					dpb.hide();
					lastOne = null;
				}
			});
		}
		return dpb;
	}

	public void setEnabled(boolean enabled) {
		dateBox.setEnabled(enabled);
	}

	public void setShowOnTop(boolean showOnTop) {
		this.showOnTop = showOnTop;
	}

	public boolean getShowOnTop() {
		return showOnTop;
	}

	private Date value = null;

	@Override
	public Date getValue() {
		return value;
	}

	@Override
	public void setValue(Date value) {
		setValue(value, false);
	}

	@Override
	public void setValue(Date value, boolean fireEvents) {
		this.value = value;
		if (value != null) {
			dateBox.setText(DF.format(value));
		} else {
			dateBox.setText(null);
		}
		if (fireEvents) {
			ValueChangeEvent.fire(this, value);
		}
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<Date> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}
}
