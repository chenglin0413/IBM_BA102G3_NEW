package com.reta.model;

import java.util.List;

import com.rest.model.RestVO;
import com.reta.model.RetaVO;

public interface RetaDAO_Interface {
	public void insert(RetaVO retaVO);
    public void update(RetaVO retaVO);
    public void delete(Integer reta_id);
    public RetaVO findByPrimaryKey(Integer reta_id);
    public List<RetaVO> getAll();
    public List<RetaVO> findByUserId(Integer user_id);
}
