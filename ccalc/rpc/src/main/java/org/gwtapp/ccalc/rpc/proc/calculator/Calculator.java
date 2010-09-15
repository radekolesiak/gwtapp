package org.gwtapp.ccalc.rpc.proc.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.gwtapp.ccalc.rpc.data.book.Calculation;
import org.gwtapp.ccalc.rpc.data.book.CalculationImpl;
import org.gwtapp.ccalc.rpc.data.book.Currency;
import org.gwtapp.ccalc.rpc.data.book.Operation;
import org.gwtapp.ccalc.rpc.data.book.OperationImpl;

public class Calculator {

	private final static List<String> FIELDS = new ArrayList<String>();
	static {
		FIELDS.addAll(new CalculationImpl().getPropertyNames());
		FIELDS.removeAll(new OperationImpl().getPropertyNames());
	}

	private final List<Calculation> calculations = new ArrayList<Calculation>();
	private final List<Calculation> summaries = new ArrayList<Calculation>();

	private final Currency baseCurrency;
	private final List<Integer> summariesPoints;

	public Calculator(Currency baseCurrency, List<Operation> operations) {
		this(baseCurrency, Arrays.asList(new Integer[] { operations.size() }),
				operations);
	}

	public Calculator(Currency baseCurrency, List<Integer> summariesPoints,
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
		public double v, r;

		public Edge(int x, int y, double v, double r) {
			this.x = x;
			this.y = y;
			this.v = v;
			this.r = r;
		}
	}

	public void calculate() {
		for (Currency currency : Currency.values()) {
			calculatePoints(currency);
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

	private void calculatePoints(Currency currency) {
		if (currency != baseCurrency) {
			List<Point> plus = new ArrayList<Point>();
			List<Point> minus = new ArrayList<Point>();
			for (int i = 0; i < calculations.size(); i++) {
				Calculation calculation = calculations.get(i);
				if (calculation.getValue() != null
						&& calculation.getCurrency() == currency) {
					Double value = r(calculation.getValue());
					Double signum = Math.signum(value);
					if (value >= 0 && signum > 0) {
						plus.add(new Point(i, value));
					} else if (value < 0 && signum < 0) {
						minus.add(new Point(i, -value));
					}
				}
			}
			List<Edge> edges = calculateEdges(currency, plus, minus);
			Collection<Point> ratio = calculateAverageRatio(edges);
		}
	}

	private List<Edge> calculateEdges(Currency currency, List<Point> plus,
			List<Point> minus) {
		List<Edge> edges = new ArrayList<Edge>();
		for (Point m : minus) {
			if (r(m.v) <= 0) {
				continue;
			}
			while (!plus.isEmpty() && r(plus.get(0).v) <= 0) {
				plus.remove(0);
			}
			while (!plus.isEmpty() && plus.get(0).i < m.i && r(m.v) > 0) {
				Point p = plus.get(0);
				double v = Math.min(p.v, m.v);
				p.v -= v;
				m.v -= v;
				double r = calculations.get(p.i).getExchange();
				edges.add(new Edge(p.i, m.i, v, r));
				if (r(p.v) <= 0) {
					plus.remove(0);
				}
			}
			if (r(m.v) > 0) {
				double r = calculations.get(m.i).getExchange();
				edges.add(new Edge(m.i, m.i, m.v, r));
				m.v = 0.0;
			}
		}
		return edges;
	}

	private Collection<Point> calculateAverageRatio(List<Edge> edges) {
		Map<Integer, Point> value = new HashMap<Integer, Point>();
		Map<Integer, Point> fifo = new HashMap<Integer, Point>();
		Map<Integer, Point> ratio = new HashMap<Integer, Point>();
		for (Edge edge : edges) {
			value.put(edge.y, new Point(edge.y, 0.0));
			fifo.put(edge.y, new Point(edge.y, 0.0));
			ratio.put(edge.y, new Point(edge.y, 0.0));
		}
		for (Edge edge : edges) {
			value.get(edge.y).v += edge.v * edge.r;
			fifo.get(edge.y).v += edge.v;
		}
		for (Map.Entry<Integer, Point> r : ratio.entrySet()) {
			int i = r.getKey();
			r.getValue().v = value.get(i).v / fifo.get(i).v;
		}
		return ratio.values();
	}
}
