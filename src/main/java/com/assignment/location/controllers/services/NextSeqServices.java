package com.assignment.location.controllers.services;

import com.assignment.location.models.NextSeq;

/**
 * @author anuragdhunna
 */
public interface NextSeqServices {

    NextSeq storeNextSeq(String tableName);

    Long getNextSeqId(String tableName);
}
