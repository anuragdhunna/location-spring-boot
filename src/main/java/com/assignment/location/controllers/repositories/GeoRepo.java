package com.assignment.location.controllers.repositories;

import com.assignment.location.models.Geo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author anuragdhunna
 */
@Repository
public interface GeoRepo extends JpaRepository<Geo, Integer> {

    @Query(value = "SELECT * FROM geo where geo.name LIKE :searchText% AND geo.location_type=:type", nativeQuery = true)
    List<Geo> searchLocation(String searchText, String type);

    @Query(value = "SELECT t1.name as statename, t12.name as distname, t12.code as distcode from\n" +
            "geo t1 inner join geo_assoc t2 on t1.code = t2.code \n" +
            "inner join geo t12 on t2.code_to = t12.code AND t12.location_type='DISTRICT' \n" +
            "where t1.name LIKE :searchText% AND t1.location_type='STATE'", nativeQuery = true)
    List<SearchStateResult> searchStateWithDist(String searchText);

    @Query(value = "select name, geo.code FROM geo inner join geo_assoc on geo.code=geo_assoc.code where geo_assoc.code_to=:distcode", nativeQuery = true)
    List<Result> getStatesByDistCode(Integer distcode);

    @Query(value = "SELECT t1.Name as cityname, t12.Name as distname, t1.code as towncode, t12.code as distcode from\n" +
            "geo t1 inner join geo_assoc t2 on t1.code = t2.code_to \n" +
            "inner join geo t12 on t2.code = t12.code \n" +
            "where t1.name LIKE :searchText% AND t1.location_type='CITY'", nativeQuery = true)
    List<SearchTownResult> searchCity(String searchText);


    @Query(value = "SELECT t1.name as distname, t12.name as townname, t1.code as distcode, t12.urban_status as urbanstatus from geo " +
            "t1 inner join geo_assoc t2 on t1.code = t2.code inner join geo t12 on t2.code_to = t12.code AND t2.relation_type='DIST_CITY'" +
            "where t1.name LIKE :searchText% AND t1.location_type='DISTRICT'", nativeQuery = true)
    List<SearchDistrict> searchDistWithTowns(String searchText);

    @Query(value = "SELECT * FROM geo WHERE code=:code AND location_type=:type", nativeQuery = true)
    Geo findByCode(int code, String type);

    @Query(value = "SELECT * FROM geo WHERE code=:code AND name=:name AND location_type=:type", nativeQuery = true)
    Geo findByCodeAndName(int code, String name, String type);

    @Query(value = "SELECT geo.name, geo.code FROM geo_assoc INNER JOIN geo on geo.id=geo_assoc.code_to AND location_type='DISTRICT' " +
            "WHERE geo_assoc.code=:code AND geo_assoc.relation_type='STATE_DIST'", nativeQuery = true)
    List<SearchStateDistricts> findDistsByStateCode(int code);

    @Query(value = "select geo.name, geo.id from geo_assoc inner join geo on geo.id=geo_assoc.code and location_type='DISTRICT'  where geo_assoc.code_to=:id and relation_type='DIST_CITY'", nativeQuery = true)
    SearchDistsByCityId findDistByCityId(int id);

    interface SearchDistsByCityId {
        String getName();
        Integer getId();
    }

    interface SearchStateDistricts {
        String getName();
        Integer getCode();
    }

    @Query(value = "select geo.name, geo.code from geo_assoc inner join geo on geo.id=geo_assoc.code and location_type='STATE' where code_to=:id and relation_type='STATE_DIST'", nativeQuery = true)
    Result findStateByDistId(Long id);



    @Query(value = "SELECT geo.name, geo.urban_status as urbanstatus FROM geo_assoc INNER JOIN geo on geo.id=geo_assoc.code_to AND location_type='CITY' " +
            "WHERE geo_assoc.code=:code AND geo_assoc.relation_type='DIST_CITY'", nativeQuery = true)
    List<SearchDistrict> findChildByParentId(int code);

    interface SearchDistrict {
        String getName();
        String getUrbanstatus();
    }

    interface SearchStateResult {
        String getStateName();
        String getdistname();
        Integer getdistcode();
    }

    interface SearchTownResult {
        String getCityname();
        String getDistname();
        Integer getdistcode();
        Integer gettowncode();
    }

    interface Result {
        String getName();
        Integer getCode();
    }

}
