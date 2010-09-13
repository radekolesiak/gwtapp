package org.gwtapp.ccalc.client;

import org.gwtapp.ccalc.client.ui.CCalcPanel;

import com.google.gwt.inject.client.Ginjector;

public interface CCalcGinjector extends Ginjector {
	CCalcPanel getCCalcPanel();
}
