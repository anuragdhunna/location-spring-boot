package com.assignment.location.controllers.services;

import com.assignment.location.controllers.repositories.NextSeqRepo;
import com.assignment.location.models.NextSeq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author anuragdhunna
 */
@Service
public class NextSeqServicesImpl implements NextSeqServices {

    @Autowired
    NextSeqRepo nextSeqRepo;

    @Override
    public NextSeq storeNextSeq(String tableName) {
        NextSeq nextSeqByTable = nextSeqRepo.findNextSeqByTable(tableName);

        if (nextSeqByTable == null) {
            nextSeqByTable = new NextSeq();
            nextSeqByTable.setNextSeqId(1L);
            nextSeqByTable.setTableName(tableName);
            return nextSeqRepo.save(nextSeqByTable);
        }
        Long nextSeqId = nextSeqByTable.getNextSeqId();
        nextSeqId = nextSeqId +1L;
        nextSeqByTable.setNextSeqId(nextSeqId);
        return nextSeqRepo.save(nextSeqByTable);
    }

    @Override
    public Long getNextSeqId(String tableName) {
        return nextSeqRepo.findNextSeqIdByTable(tableName) == null? 1: nextSeqRepo.findNextSeqIdByTable(tableName);
    }
}
