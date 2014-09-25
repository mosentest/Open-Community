package org.mu.community.code.service;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mu.community.code.repository.JavaLibraryRepository;
import org.mu.community.code.repository.StatRepository;
import org.mu.community.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;

@Service("statService")
public class StatService {

	public static final String WEEKLY = "week";
	
	public static final String MONTHLY = "month";
	
	public static final String ANNUALLY = "year";
	
	@Autowired
	private StatRepository statRepository;

	@Autowired
	private JavaLibraryRepository javaLibraryRepository;
	
	@Transactional
	public void increase(String id) {
		Date date = new Date();
		if (statRepository.exist(id, date)) {
			statRepository.increase(id, date);
		} else {
			statRepository.insert(id, date);
		}
		javaLibraryRepository.updateOne(new ObjectId(id), new BasicDBObject("$inc", new BasicDBObject("nDownload", 1)));
	}

	public List<Long> getDownloadValue(String id, String type) {
		List<Long> value = null;
		Date to = DateUtil.clearTimeValue(new Date());
		Date from = null;
		switch (type) {
			case WEEKLY:
				from = DateUtil.backOneWeek(to);
				break;
			case MONTHLY:
				from = DateUtil.backOneMonth(to);
				break;
			case ANNUALLY:
				from = DateUtil.backOneYear(to);
				break;
		}
		value = statRepository.getValues(id, from, to);
		return value;
	}
	
	public void setStatRepository(StatRepository statRepository) {
		this.statRepository = statRepository;
	}

	public void setJavaLibraryRepository(JavaLibraryRepository javaLibraryRepository) {
		this.javaLibraryRepository = javaLibraryRepository;
	}
	
}
