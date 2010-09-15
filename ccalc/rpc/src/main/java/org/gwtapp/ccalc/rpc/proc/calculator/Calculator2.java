package org.gwtapp.ccalc.rpc.proc.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.gwtapp.ccalc.rpc.data.book.Calculation;
import org.gwtapp.ccalc.rpc.data.book.CalculationImpl;
import org.gwtapp.ccalc.rpc.data.book.Currency;
import org.gwtapp.ccalc.rpc.data.book.Operation;
import org.gwtapp.ccalc.rpc.data.book.OperationImpl;

public class Calculator2 {
	private final static List<String> FIELDS = new ArrayList<String>();
	static {
		FIELDS.addAll(new CalculationImpl().getPropertyNames());
		FIELDS.removeAll(new OperationImpl().getPropertyNames());
	}

	private final List<Calculation> calculations = new ArrayList<Calculation>();
	private final List<Calculation> summaries = new ArrayList<Calculation>();
	private final Map<Currency, List<Edge>> edges = new HashMap<Currency, List<Edge>>();
	{
		for (Currency currency : Currency.values()) {
			edges.put(currency, new ArrayList<Edge>());
		}
	}

	private final Currency baseCurrency;
	private final List<Integer> summariesPoints;

	public Calculator2(Currency baseCurrency, List<Operation> operations) {
		this(baseCurrency, Arrays.asList(new Integer[] { operations.size() }),
				operations);
	}

	public Calculator2(Currency baseCurrency, List<Integer> summariesPoints,
			List<Operation> operations) {
		this.baseCurrency = baseCurrency;
		this.summariesPoints = summariesPoints;
		for (Operation operation : operations) {
			Calculation calculation = new CalculationImpl(operation);
			for (String property : FIELDS) {
				calculation.set(property, null);
			}
			calculations.add(calculation);
		}
		calculate();
	}

	public List<Calculation> getCalculations() {
		return calculations;
	}

	public List<Calculation> getSummaries() {
		return summaries;
	}

	private class Point {
		public int i;
		public double v;

		public Point(int i, double v) {
			this.i = i;
			this.v = v;
		}
	}

	public static class Edge {
		public int x, y;
		public double v;

		public Edge(int x, int y, double v) {
			this.x = x;
			this.y = y;
			this.v = v;
		}
	}

	public void calculate() {
		for (Currency currency : Currency.values()) {
			calculate(currency);
		}
		calculateSummary();
	}

	private void calculateSummary() {
		Iterator<Calculation> it = calculations.iterator();
		for (Integer count : summariesPoints) {
			Calculation S = new CalculationImpl();
			while (count-- > 0) {
				Calculation c = r(it.next());
				S.setFifoBase(o(S.getFifoBase()) + o(c.getFifoBase()));
				S.setIncome(o(S.getIncome()) + o(c.getIncome()));
				S.setCost(o(S.getCost()) + o(c.getCost()));
			}
			summaries.add(r(S));
		}
	}

	private double o(Double v) {
		if (v == null) {
			return 0.0;
		} else {
			return v;
		}
	}

	public static Double r(Double v) {
		if (v == null) {
			return null;
		}
		return Math.round(v * 1e2) / 1e2;
	}

	public static Double q(Double v) {
		if (v == null) {
			return null;
		}
		return Math.round(v * 1e4) / 1e4;
	}

	public static Calculation r(Calculation c) {
		c.setFifoBase(r(c.getFifoBase()));
		c.setIncome(r(c.getIncome()));
		c.setCost(r(c.getCost()));
		return c;
	}

	private void calculate(Currency currency) {
		List<Point> plus = new ArrayList<Point>();
		List<Point> minus = new ArrayList<Point>();
		if (currency != baseCurrency) {
			for (int i = 0; i < calculations.size(); i++) {
				Calculation calculation = calculations.get(i);
				if (calculation.getValue() != null
						&& calculation.getCurrency() == currency) {
					Double value = r(calculation.getValue());
					Double signum = Math.signum(value);
					if (value < 0 && signum < 0) {
						minus.add(new Point(i, -value));
					} else if (value >= 0 && signum > 0) {
						plus.add(new Point(i, value));
					}
				}
			}
			Map<Integer, Double> averages = new HashMap<Integer, Double>();
			while (!plus.isEmpty() && !minus.isEmpty()) {
				while (!plus.isEmpty() && plus.get(0).v <= 0) {
					plus.remove(0);
				}
				while (!minus.isEmpty() && minus.get(0).v <= 0) {
					minus.remove(0);
				}
				if (!plus.isEmpty() && !minus.isEmpty()) {
					double v = r(Math.min(plus.get(0).v, minus.get(0).v));
					plus.get(0).v = r(plus.get(0).v - v);
					minus.get(0).v = r(minus.get(0).v - v);
					edges.get(currency).add(
							new Edge(plus.get(0).i, minus.get(0).i, v));
					calculations.get(plus.get(0).i).setFifo(currency,
							plus.get(0).v);

					// calculating of an average exchange
					if (!averages.containsKey(minus.get(0).i)) {
						averages.put(minus.get(0).i, 0.0);
					}
					Double value = calculations.get(minus.get(0).i).getValue();
					Double exchange = calculations.get(plus.get(0).i)
							.getExchange();
					Double average = averages.get(minus.get(0).i);
					if (exchange == null) {
						exchange = 0.0;
					}
					average *= value;
					average += v * exchange;
					average /= value;
					calculations.get(minus.get(0).i).setFifo(currency, average);
					averages.put(minus.get(0).i, average);
				}
			}
			while (!plus.isEmpty()) {
				calculations.get(plus.get(0).i)
						.setFifo(currency, plus.get(0).v);
				plus.remove(0);
			}
		}
		// calculate fifoValue
		for (int i = 0; i < calculations.size(); i++) {
			Calculation calculation = calculations.get(i);
			if (calculation.getCurrency() != currency) {
				continue;
			}
			{
				Double value = calculation.getValue();
				if (value != null) {
					Double signum = Math.signum(value);
					if (value >= 0 && signum > 0) {
						Double fifoValue = null;
						if (currency == baseCurrency) {
							fifoValue = value;
						} else {
							if (calculation.getExchange() != null) {
								fifoValue = value * calculation.getExchange();
							}
						}
						calculation.setFifoBase(fifoValue);
					}
					if (value <= 0 && signum < 0) {
						Double fifoValue = null;
						if (currency == baseCurrency) {
							fifoValue = value;
						} else {
							if (calculation.getFifo(currency) != null) {
								fifoValue = value
										* -calculation.getFifo(currency);
							}
						}
						calculation.setFifoBase(fifoValue);
					}
				}
			}
			{
				Double value = null;
				value = calculation.getValue();
				if (value != null) {
					Double signum = Math.signum(value);
					if (value >= 0 && signum > 0) {
						Double incomeValue = null;
						if (currency == baseCurrency) {
							incomeValue = value;
						} else {
							if (calculation.getExchange() != null) {
								incomeValue = value * calculation.getExchange();
							}
						}
						calculation.setIncome(incomeValue);
					} else if (value <= 0 && signum < 0) {
						Double costValue = null;
						value = -value;
						if (currency == baseCurrency) {
							costValue = value;
						} else {
							if (calculation.getExchange() != null) {
								costValue = value * calculation.getExchange();
							}
						}
						calculation.setCost(costValue);
					}
				}
			}
		}
	}

	/*-
	public List<Edge> getComponents(Currency currency, int row, boolean edgeX) {
		List<Edge> components = new ArrayList<Edge>();
		for (Edge edge : edges.get(currency)) {
			if (edgeX) {
				if (edge.x == row) {
					components.add(edge);
				}
			} else {
				if (edge.y == row) {
					components.add(edge);
				}
			}
		}
		return components;
	}
	 */
}
