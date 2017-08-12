package com.rptl.model;

import java.util.List;

public interface RptlDAO_interface {
	public void insert(RptlVO rptlVO);
    public void update(RptlVO rptlVO);
    public void delete(Integer rptl_id);
    public RptlVO findByPrimaryKey(Integer rptl_id);
    public List<RptlVO> getAll();
    
    //修改檢舉狀態
	public void updateStatus(Integer rptl_id, Integer rptl_status);
}
