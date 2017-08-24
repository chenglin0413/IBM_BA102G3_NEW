package com.repi.model;

import java.util.List;

import com.repi.model.RepiVO;

public interface RepiDAO_Interface {
	public void insert(RepiVO repiVO);
	public void update(RepiVO repiVO);
	public void delete(Integer repi_id);
	public RepiVO findByPrimaryKey(Integer repi_id);
	public RepiVO findByRestId(Integer rest_id);
	public List<RepiVO> getAll();
	public byte[] download(Integer repi_id);
}
