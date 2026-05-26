package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.UserDAO;
import com.rays.dto.RoleDTO;
import com.rays.dto.UserDTO;

@Service
@Transactional
public class UserService {

	@Autowired
	UserDAO userDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(UserDTO dto) {

		UserDTO existDto = userDao.findByUniqueKey("loginId", dto.getLoginId());

		if (existDto != null) {
			throw new RuntimeException("Login Already Exist");
		}

		long pk = userDao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(UserDTO dto) {
		userDao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		try {
			UserDTO dto = findById(id);
			userDao.delete(dto);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public UserDTO findById(long pk) {
		UserDTO dto = userDao.findById(pk);
		return dto;
	}

	@Transactional(readOnly = true)
	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) {
		return userDao.search(dto, pageNo, pageSize);
	}

	@Transactional(readOnly = true)
	public UserDTO authenticate(String login, String password) {

		UserDTO dto = new UserDTO();

		dto = userDao.findByUniqueKey("loginId", login);

		System.out.println("name: " + dto.getFirstName());
		System.out.println("password: " + dto.getPassword());

		if (dto != null && dto.getPassword().equals(password)) {
			return dto;
		}
		return null;

	}
}
