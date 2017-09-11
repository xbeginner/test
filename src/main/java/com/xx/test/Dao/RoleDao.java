package com.xx.test.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.xx.test.Model.Org;
import com.xx.test.Model.Role;

public interface RoleDao extends CrudRepository<Role, Long>{
    
	 
}
