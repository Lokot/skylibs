
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
    "type",
    "zoomType"
})
public class Chart implements Serializable
{

    @JsonProperty("type")
    private String type;
    @JsonProperty("zoomType")
    private String zoomType;
    private final static long serialVersionUID = -1222739213931640156L;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public Chart withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("zoomType")
    public String getZoomType() {
        return zoomType;
    }

    @JsonProperty("zoomType")
    public void setZoomType(String zoomType) {
        this.zoomType = zoomType;
    }

    public Chart withZoomType(String zoomType) {
        this.zoomType = zoomType;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(type).append(zoomType).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Chart) == false) {
            return false;
        }
        Chart rhs = ((Chart) other);
        return new EqualsBuilder().append(type, rhs.type).append(zoomType, rhs.zoomType).isEquals();
    }

}
