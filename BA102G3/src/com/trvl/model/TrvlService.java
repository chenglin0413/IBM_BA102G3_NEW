package com.trvl.model;

import java.util.List;
import java.util.Map;

import com.tlcm.model.TlcmVO;
import com.trpi.model.TrpiVO;

public class TrvlService {

	private TrvlDAO_interface dao;

	public TrvlService() {
		dao = new TrvlDAO();
	}

	public String addTrvl(Integer user_id, java.sql.Date trvl_date, String trvl_tittle,String trvl_loc, String trvl_content) {

		TrvlVO trvlVO = new TrvlVO();
		trvlVO.setUser_id(user_id);
		trvlVO.setTrvl_date(trvl_date);
		trvlVO.setTrvl_tittle(trvl_tittle);
		trvlVO.setTrvl_loc(trvl_loc);
		trvlVO.setTrvl_content(trvl_content);
		String trvl_id	= dao.insert(trvlVO);

		return trvl_id;
	}

	public TrvlVO updateTrvl(Integer trvl_id, Integer user_id, java.sql.Date trvl_date, String trvl_tittle,
			String trvl_loc, String trvl_content) {

		TrvlVO trvlVO = new TrvlVO();
		trvlVO.setTrvl_id(trvl_id);
		trvlVO.setUser_id(user_id);
		trvlVO.setTrvl_date(trvl_date);
		trvlVO.setTrvl_tittle(trvl_tittle);
		trvlVO.setTrvl_loc(trvl_loc);
		trvlVO.setTrvl_content(trvl_content);

		dao.update(trvlVO);

		return trvlVO;
	}

	public void deleteTrvl(Integer trvl_id) {
		dao.delete(trvl_id);
	}

	public TrvlVO getOneTrvl(Integer trvl_id) {
		return dao.findByPrimaryKey(trvl_id);
	}
	
	//用會員ID查共寫了幾篇遊記
	public List<TrvlVO> getTrvlByUserId(Integer user_id) {
		return dao.findByForeignKey(user_id);
	}
	
	public List<TrvlVO> getAll() {
		return dao.getAll();
	}
	
	//找出單篇遊記所有留言
	public List<TlcmVO> getTlcmsByTrvlId(Integer trvl_id) {
		return dao.findTlcmsByTrvlId(trvl_id);
	}
	//找出單篇遊記所有照片
	public List<TrpiVO> getTrpisByTrvlId(Integer trvl_id) {
		return dao.findTrpisByTrvlId(trvl_id);
	}
	
	//瀏覽次數+1
	public void addTrvlCount(Integer trvl_id) {
		dao.addPageView(trvl_id);
	}
	
	//找出點擊數前三的trvl
	public List<TrvlVO> getTopTrvlCounts() {
		 return dao.getTopOfBlogs();
	}
	
	//萬用查詢
	public List<TrvlVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
//	public void addTrvlWithTrpis (TrvlVO trvlVO , List<TrpiVO> trpiList) {
//
//		TrvlVO trvlVO = new TrvlVO();
//		trvlVO.setUser_id(user_id);
//		trvlVO.setTrvl_date(trvl_date);
//		trvlVO.setTrvl_tittle(trvl_tittle);
//		trvlVO.setTrvl_loc(trvl_loc);
//		trvlVO.setTrvl_content(trvl_content);
//		dao.insertWithTrpis(trvlVO, list);
//
//		
//	}

}
