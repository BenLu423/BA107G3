package com.evem.model;

import java.util.*;

public interface EvemDAO_interface {
    public void insert(EvemVO evemVO);
    public void update(EvemVO evemVO);
    public void delete(String evemes_no);
    public EvemVO findByPrimaryKey(String evemes_no);
    public List<EvemVO> getAll();

    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
