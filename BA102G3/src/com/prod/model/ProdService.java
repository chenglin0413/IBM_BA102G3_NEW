package com.prod.model;

import java.util.List;
import java.util.Map;

import com.json.JSONObject;
import com.ord.model.OrdVO;
import com.prpi.model.PrpiVO;

public class ProdService {

	private ProdDAO_interface dao;

	public ProdService() {
		dao = new ProdDAO();
	}

	public ProdVO addProd(Integer store_id, String prod_name, String prod_descript, Integer prod_price,String prod_sort,String prod_format,String prod_brand,
			java.sql.Date prod_updatetime,Integer prod_soldcount,Integer prod_status,Integer prod_count,Integer prod_score){


		ProdVO prodVO = new ProdVO();

		prodVO.setStore_id(store_id);
		prodVO.setProd_name(prod_name);
		prodVO.setProd_descript(prod_descript);
		prodVO.setProd_price(prod_price);
		prodVO.setProd_sort(prod_sort);
		prodVO.setProd_format(prod_format);
		prodVO.setProd_brand(prod_brand);
		prodVO.setProd_updatetime(prod_updatetime);
		prodVO.setProd_soldcount(prod_soldcount);
		prodVO.setProd_status(prod_status);
		prodVO.setProd_count(prod_count);
		prodVO.setProd_score(prod_score);
		dao.insert(prodVO);

		return prodVO;
	}

	public ProdVO updateProd(Integer prod_id,Integer store_id, String prod_name, String prod_descript, Integer prod_price,String prod_sort,String prod_format,String prod_brand,
			java.sql.Date prod_updatetime,Integer prod_soldcount,Integer prod_status,Integer prod_count,Integer prod_score){

		ProdVO prodVO = new ProdVO();
		prodVO.setProd_id(prod_id);
		prodVO.setStore_id(store_id);
		prodVO.setProd_name(prod_name);
		prodVO.setProd_descript(prod_descript);
		prodVO.setProd_price(prod_price);
		prodVO.setProd_sort(prod_sort);
		prodVO.setProd_format(prod_format);
		prodVO.setProd_brand(prod_brand);
		prodVO.setProd_updatetime(prod_updatetime);
		prodVO.setProd_soldcount(prod_soldcount);
		prodVO.setProd_status(prod_status);
		prodVO.setProd_count(prod_count);
		prodVO.setProd_score(prod_score);
		dao.update(prodVO);

		return prodVO;
	}

	public void deleteProd(Integer prod_id) {
		dao.delete(prod_id);
	}

	public ProdVO getOneProd(Integer Prod_id) {
		return dao.findByPrimaryKey(Prod_id);
	}
//	public ProdVO getOneProd_Store_id(Integer Prod_id) {
//		return dao.findByPrimaryKeyforStore_id(Prod_id);
//	}

	public List<ProdVO> getAll() {
		return dao.getAll();
	}
	//取得全部產品，但去除已下架產品
	public List<ProdVO> getAll4member() {
		return dao.getAll4member();
	}
	public List<ProdVO> getOneProdSort(String prod_sort) {
		return dao.getOneProdSort(prod_sort);
	}
	public List<ProdVO> getOneStoreTer(Integer store_ter) {
		System.out.println("service:"+store_ter);
		return dao.getOneStoreTer(store_ter);
		
	}
	public List<ProdVO> getOneStore_idAllProd(Integer store_id) {
		return dao.getOneStore_idAllProd(store_id);
	}
	public List<ProdVO> getTopThree() {
		return dao.getTopThree();
	}
	
	public void update_soldcount(Integer prod_soldcount,Integer prod_id){
		dao.update_soldcount(prod_soldcount, prod_id);
	}
	public void update_count_score(Integer prod_count,Integer prod_score,Integer prod_id){
		dao.update_count_score(prod_count,prod_score, prod_id);
	}
	
	public List<ProdVO> getTopTwelve() {
		return dao.getTopTwelve();
	}
	
	public void insertWithPrpi(ProdVO deptVO , List<PrpiVO> list){
		dao.insertWithPrpi(deptVO, list);
	}	
	public List<ProdVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}
