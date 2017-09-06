
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
    "categories",
    "crosshair"
})
public class XAxi implements Serializable
{

    @JsonProperty("categories")
    private List<String> categories = new ArrayList<String>();
    @JsonProperty("crosshair")
    private Boolean crosshair;
    private final static long serialVersionUID = 4196953747859502459L;

    @JsonProperty("categories")
    public List<String> getCategories() {
        return categories;
    }

    @JsonProperty("categories")
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public XAxi withCategories(List<String> categories) {
        this.categories = categories;
        return this;
    }

    @JsonProperty("crosshair")
    public Boolean getCrosshair() {
        return crosshair;
    }

    @JsonProperty("crosshair")
    public void setCrosshair(Boolean crosshair) {
        this.crosshair = crosshair;
    }

    public XAxi withCrosshair(Boolean crosshair) {
        this.crosshair = crosshair;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(categories).append(crosshair).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof XAxi) == false) {
            return false;
        }
        XAxi rhs = ((XAxi) other);
        return new EqualsBuilder().append(categories, rhs.categories).append(crosshair, rhs.crosshair).isEquals();
    }

}
