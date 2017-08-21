package com.path.model;

import java.util.List;

public class PathService {

	private PathDAO_Interface dao;

	public PathService() {
		dao = new PathDAO();
	}

	public PathVO addPath(Integer path_term, Double path_fromlon, Double path_fromlat, Double path_tolon, Double path_tolat,
			String path_fromplace, String path_toplace, byte[] path_img, String path_imgfmt) {

		PathVO pathVO = new PathVO();

		pathVO.setPath_term(path_term);
		pathVO.setPath_fromlon(path_fromlon);
		pathVO.setPath_fromlat(path_fromlat);
		pathVO.setPath_tolon(path_tolon);
		pathVO.setPath_tolat(path_tolat);
		pathVO.setPath_fromplace(path_fromplace);
		pathVO.setPath_toplace(path_toplace);
		pathVO.setPath_img(path_img);
		pathVO.setPath_imgfmt(path_imgfmt);
		dao.insert(pathVO);
		
		return pathVO;
	}

	public PathVO updatePath(Integer path_id, Integer path_term, Double path_fromlon, Double path_fromlat, Double path_tolon, Double path_tolat,
			String path_fromplace, String path_toplace, byte[] path_img, String path_imgfmt, int updateImg) {
		
		PathVO pathVO = new PathVO();

		pathVO.setPath_id(path_id);
		pathVO.setPath_term(path_term);
		pathVO.setPath_fromlon(path_fromlon);
		pathVO.setPath_fromlat(path_fromlat);
		pathVO.setPath_tolon(path_tolon);
		pathVO.setPath_tolat(path_tolat);
		pathVO.setPath_fromplace(path_fromplace);
		pathVO.setPath_toplace(path_toplace);
		pathVO.setPath_img(path_img);
		pathVO.setPath_imgfmt(path_imgfmt);
					
		dao.update(pathVO, updateImg);
		
		return pathVO;
	}
		
	public void deletePath(Integer path_id) {
		dao.delete(path_id);
	}

	public PathVO getOnePath(Integer path_id) {
		return dao.findByPrimaryKey(path_id);
	}
	
	public PathVO getOnePathFromTo(String path_fromplace, String path_toplace) {
		return dao.findByFromTo(path_fromplace,path_toplace);
	}
	
	public List<PathVO> getAll() {
		return dao.getAll();
	}
	
}
