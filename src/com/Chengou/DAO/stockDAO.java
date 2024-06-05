package com.Chengou.DAO;

import com.Chengou.Entity.CenterData;

public interface stockDAO {
    public void insert(CenterData data);
    public void insertAll(CenterData[] data);
}
