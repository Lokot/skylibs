
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
    "labels",
    "opposite",
    "title"
})
public class YAxi implements Serializable
{

    @JsonProperty("labels")
    private Labels labels;
    @JsonProperty("opposite")
    private Boolean opposite;
    @JsonProperty("title")
    private Title_ title;
    private final static long serialVersionUID = -8896957771733877844L;

    @JsonProperty("labels")
    public Labels getLabels() {
        return labels;
    }

    @JsonProperty("labels")
    public void setLabels(Labels labels) {
        this.labels = labels;
    }

    public YAxi withLabels(Labels labels) {
        this.labels = labels;
        return this;
    }

    @JsonProperty("opposite")
    public Boolean getOpposite() {
        return opposite;
    }

    @JsonProperty("opposite")
    public void setOpposite(Boolean opposite) {
        this.opposite = opposite;
    }

    public YAxi withOpposite(Boolean opposite) {
        this.opposite = opposite;
        return this;
    }

    @JsonProperty("title")
    public Title_ getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(Title_ title) {
        this.title = title;
    }

    public YAxi withTitle(Title_ title) {
        this.title = title;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(labels).append(opposite).append(title).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof YAxi) == false) {
            return false;
        }
        YAxi rhs = ((YAxi) other);
        return new EqualsBuilder().append(labels, rhs.labels).append(opposite, rhs.opposite).append(title, rhs.title).isEquals();
    }

}
