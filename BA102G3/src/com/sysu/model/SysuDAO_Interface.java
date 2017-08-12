package com.sysu.model;

import java.util.List;
import java.util.Set;

import com.auth.model.AuthVO;

public interface SysuDAO_Interface {
	public void insert(SysuVO sysuVO, String[] funcs);
	public void update(SysuVO sysuVO);
	public void delete(Integer sysu_id);
	public SysuVO findByPrimaryKey(Integer sysu_id);
	public SysuVO findByAccount(String sysu_account);
	public List<SysuVO> getAll();
	public List<AuthVO> getAuthBySysuid(Integer sysu_id);
}
