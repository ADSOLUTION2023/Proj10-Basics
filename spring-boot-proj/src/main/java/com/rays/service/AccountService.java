package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.AccountDAO;
import com.rays.dto.AccountDTO;
import com.rays.dto.RoleDTO;
import com.rays.dto.UserDTO;


@Service
@Transactional
public class AccountService {

	@Autowired
	AccountDAO accountDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(AccountDTO dto) {
		long pk = accountDao.add(dto);
		return pk;
	
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(AccountDTO dto) {
		accountDao.update(dto);
	}
	
	@Transactional(readOnly = true)
	public AccountDTO findById(long pk) {
		AccountDTO dto = accountDao.findById(pk);
		return dto;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long Id) {
		try {
			AccountDTO dto = accountDao.findById(Id);
			accountDao.delete(dto);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	@Transactional(readOnly = true)
	public List<AccountDTO> search(AccountDTO dto, int pageNo, int pageSize) {
		return accountDao.search(dto, pageNo, pageSize);
	}
	
}
