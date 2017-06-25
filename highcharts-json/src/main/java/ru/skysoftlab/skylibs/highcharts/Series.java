
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
    "pointStart"
})
public class Series implements Serializable
{

    @JsonProperty("pointStart")
    private Integer pointStart;
    private final static long serialVersionUID = -7998110894253200784L;

    @JsonProperty("pointStart")
    public Integer getPointStart() {
        return pointStart;
    }

    @JsonProperty("pointStart")
    public void setPointStart(Integer pointStart) {
        this.pointStart = pointStart;
    }

    public Series withPointStart(Integer pointStart) {
        this.pointStart = pointStart;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(pointStart).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Series) == false) {
            return false;
        }
        Series rhs = ((Series) other);
        return new EqualsBuilder().append(pointStart, rhs.pointStart).isEquals();
    }

}
