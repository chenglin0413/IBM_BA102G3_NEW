package com.tlcm.model;

import java.util.List;

public interface TlcmDAO_interface {
	public void insert(TlcmVO tlcmVO);
	public void update(TlcmVO tlcmVO);
	public void delete(Integer tlcm_id);
	
	public TlcmVO findByPrimaryKey(Integer tlcm_id);
	//找出一篇遊記有幾則留言
	public List<TlcmVO> findByForeignKey(Integer trvl_id);
	
	public List<TlcmVO> getAll();
	
	
}
