package ru.skysoftlab.skylibs.web.ui.highcharts;

import java.io.Serializable;

import ru.skysoftlab.skylibs.vaadin.highcharts.HighChart;

/**
 * Интерфейс бина с графиком.
 * 
 * @author Lokot
 *
 */
public interface IHasChart extends Serializable {

	/**
	 * Возвращает график.
	 * 
	 * @return
	 */
	public HighChart getChart();
}
