package org.gwtapp.ccalc.client;

import org.gwtapp.ccalc.client.ui.CCalcPanel;
import org.gwtapp.ccalc.client.ui.ContentPanel;
import org.gwtapp.ccalc.client.ui.calculations.CalculationsItemFormPanel;

import com.google.gwt.inject.client.Ginjector;

public interface CCalcGinjector extends Ginjector {
	CCalcPanel getCCalcPanel();
	ContentPanel getContentPanel();
	CalculationsItemFormPanel getCalculationsItemFormPanel();
}
