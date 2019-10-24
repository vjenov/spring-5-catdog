package com.catdog.web.adm;

import org.springframework.stereotype.Repository;
import com.catdog.web.adm.Admin;

@Repository
public interface AdminMapper {
	public Admin selectAdminById(Admin param);
	public void insertAdmin(Admin param);
}

