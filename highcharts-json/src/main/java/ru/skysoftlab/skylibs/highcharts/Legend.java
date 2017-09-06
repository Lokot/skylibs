
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
    "align",
    "backgroundColor",
    "floating",
    "layout",
    "verticalAlign",
    "x",
    "y"
})
public class Legend implements Serializable
{

    @JsonProperty("align")
    private String align;
    @JsonProperty("backgroundColor")
    private String backgroundColor;
    @JsonProperty("floating")
    private Boolean floating;
    @JsonProperty("layout")
    private String layout;
    @JsonProperty("verticalAlign")
    private String verticalAlign;
    @JsonProperty("x")
    private Integer x;
    @JsonProperty("y")
    private Integer y;
    private final static long serialVersionUID = 3047203586510623551L;

    @JsonProperty("align")
    public String getAlign() {
        return align;
    }

    @JsonProperty("align")
    public void setAlign(String align) {
        this.align = align;
    }

    public Legend withAlign(String align) {
        this.align = align;
        return this;
    }

    @JsonProperty("backgroundColor")
    public String getBackgroundColor() {
        return backgroundColor;
    }

    @JsonProperty("backgroundColor")
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Legend withBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    @JsonProperty("floating")
    public Boolean getFloating() {
        return floating;
    }

    @JsonProperty("floating")
    public void setFloating(Boolean floating) {
        this.floating = floating;
    }

    public Legend withFloating(Boolean floating) {
        this.floating = floating;
        return this;
    }

    @JsonProperty("layout")
    public String getLayout() {
        return layout;
    }

    @JsonProperty("layout")
    public void setLayout(String layout) {
        this.layout = layout;
    }

    public Legend withLayout(String layout) {
        this.layout = layout;
        return this;
    }

    @JsonProperty("verticalAlign")
    public String getVerticalAlign() {
        return verticalAlign;
    }

    @JsonProperty("verticalAlign")
    public void setVerticalAlign(String verticalAlign) {
        this.verticalAlign = verticalAlign;
    }

    public Legend withVerticalAlign(String verticalAlign) {
        this.verticalAlign = verticalAlign;
        return this;
    }

    @JsonProperty("x")
    public Integer getX() {
        return x;
    }

    @JsonProperty("x")
    public void setX(Integer x) {
        this.x = x;
    }

    public Legend withX(Integer x) {
        this.x = x;
        return this;
    }

    @JsonProperty("y")
    public Integer getY() {
        return y;
    }

    @JsonProperty("y")
    public void setY(Integer y) {
        this.y = y;
    }

    public Legend withY(Integer y) {
        this.y = y;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(align).append(backgroundColor).append(floating).append(layout).append(verticalAlign).append(x).append(y).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Legend) == false) {
            return false;
        }
        Legend rhs = ((Legend) other);
        return new EqualsBuilder().append(align, rhs.align).append(backgroundColor, rhs.backgroundColor).append(floating, rhs.floating).append(layout, rhs.layout).append(verticalAlign, rhs.verticalAlign).append(x, rhs.x).append(y, rhs.y).isEquals();
    }

}
