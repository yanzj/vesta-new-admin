package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Magic on 2016/5/12.
 */
@Entity
@Table(name = "room_location")
public class RoomLocationEntititly {
    private String id;
    private String name;
    private Date createOn;

    @Id
    @Column(name = "ID",length = 50,unique = true,nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME",length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "CREATE_ON")
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
}
