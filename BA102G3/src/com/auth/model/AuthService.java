package com.auth.model;

import java.util.List;

public class AuthService {

	private AuthDAO_Interface dao;

	public AuthService() {
		dao = new AuthDAO();
	}

	public AuthVO addAuth(Integer sysu_id, Integer func_id) {

		AuthVO authVO = new AuthVO();
						
		authVO.setSysu_id(sysu_id);
		authVO.setFunc_id(func_id);

		dao.insert(authVO);

		return authVO;
	}

	public AuthVO updateAuth(Integer sysu_id, Integer func_id) {

		AuthVO authVO = new AuthVO();

		authVO.setSysu_id(sysu_id);
		authVO.setFunc_id(func_id);
			
		dao.update(authVO);

		return authVO;
	}
	
	public AuthVO updateAuthById(Integer sysu_id, Integer func_id) {

		AuthVO authVO = new AuthVO();

		authVO.setSysu_id(sysu_id);
		authVO.setFunc_id(func_id);
			
		dao.updateAuthById(sysu_id,func_id);

		return authVO;
	}

	public void deleteAuth(Integer sysu_id, Integer func_id) {
		dao.delete(sysu_id, func_id);
	}
	
	public void deleteId(Integer sysu_id) {
		dao.deleteId(sysu_id);
	}


	public List<AuthVO> getOneAuth(Integer sysu_id) {
		return dao.findByPrimaryKey(sysu_id);
	}

	public List<AuthVO> getAll() {
		return dao.getAll();
	}
}
