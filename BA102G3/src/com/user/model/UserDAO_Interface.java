package com.user.model;

import java.util.List;
import com.store.model.*;
import com.rest.model.*;
import com.stpi.model.*;
import com.repi.model.*;

public interface UserDAO_Interface {
	public void insert(UserVO userVO);
	public void insertStore(UserVO userVO, StoreVO storeVO, StpiVO stpiVO);
	public void insertRest(UserVO userVO, RestVO restVO, RepiVO repiVO);
	public void update(UserVO userVO, int updateImg);
	public void delete(Integer user_id);
	public UserVO findByPrimaryKey(Integer user_id);
	public UserVO findByAccount(String user_account);
	public List<UserVO> getAll();
	public List<UserVO> getAllByType(int user_type);
	public List<UserVO> getAllStore();
}
