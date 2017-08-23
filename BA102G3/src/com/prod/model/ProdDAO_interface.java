package com.prod.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ord.model.OrdVO;
import com.prpi.model.PrpiVO;

public interface ProdDAO_interface {
	public void insert(ProdVO prodVO);
	public void update(ProdVO prodVO);
	public void delete(Integer prod_id);
	public ProdVO findByPrimaryKey(Integer prod_id);
	public Integer findByPrimaryKeyforStore_id(Integer prod_id);
	public List<ProdVO> getAll();
	public List<ProdVO> getAll4member();
	public List<ProdVO> getOneProdSort(String prod_sort);
	public List<ProdVO> getOneStoreTer(Integer store_ter);
	public List<ProdVO> getOneStore_idAllProd(Integer store_id);
	public List<ProdVO> getTopThree();//首頁熱門商品(前三名)
	public List<ProdVO> getTopTwelve();//排行人氣商品(前十二名)
	public void update_soldcount(Integer prod_soldcount,Integer prod_id);//訂單成立後連帶增加產品售出次數
	public void update_count_score(Integer prod_count,Integer prod_score,Integer prod_id);//會員送出評價後更新評價次數。
	
	
	public void insertWithPrpi(ProdVO prodVO , List<PrpiVO> list); //新增產品與圖
	
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<ProdVO> getAll(Map<String, String[]> map);
}
