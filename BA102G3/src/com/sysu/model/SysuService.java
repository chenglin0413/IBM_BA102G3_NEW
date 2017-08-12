package com.sysu.model;

import java.sql.Date;
import java.util.List;

public class SysuService {

	private SysuDAO_Interface dao;

	public SysuService() {
		dao = new SysuDAO();
	}

	public SysuVO addSysu(String sysu_account, String sysu_passwd, Integer sysu_type, String sysu_lastname,
			String sysu_firstname, String sysu_email,
			Date sysu_joindate, Integer sysu_status, String[] sysu_funcs) {

		SysuVO sysuVO = new SysuVO();
		sysuVO.setSysu_account(sysu_account);
		sysuVO.setSysu_passwd(sysu_passwd);
		sysuVO.setSysu_type(sysu_type);		
		sysuVO.setSysu_lastname(sysu_lastname);
		sysuVO.setSysu_firstname(sysu_firstname);
		sysuVO.setSysu_email(sysu_email);
		sysuVO.setSysu_joindate(sysu_joindate);
		sysuVO.setSysu_status(sysu_status);
		dao.insert(sysuVO, sysu_funcs);
		
		return sysuVO;
	}

	public SysuVO updateSysu(Integer sysu_id, String sysu_account, String sysu_passwd, Integer sysu_type, String sysu_lastname,
			String sysu_firstname, String sysu_email,
			Date sysu_joindate, Integer sysu_status) {

		SysuVO sysuVO = new SysuVO();

		sysuVO.setSysu_id(sysu_id);
		sysuVO.setSysu_account(sysu_account);
		sysuVO.setSysu_passwd(sysu_passwd);
		sysuVO.setSysu_type(sysu_type);
		sysuVO.setSysu_lastname(sysu_lastname);
		sysuVO.setSysu_firstname(sysu_firstname);
		sysuVO.setSysu_email(sysu_email);
		sysuVO.setSysu_joindate(sysu_joindate);
		sysuVO.setSysu_status(sysu_status);
			
		dao.update(sysuVO);

		return sysuVO;
	}

	public void deleteSysu(Integer sysu_id) {
		dao.delete(sysu_id);
	}

	public SysuVO getOneSysu(Integer sysu_id) {
		return dao.findByPrimaryKey(sysu_id);
	}
	
	public SysuVO getOneAccountSysu(String sysu_account) {
		return dao.findByAccount(sysu_account);
	}

	public List<SysuVO> getAll() {
		return dao.getAll();
	}
}
