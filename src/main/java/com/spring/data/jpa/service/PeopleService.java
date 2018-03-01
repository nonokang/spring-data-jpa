package com.spring.data.jpa.service;

import com.spring.data.jpa.entity.People;
import com.spring.data.jpa.utils.Page;

public interface PeopleService {

	public People findById(Integer id);
	
	public Page<People> pageList(Integer pageNum, Integer pageSize, String searchName);
	
	public void save(People bean);
	
	public void update(People bean);
	
	public void opera(String ids, String type);
	
}
