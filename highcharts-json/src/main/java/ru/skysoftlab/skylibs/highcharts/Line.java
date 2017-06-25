
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
    "dataLabels",
    "enableMouseTracking"
})
public class Line implements Serializable
{

    @JsonProperty("dataLabels")
    private DataLabels dataLabels;
    @JsonProperty("enableMouseTracking")
    private Boolean enableMouseTracking;
    private final static long serialVersionUID = 2058290966635961420L;

    @JsonProperty("dataLabels")
    public DataLabels getDataLabels() {
        return dataLabels;
    }

    @JsonProperty("dataLabels")
    public void setDataLabels(DataLabels dataLabels) {
        this.dataLabels = dataLabels;
    }

    public Line withDataLabels(DataLabels dataLabels) {
        this.dataLabels = dataLabels;
        return this;
    }

    @JsonProperty("enableMouseTracking")
    public Boolean getEnableMouseTracking() {
        return enableMouseTracking;
    }

    @JsonProperty("enableMouseTracking")
    public void setEnableMouseTracking(Boolean enableMouseTracking) {
        this.enableMouseTracking = enableMouseTracking;
    }

    public Line withEnableMouseTracking(Boolean enableMouseTracking) {
        this.enableMouseTracking = enableMouseTracking;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(dataLabels).append(enableMouseTracking).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Line) == false) {
            return false;
        }
        Line rhs = ((Line) other);
        return new EqualsBuilder().append(dataLabels, rhs.dataLabels).append(enableMouseTracking, rhs.enableMouseTracking).isEquals();
    }

}
