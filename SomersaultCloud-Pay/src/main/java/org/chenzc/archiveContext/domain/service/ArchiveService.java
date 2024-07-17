package org.chenzc.archiveContext.domain.service;

import jakarta.annotation.Resource;
import org.chenzc.archiveContext.domain.entity.Archive;
import org.chenzc.archiveContext.domain.infrastructure.repository.ArchiveMapper;
import org.springframework.stereotype.Service;

@Service
public class ArchiveService {

    @Resource
    private ArchiveMapper archiveMapper;

//    TODO redis push rabbit mq
    public Boolean ArchivePayingLog(Archive archive){

//        TODO exception handle
        try {
            archiveMapper.insert(archive);
        }catch (Exception e){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
