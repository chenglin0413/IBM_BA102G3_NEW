package com.flsc.model;


import java.util.*;


public interface FlscDAO_interface {
		  public void insert(FlscVO flscVO);
          public void deleteTable(FlscVO flscVO);
          public List<FlscVO> findByFlno(String flsc_flno);
          public List<FlscVO> findByflsc_airlinecode(String flsc_airlinecode);
          public FlscVO flscSubQuery(String flsc_airlinecode,String flsc_flno,String flsc_sdate);
          public List<FlscVO> getAll();
          public List<FlscVO> findByArrive();
          public List<FlscVO> findByOut();
          public FlscVO findByPK(Integer flsc_id);
}
