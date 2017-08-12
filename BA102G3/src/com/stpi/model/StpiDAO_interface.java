package com.stpi.model;

import java.util.List;

public interface StpiDAO_interface {
	public void insert(StpiVO stpiVO);
	public void update(StpiVO stpiVO);
	public void delete(Integer stpi_id);
	public StpiVO findByPrimaryKey(Integer stpi_id);
	public List<StpiVO> getAll();
}
