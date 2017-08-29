package com.reta.model;

import java.sql.Date;
import java.util.List;

import com.reta.model.RetaDAO;
import com.reta.model.RetaDAO_Interface;
import com.reta.model.RetaVO;

public class RetaService {
	private RetaDAO_Interface dao;
	public RetaService() {
		dao = new RetaDAO();
	}
	
		public RetaVO addReta(Integer reta_id, Integer avtb_id, Integer user_id, Integer reta_number, Integer reta_status,
				Integer reta_grant, Date reta_date, Integer reta_rank_res, String reta_review, Date reta_reviewdate,
				Date rest_rpdate, String rest_rpcomm, Integer rest_rpstatus) {

			RetaVO retaVO = new RetaVO();
			retaVO.setReta_id(reta_id);
			retaVO.setAvtb_id(avtb_id);
			retaVO.setUser_id(user_id);
			retaVO.setReta_number(reta_number);
			retaVO.setReta_status(reta_status);
			retaVO.setReta_grant(reta_grant);
			retaVO.setReta_date(reta_date);
			retaVO.setReta_rank_res(reta_rank_res);
			retaVO.setReta_review(reta_review);
			retaVO.setReta_reviewdate(reta_reviewdate);
			retaVO.setRest_rpdate(rest_rpdate);
			retaVO.setRest_rpcomm(rest_rpcomm);
			retaVO.setRest_rpstatus(rest_rpstatus);
			dao.insert(retaVO);

			return retaVO;
		}

		public RetaVO updateReta(Integer reta_id, Integer avtb_id, Integer user_id, Integer reta_number, Integer reta_status,
				Integer reta_grant, Date reta_date, Integer reta_rank_res, String reta_review, Date reta_reviewdate,
				Date rest_rpdate, String rest_rpcomm, Integer rest_rpstatus) {

			RetaVO retaVO = new RetaVO();

			retaVO.setReta_id(reta_id);
			retaVO.setAvtb_id(avtb_id);
			retaVO.setUser_id(user_id);
			retaVO.setReta_number(reta_number);
			retaVO.setReta_status(reta_status);
			retaVO.setReta_grant(reta_grant);
			retaVO.setReta_date(reta_date);
			retaVO.setReta_rank_res(reta_rank_res);
			retaVO.setReta_review(reta_review);
			retaVO.setReta_reviewdate(reta_reviewdate);
			retaVO.setRest_rpdate(rest_rpdate); 
			retaVO.setRest_rpcomm(rest_rpcomm);
			retaVO.setRest_rpstatus(rest_rpstatus);
			dao.update(retaVO);

			return retaVO;
		}

		public void deleteReta(Integer reta_id) {
			dao.delete(reta_id);
		}

		public RetaVO getOneReta(Integer reta_id) {
			return dao.findByPrimaryKey(reta_id);
		}

		public RetaVO getOneRetaByUserID(Integer user_id) {

			return dao.findRetaByUserId(user_id);
		}
		
		public List<RetaVO> getAll() {
			return dao.getAll();
		}
		
		public List<RetaVO> findByUserId(Integer user_id) {
			return dao.findByUserId(user_id);
		}	
		
		public List<RetaVO> getAllRetaByRestID(Integer rest_id){
			return dao.findByUserId(rest_id);
		}
}
