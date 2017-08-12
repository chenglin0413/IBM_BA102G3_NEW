package com.tlcm.model;

import java.util.List;

public class TlcmService {

	private TlcmDAO_interface dao;

	public TlcmService() {
		dao = new TlcmDAO();
	}

	public TlcmVO addTlcm(Integer trvl_id, Integer user_id, java.sql.Date tlcm_date, String tlcm_content) {

		TlcmVO tlcmVO = new TlcmVO();
		
		tlcmVO.setTrvl_id(trvl_id);
		tlcmVO.setUser_id(user_id);
		tlcmVO.setTlcm_date(tlcm_date);
		tlcmVO.setTlcm_content(tlcm_content);
		dao.insert(tlcmVO);

		return tlcmVO;
	}
	
	public TlcmVO updateTlcm(Integer tlcm_id,Integer trvl_id,Integer user_id, java.sql.Date tlcm_date, String tlcm_content){
		
		TlcmVO tlcmVO = new TlcmVO();
		tlcmVO.setTlcm_id(tlcm_id);
		tlcmVO.setTrvl_id(trvl_id);
		tlcmVO.setUser_id(user_id);
		tlcmVO.setTlcm_date(tlcm_date);
		tlcmVO.setTlcm_content(tlcm_content);
		
		dao.update(tlcmVO);
		
		return tlcmVO;
	}
	
	public void deleteTlcm(Integer tlcm_id) {
		dao.delete(tlcm_id);
	}
	
	public TlcmVO getOneTlcm(Integer tlcm_id) {
		return dao.findByPrimaryKey(tlcm_id);
	}
	
	public List<TlcmVO> getAllTlcm_For_OneTrvl(Integer trvl_id) {
		return dao.findByForeignKey(trvl_id);
	}
	
	public List<TlcmVO> getAll(){
		return dao.getAll();
	}
}
