package com.trvlrank.model;

import java.util.List;

public interface TrvlRankDAO_interface {
	
	public void insert(TrvlRankVO trvlrankVO);       
    public void update(TrvlRankVO trvlrankVO);
//    public void delete(Integer trvl_id);

    //回傳分數
    public Integer findByPrimaryKey(Integer trvl_id, Integer user_id);
    public List<TrvlRankVO> getAll();   
  
}
