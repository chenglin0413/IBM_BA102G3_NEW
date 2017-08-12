package com.trpi.model;

import java.util.List;

import com.tlcm.model.TlcmVO;
import com.trpi.model.TrpiVO;

public interface TrpiDAO_interface {
	
	public void insert(TrpiVO trpiVO);
    public void update(TrpiVO trpiVO);
    public void delete(Integer trpi_id);
    
    public TrpiVO findByPrimaryKey(Integer trpi_id); 
    //找出一篇遊記裏全部的照片
    public List<TrpiVO> findByForeignKey(Integer trvl_id); 
   
    public List<TrpiVO> getAll();
}
