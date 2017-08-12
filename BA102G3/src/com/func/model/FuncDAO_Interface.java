package com.func.model;

import java.util.List;

public interface FuncDAO_Interface {
	public void insert(FuncVO funcVO);
	public void update(FuncVO funcVO);
	public void delete(Integer func_id);
	public FuncVO findByPrimaryKey(Integer func_id);
	public List<FuncVO> getAll();
}
