package org.mu.community.code.repository;

import org.mu.jmdb.spring.repository.RepositoryTemplate;
import org.mu.community.code.entity.NCSALog;
import org.springframework.stereotype.Repository;

@Repository("ncsaLogRepository")
public class NCSALogRepository extends RepositoryTemplate<NCSALog, String> {
	
}
