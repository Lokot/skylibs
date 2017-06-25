
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
    "series"
})
public class PlotOptions implements Serializable
{

    @JsonProperty("series")
    private Series series;
    private final static long serialVersionUID = 6204375343241429241L;

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
        return new HashCodeBuilder().append(series).toHashCode();
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
        return new EqualsBuilder().append(series, rhs.series).isEquals();
    }

}
