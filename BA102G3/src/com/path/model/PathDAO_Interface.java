package com.path.model;

import java.util.List;
import java.util.Set;

import com.auth.model.AuthVO;

public interface PathDAO_Interface {
	public void insert(PathVO pathVO);
	public void update(PathVO pathVO, Integer updateImg);
	public void delete(Integer path_id);
	public PathVO findByPrimaryKey(Integer path_id);
	public PathVO findByFromTo(String path_fromplace, String path_toplace);
	public List<PathVO> getAll();
}
