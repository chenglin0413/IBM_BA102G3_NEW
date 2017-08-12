package com.prpi.model;

import java.util.List;

public interface PrpiDAO_interface {
    public void insert(PrpiVO prpiVO);
    public void update(PrpiVO prpiVO);
    public void delete(Integer prpi_id);
    public PrpiVO findByPrimaryKey(Integer prpi_id);
    public List<PrpiVO> getAll();
    
    public PrpiVO findByProd_id(Integer prod_id); //產品取圖
    public void insertImg (PrpiVO prpiVO , java.sql.Connection con); //新增產品與圖
}
