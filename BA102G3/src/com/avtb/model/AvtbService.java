package com.avtb.model;

import java.sql.Date;
import java.util.List;

import com.avtb.model.AvtbDAO;
import com.avtb.model.AvtbDAO_Interface;
import com.avtb.model.AvtbVO;

public class AvtbService {
	private AvtbDAO_Interface dao;
	public AvtbService() {
		dao = new AvtbDAO();
	}

	public AvtbVO addAvtb(Integer avtb_id, Integer rest_id, Date avtb_date_s, Date avtb_date_e, Integer avtb_reservation,
			Integer avtb_max_reservation) {
		AvtbVO avtbVO = new AvtbVO();
		
		avtbVO.setAvtb_id(avtb_id);
		avtbVO.setRest_id(rest_id);
		avtbVO.setAvtb_date_s(avtb_date_s);
		avtbVO.setAvtb_date_e(avtb_date_e);
		avtbVO.setAvtb_reservation(avtb_reservation);
		avtbVO.setAvtb_max_reservation(avtb_max_reservation);
		dao.insert(avtbVO);
		
		return avtbVO;
	}
	
	public AvtbVO updateAvtb(Integer avtb_id, Integer rest_id, Date avtb_date_s, Date avtb_date_e, Integer avtb_reservation,
			Integer avtb_max_reservation ) {
		AvtbVO avtbVO = new AvtbVO();
		
		avtbVO.setAvtb_id(avtb_id);
		avtbVO.setRest_id(rest_id);
		avtbVO.setAvtb_date_s(avtb_date_s);
		avtbVO.setAvtb_date_e(avtb_date_e);
		avtbVO.setAvtb_reservation(avtb_reservation);
		avtbVO.setAvtb_max_reservation(avtb_max_reservation);
		dao.update(avtbVO);
		
		return avtbVO;
	}
	
	public void deleteAvtb(Integer avtb_id) {
		dao.delete(avtb_id);
	}
	
	public AvtbVO getOneAvtb(Integer avtb_id) {
		return dao.findByPrimaryKey(avtb_id);
	}
	
	public List<AvtbVO> getAll(){
		return dao.getAll();
	}
}
