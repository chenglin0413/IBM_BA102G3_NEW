package com.ord.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.item.model.ItemVO;

public class OrdService {

	private OrdDAO_interface dao;

	public OrdService() {
		dao = new OrdDAO();
	}
	

	public OrdVO addOrd(Integer user_id,Integer store_id,Date ord_date,Integer ord_total,Integer ord_bill,Integer ord_grant,Integer ord_status,Integer ord_sscore,
			Date ord_rpdate,String ord_rpcomm,Integer ord_rpstatus) {

		OrdVO ordVO = new OrdVO();

		ordVO.setUser_id(user_id);
		ordVO.setStore_id(store_id);
		ordVO.setOrd_date(ord_date);
		ordVO.setOrd_total(ord_total);
		ordVO.setOrd_bill(ord_bill);
		ordVO.setOrd_grant(ord_grant);
		ordVO.setOrd_status(ord_status);
		ordVO.setOrd_sscore(ord_sscore);
		ordVO.setOrd_rpdate(ord_rpdate);
		ordVO.setOrd_rpcomm(ord_rpcomm);
		ordVO.setOrd_rpstatus(ord_rpstatus);
		
		
		dao.insert(ordVO);

		return ordVO;
	}

	public OrdVO updateOrd(
			 Integer ord_id 
			,Integer user_id
			,Integer store_id
			,Date ord_date
			,Integer ord_total
			,Integer ord_bill
			,Integer ord_grant
			,Integer ord_status
			,Integer ord_sscore
			,Date ord_rpdate
			,String ord_rpcomm
            ,Integer ord_rpstatus) {

		OrdVO ordVO = new OrdVO();

		ordVO.setOrd_id(ord_id);
		ordVO.setUser_id(user_id);
		ordVO.setStore_id(store_id);
		ordVO.setOrd_date(ord_date);
		ordVO.setOrd_total(ord_total);
		ordVO.setOrd_bill(ord_bill);
		ordVO.setOrd_grant(ord_grant);
		ordVO.setOrd_status(ord_status);
		ordVO.setOrd_sscore(ord_sscore);
		ordVO.setOrd_rpdate(ord_rpdate);
		ordVO.setOrd_rpcomm(ord_rpcomm);
		ordVO.setOrd_rpstatus(ord_rpstatus);
		dao.update(ordVO);

		return ordVO;
	}

	public void deleteOrd(Integer ord_id) {
		dao.delete(ord_id);
	}

	public OrdVO getOneOrd(Integer ord_id) {
		return dao.findByPrimaryKey(ord_id);
	}

	public List<OrdVO> getAll() {
		return dao.getAll();
	}
	public OrdVO getOneOrd_idANDOneStore_id() {
		return dao.select_One_ord_id();
	}
	public List<OrdVO> getOneStore_idAllOrd(Integer store_id){
		return dao.getOneStore_idAllOrd(store_id);
	}
	public List<OrdVO> getOneUser_idAllOrd(Integer user_id){
		return dao.getOneUser_idAllOrd(user_id);
	}
	public void update_sscore(Integer ord_sscore,Integer ord_id){
		 dao.update_sscore(ord_sscore, ord_id);
	}
	public void update_bill(Integer ord_bill,Integer ord_id){
		 dao.update_bill(ord_bill, ord_id);
	}
	public Integer insertWithItems(OrdVO ordVO , List<ItemVO> list) {
		return dao.insertWithItems(ordVO, list);
	}
	
	public List<OrdVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}
