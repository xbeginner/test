package com.xx.test.Dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.xx.test.Model.Org;

public interface OrgDao extends PagingAndSortingRepository<Org, Long>{

	List<Org> findByParentOrgId(Long parentOrgId);
	
}
