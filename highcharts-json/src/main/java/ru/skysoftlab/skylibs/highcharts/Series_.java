
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
    "data",
    "name",
    "tooltip",
    "type",
    "yAxis"
})
public class Series_ implements Serializable
{

    @JsonProperty("data")
    private List<Double> data = new ArrayList<Double>();
    @JsonProperty("name")
    private String name;
    @JsonProperty("tooltip")
    private Tooltip tooltip;
    @JsonProperty("type")
    private String type;
    @JsonProperty("yAxis")
    private Integer yAxis;
    private final static long serialVersionUID = 11842444378089954L;

    @JsonProperty("data")
    public List<Double> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<Double> data) {
        this.data = data;
    }

    public Series_ withData(List<Double> data) {
        this.data = data;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Series_ withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("tooltip")
    public Tooltip getTooltip() {
        return tooltip;
    }

    @JsonProperty("tooltip")
    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }

    public Series_ withTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public Series_ withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("yAxis")
    public Integer getYAxis() {
        return yAxis;
    }

    @JsonProperty("yAxis")
    public void setYAxis(Integer yAxis) {
        this.yAxis = yAxis;
    }

    public Series_ withYAxis(Integer yAxis) {
        this.yAxis = yAxis;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(data).append(name).append(tooltip).append(type).append(yAxis).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Series_) == false) {
            return false;
        }
        Series_ rhs = ((Series_) other);
        return new EqualsBuilder().append(data, rhs.data).append(name, rhs.name).append(tooltip, rhs.tooltip).append(type, rhs.type).append(yAxis, rhs.yAxis).isEquals();
    }

}
