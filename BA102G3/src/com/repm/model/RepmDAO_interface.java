package com.repm.model;

import java.util.*;

public interface RepmDAO_interface {

	public void insert(RepmVO repmVO);
	public void update(RepmVO repmVO);
	public RepmVO findByPrimaryKey(Integer repm_id);
	public List<RepmVO> findByRestID(Integer rest_id);
	public List<RepmVO> getAll();
}
