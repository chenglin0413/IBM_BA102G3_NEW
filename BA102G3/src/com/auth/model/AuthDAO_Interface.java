package com.auth.model;

import java.util.List;

public interface AuthDAO_Interface {
	public void insert(AuthVO authVO);
	public void update(AuthVO authVO);
	public void updateAuthById(Integer sysu_id, Integer func_id);
	public void delete(Integer sysu_id, Integer func_id);
	public void deleteId(Integer sysu_id);
	public List<AuthVO> findByPrimaryKey(Integer sysu_id);
	public List<AuthVO> getAll();
}
