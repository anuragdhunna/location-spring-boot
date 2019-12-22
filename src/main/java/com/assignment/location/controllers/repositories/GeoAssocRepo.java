package com.assignment.location.controllers.repositories;

import com.assignment.location.models.GeoAssoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author anuragdhunna
 */
@Repository
public interface GeoAssocRepo extends JpaRepository<GeoAssoc, Integer> {

    @Query(value = "SELECT * FROM  geo_assoc where code=:code AND code_to=:codeTo AND relation_type=:relationType", nativeQuery = true)
    GeoAssoc findRelation(int code, int codeTo, GeoAssoc.RelationType relationType);
}
