package com.xinghua24.bookmark.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "provider")
    @NotBlank
    private String provider;

    @Column(name = "provider_id")
    @NotBlank
    private String providerId;

    @Column(name = "created_at")
    @NotBlank
    private Date createdAt;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    @JsonIgnore
    List<BookmarkList> bookmarkLists = new ArrayList<>();

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(id)
                .append(name)
                .append(provider)
                .append(providerId)
                .append(createdAt).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id)
                .append(name)
                .append(provider)
                .append(providerId)
                .append(createdAt).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        User rhs = (User) obj;
        return new EqualsBuilder().append(id, rhs.id)
                .append(name, rhs.name)
                .append(provider, rhs.provider)
                .append(providerId, rhs.providerId)
                .append(createdAt, rhs.createdAt)
                .isEquals();
    }
}
