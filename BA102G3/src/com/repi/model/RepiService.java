package com.repi.model;

import java.util.List;

import com.repi.model.RepiVO;

public class RepiService {
	private RepiDAO_Interface dao;
	public RepiService(){
		dao = new RepiDAO();
	}

	public RepiVO addrepi(Integer rest_id,String repi_name,byte[] repi_img,
			String repi_imgfmt) {
		
		RepiVO repiVO = new RepiVO();
		
		repiVO.setRest_id(rest_id);
		repiVO.setRepi_name(repi_name);
		repiVO.setRepi_img(repi_img);
		repiVO.setRepi_imgfmt(repi_imgfmt);
		
		dao.insert(repiVO);
		
		return repiVO;
	}
	
	public RepiVO updaterepi(Integer rest_id,String repi_name,byte[] repi_img,
			String repi_imgfmt,Integer repi_id ) {
		
		RepiVO repiVO = new RepiVO();
		
		repiVO.setRest_id(rest_id);
		repiVO.setRepi_name(repi_name);
		repiVO.setRepi_img(repi_img);
		repiVO.setRepi_imgfmt(repi_imgfmt);
		repiVO.setRepi_id(repi_id);
		
		dao.update(repiVO);
		
		return repiVO;
	}
	
	public void deleterepi(Integer repi_id) {
		dao.delete(repi_id);
	}
	
	public RepiVO getOnerepi(Integer repi_id) {
		return dao.findByPrimaryKey(repi_id);
	}

	public RepiVO getOneRepiByRestId(Integer rest_id) {
		return dao.findByRestId(rest_id);
	}

	public List<RepiVO> getAll(){
		return dao.getAll();
	}

}
