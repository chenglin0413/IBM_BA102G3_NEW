package com.prpm.model;

import java.util.*;

public interface PrpmDAO_interface {
	
	      public void insert(PrpmVO prpmVO);
          public void update(PrpmVO prpmVO);
          public PrpmVO findByStpmID_ProdID(Integer stpm_id, Integer prod_id);
          
          public List<PrpmVO> findByStpmID(Integer stpm_id);
          public PrpmVO findByPrice(Integer stpm_id);
	      public List<PrpmVO> getAll();
	      public PrpmVO getOneRmPrice_prod(Integer prod_id);
	      
	      
	      public PrpmVO findByPrimaryKey(Integer stpm_id);
          public void updateStatus(PrpmVO prpmVO); //for status chang
          public void delete(Integer stpm_id,Integer prod_id);
	      //查詢某部門的員工(一對多)(回傳 Set)
	//public Set<EmpVO> getEmpsByDeptno(Integer deptno);

}
