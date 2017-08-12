package com.mfee.model;

import java.util.List;

public interface MfeeDAO_interface {
	public void insert(MfeeVO mfeeVO);
	public void update(MfeeVO mfeeVO);
	public void delete(Integer mfee_id);
	public MfeeVO findByPrimaryKey(Integer mfee_id);
	public List<MfeeVO> getAll();
	public List<MfeeVO> findUnpay(String year, String month);
	public List<MfeeVO> findByUserId(Integer user_id);
}
