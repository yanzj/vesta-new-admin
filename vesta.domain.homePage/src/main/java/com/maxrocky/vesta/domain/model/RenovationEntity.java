package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by Annie on 2016/2/23.
 */
//@Entity
//@Table(name="home_renovation")
public class RenovationEntity {
    String content;

    @Id
    @Column(name = "CONTENT", nullable = false, insertable = true, updatable = true, length = 200)
    public String getContent() {return content;}

    public void setContent(String content) {this.content = content;}
}
