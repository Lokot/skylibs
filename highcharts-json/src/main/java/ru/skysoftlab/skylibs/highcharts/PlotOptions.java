
package ru.skysoftlab.skylibs.highcharts;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "line",
    "series"
})
public class PlotOptions implements Serializable
{

    @JsonProperty("line")
    private Line line;
    @JsonProperty("series")
    private Series series;
    private final static long serialVersionUID = -1267275120050335389L;

    @JsonProperty("line")
    public Line getLine() {
        return line;
    }

    @JsonProperty("line")
    public void setLine(Line line) {
        this.line = line;
    }

    public PlotOptions withLine(Line line) {
        this.line = line;
        return this;
    }

    @JsonProperty("series")
    public Series getSeries() {
        return series;
    }

    @JsonProperty("series")
    public void setSeries(Series series) {
        this.series = series;
    }

    public PlotOptions withSeries(Series series) {
        this.series = series;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(line).append(series).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PlotOptions) == false) {
            return false;
        }
        PlotOptions rhs = ((PlotOptions) other);
        return new EqualsBuilder().append(line, rhs.line).append(series, rhs.series).isEquals();
    }

}
