package com.busc.model;


import java.util.*;

public interface BuscDAO_interface {
          public void insert(BuscVO buscVO);
          public void deleteTable(BuscVO buscVO);
          public BuscVO findByPrimaryKey(Integer busc_id);
          public List<BuscVO> getAll();
}
