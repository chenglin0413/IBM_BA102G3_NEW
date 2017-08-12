package com.rest.model;

import java.util.List;

public interface RestDAO_Interface {
	public void insert(RestVO restVO);
    public void update(RestVO restVO, Integer user_status);
    public void delete(Integer rest_id);
    public RestVO findByPrimaryKey(Integer rest_id);
    public RestVO findByUserId(Integer user_id);
    public List<RestVO> getAll();
    public List<RestVO> getAllbyStatus(Integer user_status);
}
