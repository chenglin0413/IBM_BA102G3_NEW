package com.user.model;

import java.sql.Date;
import java.util.List;
import com.store.model.*;
import com.rest.model.*;
import com.stpi.model.*;
import com.repi.model.*;

public class UserService {

	private UserDAO_Interface dao;

	public UserService() {
		dao = new UserDAO();
	}

	public UserVO addUser(String user_account, String user_passwd, Integer user_type, String user_lastname,
			String user_firstname, String user_phone, String user_mobile, String user_address, String user_email,
			Date user_joindate, Integer user_status, byte[] user_img, String user_imgfmt) {

		UserVO userVO = new UserVO();

		userVO.setUser_account(user_account);
		userVO.setUser_passwd(user_passwd);
		userVO.setUser_type(user_type);
		userVO.setUser_lastname(user_lastname);
		userVO.setUser_firstname(user_firstname);
		userVO.setUser_phone(user_phone);
		userVO.setUser_mobile(user_mobile);
		userVO.setUser_address(user_address);
		userVO.setUser_email(user_email);
		userVO.setUser_joindate(user_joindate);
		userVO.setUser_status(user_status);
		userVO.setUser_img(user_img);
		userVO.setUser_imgfmt(user_imgfmt);
		dao.insert(userVO);
		
		return userVO;
	}

	public UserVO addStore(String user_account, String user_passwd, Integer user_type, String user_lastname,
			String user_firstname, String user_phone, String user_mobile, String user_address, String user_email,
			Date user_joindate, Integer user_status, byte[] user_img, String user_imgfmt,
			String store_name, String store_time ,String store_phone ,String store_describe ,Integer store_ter,
			String store_floor,Double store_lon,Double store_lat,Integer store_inout, Integer store_count,Integer store_score,
			Integer stpi_name, byte[] stpi_img, String stpi_imgfmt
			) {

		UserVO userVO = new UserVO();

		userVO.setUser_account(user_account);
		userVO.setUser_passwd(user_passwd);
		userVO.setUser_type(user_type);
		userVO.setUser_lastname(user_lastname);
		userVO.setUser_firstname(user_firstname);
		userVO.setUser_phone(user_phone);
		userVO.setUser_mobile(user_mobile);
		userVO.setUser_address(user_address);
		userVO.setUser_email(user_email);
		userVO.setUser_joindate(user_joindate);
		userVO.setUser_status(user_status);
		userVO.setUser_img(user_img);
		userVO.setUser_imgfmt(user_imgfmt);
		
		StoreVO storeVO = new StoreVO();
		
		storeVO.setStore_name(store_name);
		storeVO.setStore_time(store_time);
		storeVO.setStore_phone(store_phone);
		storeVO.setStore_describe(store_describe);
		storeVO.setStore_ter(store_ter);
		storeVO.setStore_floor(store_floor);
		storeVO.setStore_lon(store_lon);
		storeVO.setStore_lat(store_lat);
		storeVO.setStore_inout(store_inout);
		storeVO.setStore_count(store_count);
		storeVO.setStore_score(store_score);
				
		StpiVO stpiVO = new StpiVO();
		stpiVO.setStpi_name(stpi_name);
		stpiVO.setStpi_img(stpi_img);
		stpiVO.setStpi_imgfmt(stpi_imgfmt);

		System.out.println("stpi_name: "+stpi_name);
		System.out.println("stpi_imgfmt: "+stpi_imgfmt);
		System.out.println("pass UserService l.86");
		
		dao.insertStore(userVO, storeVO, stpiVO);
		
		System.out.println("pass UserService l.90");
		
		return userVO;
	}
	
	public UserVO addRest(String user_account, String user_passwd, Integer user_type, String user_lastname,
			String user_firstname, String user_phone, String user_mobile, String user_address, String user_email,
			Date user_joindate, Integer user_status, byte[] user_img, String user_imgfmt,
			String rest_name, String rest_address, String rest_phone ,String rest_trans ,String rest_detail ,String rest_hours, 
			Integer rest_ter, Integer rest_floor, Double rest_lon,Double rest_lat,Integer rest_inout, Integer rest_type, 
			Integer rest_count,Integer rest_score, String repi_name, byte[] repi_img, String repi_imgfmt
			) {

		UserVO userVO = new UserVO();

		userVO.setUser_account(user_account);
		userVO.setUser_passwd(user_passwd);
		userVO.setUser_type(user_type);
		userVO.setUser_lastname(user_lastname);
		userVO.setUser_firstname(user_firstname);
		userVO.setUser_phone(user_phone);
		userVO.setUser_mobile(user_mobile);
		userVO.setUser_address(user_address);
		userVO.setUser_email(user_email);
		userVO.setUser_joindate(user_joindate);
		userVO.setUser_status(user_status);
		userVO.setUser_img(user_img);
		userVO.setUser_imgfmt(user_imgfmt);
		
		RestVO restVO = new RestVO();
		
		restVO.setRest_name(rest_name);
		restVO.setRest_address(rest_address);
		restVO.setRest_phone(rest_phone);
		restVO.setRest_trans(rest_trans);
		restVO.setRest_detail(rest_detail);
		restVO.setRest_hours(rest_hours);
		restVO.setRest_ter(rest_ter);
		restVO.setRest_floor(rest_floor);
		restVO.setRest_lon(rest_lon);
		restVO.setRest_lat(rest_lat);
		restVO.setRest_inout(rest_inout);
		restVO.setRest_type(rest_type);
		restVO.setRest_count(rest_count);
		restVO.setRest_score(rest_score);
		
		RepiVO repiVO = new RepiVO();
		repiVO.setRepi_name(repi_name);
		repiVO.setRepi_img(repi_img);
		repiVO.setRepi_imgfmt(repi_imgfmt);
		
		
		System.out.println("rest_phoone: "+rest_phone);
		System.out.println("rest_inout: "+rest_inout);
		System.out.println("pass UserService l.131");
				
		dao.insertRest(userVO, restVO, repiVO);
		
		System.out.println("pass UserService l.135");
		
		return userVO;
	}
	

	public UserVO updateUser(Integer user_id, String user_account, String user_passwd, Integer user_type, String user_lastname,
			String user_firstname, String user_phone, String user_mobile, String user_address, String user_email,
			Date user_joindate, Integer user_status, byte[] user_img, String user_imgfmt, int updateImg) {
		
		UserVO userVO = new UserVO();

		userVO.setUser_id(user_id);
		userVO.setUser_account(user_account);
		userVO.setUser_passwd(user_passwd);
		userVO.setUser_type(user_type);
		userVO.setUser_lastname(user_lastname);
		userVO.setUser_firstname(user_firstname);
		userVO.setUser_phone(user_phone);
		userVO.setUser_mobile(user_mobile);
		userVO.setUser_address(user_address);
		userVO.setUser_email(user_email);
		userVO.setUser_joindate(user_joindate);
		userVO.setUser_status(user_status);
		userVO.setUser_img(user_img);
		userVO.setUser_imgfmt(user_imgfmt);
					
		dao.update(userVO, updateImg);
		
		return userVO;
	}
		
	public void deleteUser(Integer user_id) {
		dao.delete(user_id);
	}

	public UserVO getOneUser(Integer user_id) {
		return dao.findByPrimaryKey(user_id);
	}
	
	public UserVO getOneAccountUser(String user_account) {
		return dao.findByAccount(user_account);
	}

	public List<UserVO> getAll() {
		return dao.getAll();
	}
	
	public List<UserVO> getAllByType(int user_type) {
		return dao.getAllByType(user_type);
	}

}
