
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
    "title"
})
public class YAxis_ implements Serializable
{

    @JsonProperty("title")
    private Title___ title;
    private final static long serialVersionUID = 2846849549216734625L;

    @JsonProperty("title")
    public Title___ getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(Title___ title) {
        this.title = title;
    }

    public YAxis_ withTitle(Title___ title) {
        this.title = title;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(title).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof YAxis_) == false) {
            return false;
        }
        YAxis_ rhs = ((YAxis_) other);
        return new EqualsBuilder().append(title, rhs.title).isEquals();
    }

}
