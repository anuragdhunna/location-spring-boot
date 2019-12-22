package com.assignment.location.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author anuragdhunna
 */
@Entity
@IdClass(GeoAssocCompositeId.class) // Used for creating the composite keys
@Table(name = "geo_assoc")
public class GeoAssoc {

    public GeoAssoc() {
    }

    public GeoAssoc(int code, int codeTo, RelationType relationType) {
        this.code = code;
        this.codeTo = codeTo;
        this.relationType = relationType;
    }

    public GeoAssoc(int code, int codeTo, RelationType relationType, Long id) {
        this.code = code;
        this.codeTo = codeTo;
        this.relationType = relationType;
        this.id = id;
    }

    public enum RelationType {
        DIST_CITY, STATE_DIST
    }

    @Id
    @Column(unique = true)
    private Long id;

    private int code;
    private int codeTo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RelationType relationType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCodeTo() {
        return codeTo;
    }

    public void setCodeTo(int codeTo) {
        this.codeTo = codeTo;
    }

    public RelationType getRelationType() {
        return relationType;
    }

    public void setRelationType(RelationType relationType) {
        this.relationType = relationType;
    }
}
