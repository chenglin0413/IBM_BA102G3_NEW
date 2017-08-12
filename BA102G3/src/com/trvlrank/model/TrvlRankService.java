package com.trvlrank.model;

import java.util.List;

public class TrvlRankService {

	private TrvlRankDAO_interface dao;

	public TrvlRankService() {
		dao = new TrvlRankDAO();
	}

	public TrvlRankVO addTrvlRank(Integer trvl_id, Integer user_id, Integer trvl_score) {

		TrvlRankVO trvlrankVO = new TrvlRankVO();
		trvlrankVO.setTrvl_id(trvl_id);
		trvlrankVO.setTrvl_id(user_id);
		trvlrankVO.setTrvlrank_score(trvl_score);

		dao.insert(trvlrankVO);

		return trvlrankVO;
	}

	public TrvlRankVO updateTrvlRank(Integer trvl_id, Integer user_id, Integer trvl_score) {

		TrvlRankVO trvlrankVO = new TrvlRankVO();
		trvlrankVO.setTrvl_id(trvl_id);
		trvlrankVO.setTrvl_id(user_id);
		trvlrankVO.setTrvlrank_score(trvl_score);

		dao.update(trvlrankVO);

		return trvlrankVO;
	}
	
	public Integer getoneTrvlRank(Integer trvl_id, Integer user_id) {
		return dao.findByPrimaryKey(trvl_id,user_id);
	}
	
	public List<TrvlRankVO> getAll() {
		return	dao.getAll();
	}
}
