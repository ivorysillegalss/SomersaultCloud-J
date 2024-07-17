package org.chenzc.archiveContext.domain.infrastructure.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.chenzc.archiveContext.domain.entity.Archive;

@Mapper
public interface ArchiveMapper extends BaseMapper<Archive> {
}
