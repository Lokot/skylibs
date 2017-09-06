
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
    "format",
    "style"
})
public class Labels implements Serializable
{

    @JsonProperty("format")
    private String format;
    @JsonProperty("style")
    private Style style;
    private final static long serialVersionUID = -1699914303859646899L;

    @JsonProperty("format")
    public String getFormat() {
        return format;
    }

    @JsonProperty("format")
    public void setFormat(String format) {
        this.format = format;
    }

    public Labels withFormat(String format) {
        this.format = format;
        return this;
    }

    @JsonProperty("style")
    public Style getStyle() {
        return style;
    }

    @JsonProperty("style")
    public void setStyle(Style style) {
        this.style = style;
    }

    public Labels withStyle(Style style) {
        this.style = style;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(format).append(style).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Labels) == false) {
            return false;
        }
        Labels rhs = ((Labels) other);
        return new EqualsBuilder().append(format, rhs.format).append(style, rhs.style).isEquals();
    }

}
