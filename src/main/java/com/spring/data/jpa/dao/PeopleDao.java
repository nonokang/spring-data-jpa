package com.spring.data.jpa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.data.jpa.entity.People;

@Repository("peopleDao")
public interface PeopleDao extends JpaRepository<People, Integer>{

	public Page<People> findAll(Specification<People> page, Pageable pageable);
}
