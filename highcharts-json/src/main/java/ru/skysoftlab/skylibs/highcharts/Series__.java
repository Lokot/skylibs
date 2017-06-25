
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
    "name"
})
public class Series__ implements Serializable
{

    @JsonProperty("data")
    private List<Integer> data = new ArrayList<Integer>();
    @JsonProperty("name")
    private String name;
    private final static long serialVersionUID = -4550762370177648667L;

    @JsonProperty("data")
    public List<Integer> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<Integer> data) {
        this.data = data;
    }

    public Series__ withData(List<Integer> data) {
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

    public Series__ withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(data).append(name).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Series__) == false) {
            return false;
        }
        Series__ rhs = ((Series__) other);
        return new EqualsBuilder().append(data, rhs.data).append(name, rhs.name).isEquals();
    }

}
