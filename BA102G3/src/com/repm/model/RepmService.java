package com.repm.model;

import java.util.List;

public class RepmService {

	private RepmDAO_interface dao;

	public RepmService() {
		dao = new RepmDAO();
	}

	public RepmVO addRepm(Integer rest_id, String repm_name, String repm_desc, String repm_content,
			java.sql.Date repm_startdate, java.sql.Date repm_enddate, Integer repm_status) {

		RepmVO repmVO = new RepmVO();

		repmVO.setRest_id(rest_id);
		repmVO.setRepm_name(repm_name);
		repmVO.setRepm_desc(repm_desc);
		repmVO.setRepm_content(repm_content);
		repmVO.setRepm_startdate(repm_startdate);
		repmVO.setRepm_enddate(repm_enddate);
		repmVO.setRepm_status(repm_status);
		dao.insert(repmVO);

		return repmVO;
	}

	// �w�d�� Struts 2 �Ϊ�
	public void addRepm(RepmVO repmVO) {
		dao.insert(repmVO);
	}

	public RepmVO updateRepm(String repm_name, String repm_desc, String repm_content, java.sql.Date repm_startdate,
			java.sql.Date repm_enddate, Integer repm_status, Integer repm_id) {

		RepmVO repmVO = new RepmVO();

		repmVO.setRepm_name(repm_name);
		repmVO.setRepm_desc(repm_desc);
		repmVO.setRepm_content(repm_content);
		repmVO.setRepm_startdate(repm_startdate);
		repmVO.setRepm_enddate(repm_enddate);
		repmVO.setRepm_status(repm_status);
		repmVO.setRepm_id(repm_id);
		dao.update(repmVO);

		return dao.findByPrimaryKey(repm_id);
	}

	public void updateRepm(RepmVO repmVO) {
		dao.update(repmVO);
	}

	public RepmVO getOneRepm(Integer repm_id) {
		return dao.findByPrimaryKey(repm_id);
	}

	public List<RepmVO> getAll() {
		return dao.getAll();
	}

	public List<RepmVO> findByRestID(Integer rest_id) {
		return dao.findByRestID(rest_id);
	}
}
