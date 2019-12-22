package com.assignment.location.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author anuragdhunna
 */
@Entity
@Table(name = "next_seq")
public class NextSeq {

    @Id
    @GeneratedValue
    private Long id;
    private String tableName;
    private Long nextSeqId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Long getNextSeqId() {
        return nextSeqId;
    }

    public void setNextSeqId(Long nextSeqId) {
        this.nextSeqId = nextSeqId;
    }
}
