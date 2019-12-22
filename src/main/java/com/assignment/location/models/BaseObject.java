package com.assignment.location.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.util.Date;
import javax.persistence.Id;

/**
 * @author anuragdhunna
 */
public abstract class BaseObject {

    @Id
    private Long id;

    @CreatedDate
    private Date creationDate = new Date();

    @JsonIgnore
    private boolean deleted = false;

    @JsonIgnore
    @LastModifiedDate
    private boolean updated = false;

    @JsonIgnore
    private  boolean isActive = false;

    @JsonIgnore
    @LastModifiedDate
    private Date modified;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
