
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
    "plotOptions",
    "series",
    "subtitle",
    "title",
    "xAxis",
    "yAxis"
})
public class BasicLineWithDataLabels implements Serializable
{

    @JsonProperty("chart")
    private Chart chart;
    @JsonProperty("plotOptions")
    private PlotOptions_ plotOptions;
    @JsonProperty("series")
    private List<Series__> series = new ArrayList<Series__>();
    @JsonProperty("subtitle")
    private Subtitle_ subtitle;
    @JsonProperty("title")
    private Title__ title;
    @JsonProperty("xAxis")
    private XAxis xAxis;
    @JsonProperty("yAxis")
    private YAxis_ yAxis;
    private final static long serialVersionUID = 2380315154058934176L;

    @JsonProperty("chart")
    public Chart getChart() {
        return chart;
    }

    @JsonProperty("chart")
    public void setChart(Chart chart) {
        this.chart = chart;
    }

    public BasicLineWithDataLabels withChart(Chart chart) {
        this.chart = chart;
        return this;
    }

    @JsonProperty("plotOptions")
    public PlotOptions_ getPlotOptions() {
        return plotOptions;
    }

    @JsonProperty("plotOptions")
    public void setPlotOptions(PlotOptions_ plotOptions) {
        this.plotOptions = plotOptions;
    }

    public BasicLineWithDataLabels withPlotOptions(PlotOptions_ plotOptions) {
        this.plotOptions = plotOptions;
        return this;
    }

    @JsonProperty("series")
    public List<Series__> getSeries() {
        return series;
    }

    @JsonProperty("series")
    public void setSeries(List<Series__> series) {
        this.series = series;
    }

    public BasicLineWithDataLabels withSeries(List<Series__> series) {
        this.series = series;
        return this;
    }

    @JsonProperty("subtitle")
    public Subtitle_ getSubtitle() {
        return subtitle;
    }

    @JsonProperty("subtitle")
    public void setSubtitle(Subtitle_ subtitle) {
        this.subtitle = subtitle;
    }

    public BasicLineWithDataLabels withSubtitle(Subtitle_ subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    @JsonProperty("title")
    public Title__ getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(Title__ title) {
        this.title = title;
    }

    public BasicLineWithDataLabels withTitle(Title__ title) {
        this.title = title;
        return this;
    }

    @JsonProperty("xAxis")
    public XAxis getXAxis() {
        return xAxis;
    }

    @JsonProperty("xAxis")
    public void setXAxis(XAxis xAxis) {
        this.xAxis = xAxis;
    }

    public BasicLineWithDataLabels withXAxis(XAxis xAxis) {
        this.xAxis = xAxis;
        return this;
    }

    @JsonProperty("yAxis")
    public YAxis_ getYAxis() {
        return yAxis;
    }

    @JsonProperty("yAxis")
    public void setYAxis(YAxis_ yAxis) {
        this.yAxis = yAxis;
    }

    public BasicLineWithDataLabels withYAxis(YAxis_ yAxis) {
        this.yAxis = yAxis;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(chart).append(plotOptions).append(series).append(subtitle).append(title).append(xAxis).append(yAxis).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BasicLineWithDataLabels) == false) {
            return false;
        }
        BasicLineWithDataLabels rhs = ((BasicLineWithDataLabels) other);
        return new EqualsBuilder().append(chart, rhs.chart).append(plotOptions, rhs.plotOptions).append(series, rhs.series).append(subtitle, rhs.subtitle).append(title, rhs.title).append(xAxis, rhs.xAxis).append(yAxis, rhs.yAxis).isEquals();
    }

}
