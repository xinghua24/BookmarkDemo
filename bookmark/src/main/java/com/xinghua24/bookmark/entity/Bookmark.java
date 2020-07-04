package com.xinghua24.bookmark.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "bookmark")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(name = "url")
    @NotBlank(message = "Url is mandatory")
    private String url;

    @ManyToOne
    @JoinColumn(name="list_id", nullable=false)
    private BookmarkList bookmarkList;

    public Bookmark(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(id).append(name).append(url).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(name).append(url).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Bookmark rhs = (Bookmark) obj;
        return new EqualsBuilder()
                .append(id, rhs.id).append(name, rhs.name).append(url, rhs.url)
                .isEquals();
    }
}
