package org.it.discovery.training.hibernate.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

//@MappedSuperclass
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BaseEntity {
    private int id;

    private LocalDateTime created;

    private LocalDateTime modified;

    @Id
    @GeneratedValue(generator = "offset")
    @GenericGenerator(name = "offset", strategy = "org.it.discovery.training.hibernate.generator.OffsetGenerator")
    //@Column(name = "ID", length = 40)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

}
