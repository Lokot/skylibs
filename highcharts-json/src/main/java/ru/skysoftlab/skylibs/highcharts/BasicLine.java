
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
    "legend",
    "plotOptions",
    "series",
    "subtitle",
    "title",
    "yAxis"
})
public class BasicLine implements Serializable
{

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
    @JsonProperty("yAxis")
    private YAxis yAxis;
    private final static long serialVersionUID = 5163236378921313894L;

    @JsonProperty("legend")
    public Legend getLegend() {
        return legend;
    }

    @JsonProperty("legend")
    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    public BasicLine withLegend(Legend legend) {
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

    public BasicLine withPlotOptions(PlotOptions plotOptions) {
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

    public BasicLine withSeries(List<Series_> series) {
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

    public BasicLine withSubtitle(Subtitle subtitle) {
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

    public BasicLine withTitle(Title title) {
        this.title = title;
        return this;
    }

    @JsonProperty("yAxis")
    public YAxis getYAxis() {
        return yAxis;
    }

    @JsonProperty("yAxis")
    public void setYAxis(YAxis yAxis) {
        this.yAxis = yAxis;
    }

    public BasicLine withYAxis(YAxis yAxis) {
        this.yAxis = yAxis;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(legend).append(plotOptions).append(series).append(subtitle).append(title).append(yAxis).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BasicLine) == false) {
            return false;
        }
        BasicLine rhs = ((BasicLine) other);
        return new EqualsBuilder().append(legend, rhs.legend).append(plotOptions, rhs.plotOptions).append(series, rhs.series).append(subtitle, rhs.subtitle).append(title, rhs.title).append(yAxis, rhs.yAxis).isEquals();
    }

}
