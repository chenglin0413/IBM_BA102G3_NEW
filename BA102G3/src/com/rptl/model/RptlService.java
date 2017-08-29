package com.rptl.model;

import java.util.List;

import com.rppr.model.RpprVO;

public class RptlService {

	private RptlDAO_interface dao;

	public RptlService() {
		dao = new RptlDAO();
	}

	public RptlVO addRptl(Integer trvl_id, Integer user_id, java.sql.Timestamp rptl_date,
			String rptl_tittle, String rptl_content) {
		
		RptlVO rptlVO = new RptlVO();
		rptlVO.setTrvl_id(trvl_id);
		rptlVO.setUser_id(user_id);
		rptlVO.setRptl_date(rptl_date);
		rptlVO.setRptl_tittle(rptl_tittle);
		rptlVO.setRptl_content(rptl_content);
		
		dao.insert(rptlVO);
		return rptlVO;
	}
	
	public RptlVO updateRptl(Integer rptl_id,Integer trvl_id, Integer user_id, java.sql.Timestamp rptl_date, Integer rptl_status,
			String rptl_tittle, String rptl_content) {
		
		RptlVO rptlVO = new RptlVO();
		rptlVO.setRptl_id(rptl_id);
		rptlVO.setTrvl_id(trvl_id);
		rptlVO.setUser_id(user_id);
		rptlVO.setRptl_date(rptl_date);
		rptlVO.setRptl_status(rptl_status);
		rptlVO.setRptl_tittle(rptl_tittle);
		rptlVO.setRptl_content(rptl_content);
		dao.update(rptlVO);
		
		return rptlVO;
		
	}
	
	public void deleteRptl(Integer rptl_id) {
		dao.delete(rptl_id);
	}
	
	public RptlVO getOneRptl(Integer rptl_id) {
		return dao.findByPrimaryKey(rptl_id);
	}
	
	public List<RptlVO> getAll() {
		return dao.getAll();
	}
	
	public List<RptlVO> getAllByStatus(Integer rptl_status) {
		return dao.getAllByStatus(rptl_status);
	}
	
	public void updateRpprStatus(Integer rptl_id,Integer rptl_status) {
		
		RptlVO rptlVO = new RptlVO();
		rptlVO.setRptl_id(rptl_id);
		rptlVO.setRptl_status(rptl_status);

		dao.updateStatus(rptl_id, rptl_status);
	}
	
}
