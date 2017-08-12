package com.rppr.model;

import java.sql.Date;
import java.util.List;

public class RpprService {
		
	
	private RpprDAO_interface dao;

	public RpprService() {
		dao = new RpprDAO();
	}

	//檢舉狀態已預設'0',未處理
	public RpprVO addRppr(Integer prod_id, Integer user_id,  java.sql.Timestamp rppr_date,String rppr_tittle, String rppr_content) {
		
		RpprVO rpprVO = new RpprVO();
		rpprVO.setProd_id(prod_id);
		rpprVO.setUser_id(user_id);
		rpprVO.setRppr_date(rppr_date);
		rpprVO.setRppr_tittle(rppr_tittle);
		rpprVO.setRppr_content(rppr_content);
		
		dao.insert(rpprVO);
		return rpprVO;
	}
	
	public RpprVO updateRppr(Integer rppr_id,Integer prod_id, Integer user_id, java.sql.Timestamp rppr_date, Integer rppr_status,
			String rppr_tittle, String rppr_content) {
		
		RpprVO rpprVO = new RpprVO();
		rpprVO.setRppr_id(rppr_id);
		rpprVO.setProd_id(prod_id);;
		rpprVO.setUser_id(user_id);
		rpprVO.setRppr_date(rppr_date);
		rpprVO.setRppr_status(rppr_status);
		rpprVO.setRppr_tittle(rppr_tittle);
		rpprVO.setRppr_content(rppr_content);
		dao.update(rpprVO);
		
		return rpprVO;
		
	}
	
	public void deleteRppr(Integer rppr_id) {
		dao.delete(rppr_id);
	}
	
	public RpprVO getOneRppr(Integer rppr_id) {
		return dao.findByPrimaryKey(rppr_id);
	}
	
	public List<RpprVO> getAll() {
		return dao.getAll();
	}
	
	public void updateRpprStatus(Integer rppr_id,Integer rppr_status) {
		
		RpprVO rpprVO = new RpprVO();
		rpprVO.setRppr_id(rppr_id);
		rpprVO.setRppr_status(rppr_status);

		dao.updateStatus(rppr_id, rppr_status);
	}
	
}
