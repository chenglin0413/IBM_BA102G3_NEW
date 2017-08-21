package com.mfee.model;

import java.sql.Date;
import java.util.List;

public class MfeeService {

	private MfeeDAO_interface dao;

	public MfeeService() {
		dao = new MfeeDAO();
	}

	public MfeeVO addMfee(Integer user_id, Date mfee_date, Date pay_date) {

		MfeeVO mfeeVO = new MfeeVO();
		mfeeVO.setUser_id(user_id);
		mfeeVO.setMfee_date(mfee_date);
		mfeeVO.setPay_date(pay_date);
		dao.insert(mfeeVO);
		
		return mfeeVO;
	}

	public MfeeVO updateMfee(Integer mfee_id, Integer user_id, Date mfee_date, Date pay_date) {

		MfeeVO mfeeVO = new MfeeVO();

		mfeeVO.setMfee_id(mfee_id);
		mfeeVO.setUser_id(user_id);
		mfeeVO.setMfee_date(mfee_date);
		mfeeVO.setPay_date(pay_date);
			
		dao.update(mfeeVO);

		return mfeeVO;
	}

	public void deleteMfee(Integer mfee_id) {
		dao.delete(mfee_id);
	}

	public MfeeVO getOneMfee(Integer mfee_id) {
		return dao.findByPrimaryKey(mfee_id);
	}
	
	public List<MfeeVO> getAll() {
		return dao.getAll();
	}
	
	public List<MfeeVO> findUnpay(String year, String month) {
		System.out.println("year:"+year+" ,month:"+month);
		return dao.findUnpay(year, month);
	}
	
//	public List<MfeeVO> findUnpayCurrent() {
////		System.out.println("year:"+year+" ,month:"+month);
////		return dao.findUnpay(year, month);
//	}
	
	public List<MfeeVO> getOneByUser_id(Integer user_id) {
		return dao.findByUserId(user_id);
	}
	
}
