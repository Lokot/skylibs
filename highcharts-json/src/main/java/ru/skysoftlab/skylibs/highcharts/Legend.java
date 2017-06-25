
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
    "layout",
    "verticalAlign"
})
public class Legend implements Serializable
{

    @JsonProperty("align")
    private String align;
    @JsonProperty("layout")
    private String layout;
    @JsonProperty("verticalAlign")
    private String verticalAlign;
    private final static long serialVersionUID = 9137308649896142849L;

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(align).append(layout).append(verticalAlign).toHashCode();
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
        return new EqualsBuilder().append(align, rhs.align).append(layout, rhs.layout).append(verticalAlign, rhs.verticalAlign).isEquals();
    }

}
