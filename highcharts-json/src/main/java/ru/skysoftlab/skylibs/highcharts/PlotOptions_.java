
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
    "line"
})
public class PlotOptions_ implements Serializable
{

    @JsonProperty("line")
    private Line line;
    private final static long serialVersionUID = 6766800427135557431L;

    @JsonProperty("line")
    public Line getLine() {
        return line;
    }

    @JsonProperty("line")
    public void setLine(Line line) {
        this.line = line;
    }

    public PlotOptions_ withLine(Line line) {
        this.line = line;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(line).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PlotOptions_) == false) {
            return false;
        }
        PlotOptions_ rhs = ((PlotOptions_) other);
        return new EqualsBuilder().append(line, rhs.line).isEquals();
    }

}
