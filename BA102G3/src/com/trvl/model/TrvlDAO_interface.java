package com.trvl.model;

import java.util.List;

import com.tlcm.model.TlcmVO;
import com.trpi.model.TrpiVO;


public interface TrvlDAO_interface {
	
	public String insert(TrvlVO trvlVO);
    public void update(TrvlVO trvlVO);
    public void delete(Integer trvl_id);
    public TrvlVO findByPrimaryKey(Integer trvl_id);
    public List<TrvlVO> getAll();
    
    //查看會員撰寫了幾篇遊記
    public List<TrvlVO> findByForeignKey (Integer user_id);
    
    //用遊記PK找出有多少照片,留言 
    public List<TlcmVO> findTlcmsByTrvlId (Integer trvl_id);
    public List<TrpiVO> findTrpisByTrvlId (Integer trvl_id);
    
    //瀏覽次數+1
    public void addPageView(Integer trvl_id);
    
    //取點擊數前三名
    public List<TrvlVO>	getTopOfBlogs();
    
    //同時新增遊記與照片 
    //public void insertWithTrpis(TrvlVO trvlVO , List<TrpiVO> list);
}
