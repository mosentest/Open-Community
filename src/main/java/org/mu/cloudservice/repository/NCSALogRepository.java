package org.mu.cloudservice.repository;

import org.mu.cloudservice.entity.NCSALog;
import org.springframework.stereotype.Repository;

@Repository("ncsaLogRepository")
public interface NCSALogRepository {

    public void save(NCSALog log);

}
