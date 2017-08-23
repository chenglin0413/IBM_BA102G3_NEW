package com.avtb.model;

import java.util.List;

import com.avtb.model.AvtbVO;
import com.reta.model.RetaVO;

public interface AvtbDAO_Interface {
	public void insert(AvtbVO avtbVO);
    public void update(AvtbVO avtbVO);
    public void delete(Integer avtb_id);
    public AvtbVO findByPrimaryKey(Integer avtb_id);
    public List<AvtbVO> findByPrimaryKeyByRest(Integer rest_id);
    public List<AvtbVO> getAll();
	public void update_max_reservation(AvtbVO avtbVO);
	public void update_reservation(AvtbVO avtbVO);
}
