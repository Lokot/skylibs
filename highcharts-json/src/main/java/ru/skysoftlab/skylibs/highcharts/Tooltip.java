
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
    "valueSuffix"
})
public class Tooltip implements Serializable
{

    @JsonProperty("valueSuffix")
    private String valueSuffix;
    private final static long serialVersionUID = 122483174149874694L;

    @JsonProperty("valueSuffix")
    public String getValueSuffix() {
        return valueSuffix;
    }

    @JsonProperty("valueSuffix")
    public void setValueSuffix(String valueSuffix) {
        this.valueSuffix = valueSuffix;
    }

    public Tooltip withValueSuffix(String valueSuffix) {
        this.valueSuffix = valueSuffix;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(valueSuffix).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Tooltip) == false) {
            return false;
        }
        Tooltip rhs = ((Tooltip) other);
        return new EqualsBuilder().append(valueSuffix, rhs.valueSuffix).isEquals();
    }

}
