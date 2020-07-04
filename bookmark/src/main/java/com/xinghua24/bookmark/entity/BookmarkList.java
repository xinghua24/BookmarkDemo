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
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Table(name = "bookmark_list")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    User user;

    @OneToMany(mappedBy="bookmarkList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Bookmark> bookmarks = new ArrayList<>();

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(id).append(name).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(name).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        BookmarkList rhs = (BookmarkList) obj;
        return new EqualsBuilder()
                .append(id, rhs.id).append(name, rhs.name)
                .isEquals();
    }
}
