
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
    "style",
    "text"
})
public class Title_ implements Serializable
{

    @JsonProperty("style")
    private Style_ style;
    @JsonProperty("text")
    private String text;
    private final static long serialVersionUID = -7306556071834763294L;

    @JsonProperty("style")
    public Style_ getStyle() {
        return style;
    }

    @JsonProperty("style")
    public void setStyle(Style_ style) {
        this.style = style;
    }

    public Title_ withStyle(Style_ style) {
        this.style = style;
        return this;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    public Title_ withText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(style).append(text).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Title_) == false) {
            return false;
        }
        Title_ rhs = ((Title_) other);
        return new EqualsBuilder().append(style, rhs.style).append(text, rhs.text).isEquals();
    }

}
