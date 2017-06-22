package me.gladysz.model;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractDomainClass implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Integer version;
    private Date dateCreated;
    private Date lastUpdated;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    @PrePersist
    @PreUpdate
    public void updateTimestamps() {
        if (this.dateCreated == null) {
            this.dateCreated = new Date();
        }
        this.lastUpdated = new Date();
    }
}
