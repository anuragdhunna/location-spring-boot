package com.assignment.location.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author anuragdhunna
 */
@Entity
@IdClass(GeoCompositeId.class) // Used for creating the composite keys
@Table(name = "geo")
public class Geo {

    public Geo() {
    }

    public Geo(String name, Type locationType, int code, String urbanStatus) {
        this.name = name;
        this.locationType = locationType;
        this.code = code;
        this.urbanStatus = urbanStatus;
    }

    public enum Type {
        STATE, DISTRICT, CITY
    }

    @Id
    @Column(unique = true)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type locationType;

    @Id
    private int code;

    private String urbanStatus;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getLocationType() {
        return locationType;
    }

    public void setLocationType(Type locationType) {
        this.locationType = locationType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUrbanStatus() {
        return urbanStatus;
    }

    public void setUrbanStatus(String urbanStatus) {
        this.urbanStatus = urbanStatus;
    }
}
