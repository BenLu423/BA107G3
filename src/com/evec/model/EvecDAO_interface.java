package com.evec.model;

import java.util.*;


public interface EvecDAO_interface {
	  public void insert(EvecVO evecVO);
    public void update(EvecVO evecVO);
    public void delete(String evec_name);
    public EvecVO findByPrimaryKey(String evec_no);
    public List<EvecVO> getAll();
//    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<EvecVO> getAll(Map<String, String[]> map); 
}