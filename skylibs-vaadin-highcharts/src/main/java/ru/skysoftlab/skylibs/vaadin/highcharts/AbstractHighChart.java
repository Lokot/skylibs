package ru.skysoftlab.skylibs.vaadin.highcharts;

import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScript;

/**
 * <p>Abstract Highcharts chart.</p>
 *
 * @author Stefan Endrullis
 */
public abstract class AbstractHighChart extends AbstractJavaScriptComponent {
	private static final long serialVersionUID = 7738496276049495017L;

	public static int currChartId = 0;

	public static int nextChartId() {
		return ++currChartId;
	}


	protected int chartId = nextChartId();

	/**
	 * Creates the chart object.
	 */
	public AbstractHighChart() {
		setId(getDomId());
		getState().domId = getDomId();
		getState().hcjs = "";
	}

	/**
	 * Returns the state of the chart that is shared with the web browser.
	 *
	 * @return the state of the chart that is shared with the web browser
	 */
	@Override
	protected HighChartState getState() {
		return (HighChartState) super.getState();
	}

	/**
	 * Returns the DOM ID of the chart component.
	 *
	 * @return the DOM ID of the chart component
	 */
	public String getDomId() {
		return "highchart_" + chartId;
	}

	/**
	 * <p>
	 * Sets the Highcharts JavaScript code describing the chart.
	 * Note that this code needs to bind the the JSON definition of the chart to a JS variable called <code>options</code>.
	 * </p>
	 * <p>Example:</p>
	 * <pre>  chart.setHcjs("var options = { chart: { title: 'my title' } };")</pre>
	 *
	 * @param hcjs Highcharts JavaScript code describing the chart
	 */
	public void setHcjs(String hcjs) {
		getState().hcjs = hcjs;
	}

	/**
	 * <p>
	 * Executes the given JavaScript code to manipulate the chart.
	 * Use the JavaScript variable <code>chart</code> to access the chart.
	 * </p>
	 * <p>Example:</p>
	 * <pre>  chart.manipulateChart("chart.addSeries({name: 'new', data: [1, 2]});");</pre>
	 *
	 * @param js JavaScript code to be executed
	 */
	public void manipulateChart(String js) {
		JavaScript.eval(
				"var chart = document.getElementById('" + getDomId() + "').chart;\n" + js
		);
	}
	
	public void updateOptions(String options) {
		JavaScript.eval(options + ";\n"
						+ "var chart = document.getElementById('" + getDomId() + "').chart;\n"
						+ "chart.update(options);"
		);
	}
}
