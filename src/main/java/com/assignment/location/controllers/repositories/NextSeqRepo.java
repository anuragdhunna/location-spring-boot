package com.assignment.location.controllers.repositories;

import com.assignment.location.models.NextSeq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author anuragdhunna
 */
@Repository
public interface NextSeqRepo extends JpaRepository<NextSeq, Long> {

    @Query(value = "SELECT next_seq_id FROM next_seq WHERE table_name=:tableName", nativeQuery = true)
    Long findNextSeqIdByTable(String tableName);

    @Query(value = "SELECT * FROM next_seq WHERE table_name=:tableName", nativeQuery = true)
    NextSeq findNextSeqByTable(String tableName);
}
