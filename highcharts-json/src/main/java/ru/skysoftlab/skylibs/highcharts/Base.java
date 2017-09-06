
package ru.skysoftlab.skylibs.highcharts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "chart",
    "legend",
    "plotOptions",
    "series",
    "subtitle",
    "title",
    "tooltip",
    "xAxis",
    "yAxis"
})
public class Base implements Serializable
{

    @JsonProperty("chart")
    private Chart chart;
    @JsonProperty("legend")
    private Legend legend;
    @JsonProperty("plotOptions")
    private PlotOptions plotOptions;
    @JsonProperty("series")
    private List<Series_> series = new ArrayList<Series_>();
    @JsonProperty("subtitle")
    private Subtitle subtitle;
    @JsonProperty("title")
    private Title title;
    @JsonProperty("tooltip")
    private Tooltip_ tooltip;
    @JsonProperty("xAxis")
    private List<XAxi> xAxis = new ArrayList<XAxi>();
    @JsonProperty("yAxis")
    private List<YAxi> yAxis = new ArrayList<YAxi>();
    private final static long serialVersionUID = -2201630468444317528L;

    @JsonProperty("chart")
    public Chart getChart() {
        return chart;
    }

    @JsonProperty("chart")
    public void setChart(Chart chart) {
        this.chart = chart;
    }

    public Base withChart(Chart chart) {
        this.chart = chart;
        return this;
    }

    @JsonProperty("legend")
    public Legend getLegend() {
        return legend;
    }

    @JsonProperty("legend")
    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    public Base withLegend(Legend legend) {
        this.legend = legend;
        return this;
    }

    @JsonProperty("plotOptions")
    public PlotOptions getPlotOptions() {
        return plotOptions;
    }

    @JsonProperty("plotOptions")
    public void setPlotOptions(PlotOptions plotOptions) {
        this.plotOptions = plotOptions;
    }

    public Base withPlotOptions(PlotOptions plotOptions) {
        this.plotOptions = plotOptions;
        return this;
    }

    @JsonProperty("series")
    public List<Series_> getSeries() {
        return series;
    }

    @JsonProperty("series")
    public void setSeries(List<Series_> series) {
        this.series = series;
    }

    public Base withSeries(List<Series_> series) {
        this.series = series;
        return this;
    }

    @JsonProperty("subtitle")
    public Subtitle getSubtitle() {
        return subtitle;
    }

    @JsonProperty("subtitle")
    public void setSubtitle(Subtitle subtitle) {
        this.subtitle = subtitle;
    }

    public Base withSubtitle(Subtitle subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    @JsonProperty("title")
    public Title getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(Title title) {
        this.title = title;
    }

    public Base withTitle(Title title) {
        this.title = title;
        return this;
    }

    @JsonProperty("tooltip")
    public Tooltip_ getTooltip() {
        return tooltip;
    }

    @JsonProperty("tooltip")
    public void setTooltip(Tooltip_ tooltip) {
        this.tooltip = tooltip;
    }

    public Base withTooltip(Tooltip_ tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    @JsonProperty("xAxis")
    public List<XAxi> getXAxis() {
        return xAxis;
    }

    @JsonProperty("xAxis")
    public void setXAxis(List<XAxi> xAxis) {
        this.xAxis = xAxis;
    }

    public Base withXAxis(List<XAxi> xAxis) {
        this.xAxis = xAxis;
        return this;
    }

    @JsonProperty("yAxis")
    public List<YAxi> getYAxis() {
        return yAxis;
    }

    @JsonProperty("yAxis")
    public void setYAxis(List<YAxi> yAxis) {
        this.yAxis = yAxis;
    }

    public Base withYAxis(List<YAxi> yAxis) {
        this.yAxis = yAxis;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(chart).append(legend).append(plotOptions).append(series).append(subtitle).append(title).append(tooltip).append(xAxis).append(yAxis).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Base) == false) {
            return false;
        }
        Base rhs = ((Base) other);
        return new EqualsBuilder().append(chart, rhs.chart).append(legend, rhs.legend).append(plotOptions, rhs.plotOptions).append(series, rhs.series).append(subtitle, rhs.subtitle).append(title, rhs.title).append(tooltip, rhs.tooltip).append(xAxis, rhs.xAxis).append(yAxis, rhs.yAxis).isEquals();
    }

}
