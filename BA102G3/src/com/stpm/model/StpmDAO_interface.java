package com.stpm.model;

import java.util.*;

public interface StpmDAO_interface {
	
	      public void insert(StpmVO stpmVO);
          public void update(StpmVO stpmVO);
          //public void delete(Integer stpm_id);
          public StpmVO findByPrimaryKey(Integer stpm_id);
          public List<StpmVO> findByStoreID(Integer store_id);
	      public List<StpmVO> getAll();
	      //查詢某部門的員工(一對多)(回傳 Set)
	//public Set<EmpVO> getEmpsByDeptno(Integer deptno);

}
