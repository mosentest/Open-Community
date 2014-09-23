package org.mu.cloudservice.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("statRepository")
public interface StatRepository {

	public void insert(@Param("id") String id, @Param("date") Date date);
	
	public int increase(@Param("id") String id, @Param("date") Date date);
	
	public boolean exist(@Param("id") String id, @Param("date") Date date);
	
	public List<Long> getValues(@Param("id") String id, @Param("from") Date from, @Param("to") Date to);
	
	//test method
	public boolean set(@Param("id") String id, @Param("sum") long sum, @Param("date") Date date);
	
}
