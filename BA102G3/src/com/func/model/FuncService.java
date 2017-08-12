package com.func.model;

import java.util.List;

public class FuncService {

	private FuncDAO_Interface dao;

	public FuncService() {
		dao = new FuncDAO();
	}

	public FuncVO addFunc(String func_name, String func_path, Integer func_status) {

		FuncVO funcVO = new FuncVO();

		funcVO.setFunc_name(func_name);
		funcVO.setFunc_path(func_path);
		funcVO.setFunc_status(func_status);
		dao.insert(funcVO);

		return funcVO;
	}

	public FuncVO updateFunc(Integer func_id, String func_name, String func_path, Integer func_status) {

		FuncVO funcVO = new FuncVO();

		funcVO.setFunc_id(func_id);
		funcVO.setFunc_name(func_name);
		funcVO.setFunc_path(func_path);
		funcVO.setFunc_status(func_status);
			
		dao.update(funcVO);

		return funcVO;
	}

	public void deleteFunc(Integer func_id) {
		dao.delete(func_id);
	}

	public FuncVO getOneFunc(Integer func_id) {
		return dao.findByPrimaryKey(func_id);
	}
	
	public List<FuncVO> getAll() {
		return dao.getAll();
	}
}
