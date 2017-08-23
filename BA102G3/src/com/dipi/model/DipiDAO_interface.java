package com.dipi.model;

import java.util.List;


public interface DipiDAO_interface {
	public void insert(DipiVO dipiVO);
	public void update(DipiVO dipiVO);
	public void delete(Integer dipi_id);
	public DipiVO findByPrimaryKey(Integer dipi_id);
	public List<DipiVO> getAll();
	public byte[] download(Integer dipi_id);
}
