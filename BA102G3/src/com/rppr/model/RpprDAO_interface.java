package com.rppr.model;

import java.util.List;


public interface RpprDAO_interface {
	
	public void insert(RpprVO rpprVO);
    public void update(RpprVO rpprVO);
    public void delete(Integer rppr_id);
    public RpprVO findByPrimaryKey(Integer rppr_id);
    public List<RpprVO> getAll();
    //取得所有檢舉產品
    public List<RpprVO> getAllByStatus(Integer rppr_status);
    //切換檢舉處理狀態
	void updateStatus(Integer rppr_id,Integer rppr_status);
    
	
}
