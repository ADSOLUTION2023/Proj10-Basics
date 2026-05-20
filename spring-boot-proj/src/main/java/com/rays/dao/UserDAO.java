package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.dto.RoleDTO;
import com.rays.dto.UserDTO;

@Repository
public class UserDAO {

	@PersistenceContext
	public EntityManager entityManager;

	public long add(UserDTO dto) {
		entityManager.persist(dto);
		return dto.getId();

	}

	public void update(UserDTO dto) {
		entityManager.merge(dto);
	}

	public void delete(UserDTO dto) {
		entityManager.remove(dto);
	}

	public UserDTO findById(long Id) {
		UserDTO dto = entityManager.find(UserDTO.class, Id);
		return dto;
	}

	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) {

		List<UserDTO> list = null;

		// CriteriaBuilder SQL query programmatically banane ke kaam aata hai(sql query
		// banane ke liye)
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		// UserDTO type ki query banate hain (result UserDTO hoga)(CriteriaQueiry DTO
		// Type ki query banane ke liye)
		CriteriaQuery<UserDTO> cq = builder.createQuery(UserDTO.class);

		// Query kis table/entity par chalegi - yaha UserDTO table search filter lagane
		// k liye
		Root<UserDTO> qRoot = cq.from(UserDTO.class);

		List<Predicate> predicateList = new ArrayList<Predicate>();

		if (dto != null) {
			// ID
			if (dto.getId() != null && dto.getId() > 0) {
				predicateList.add(builder.equal(qRoot.get("id"), dto.getId()));
			}

			// First Name
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				predicateList.add(builder.like(qRoot.get("firstName"), dto.getFirstName() + "%"));
			}

			// Last Name
			if (dto.getLastName() != null && dto.getLastName().length() > 0) {
				predicateList.add(builder.like(qRoot.get("lastName"), dto.getLastName() + "%"));
			}

			// Login ID
			if (dto.getLoginId() != null && dto.getLoginId().length() > 0) {
				predicateList.add(builder.like(qRoot.get("loginId"), dto.getLoginId() + "%"));
			}

			// Password (generally not used in search, but if needed)
			if (dto.getPassword() != null && dto.getPassword().length() > 0) {
				predicateList.add(builder.equal(qRoot.get("password"), dto.getPassword()));
			}

			// Role ID
			if (dto.getRoleId() != null) {
				predicateList.add(builder.equal(qRoot.get("roleId"), dto.getRoleId()));
			}

			// Role Name
			if (dto.getRoleName() != null && dto.getRoleName().length() > 0) {
				predicateList.add(builder.like(qRoot.get("roleName"), dto.getRoleName() + "%"));
			}
		}

		// final query = select * from UserDTO
		cq.where(predicateList.toArray(new Predicate[predicateList.size()]));

		TypedQuery<UserDTO> tq = entityManager.createQuery(cq);

		if (pageSize > 0) {
			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageSize);
		}

//		select * from UserDTO where 1=1 limit 0,5;

		list = tq.getResultList();

		return list;
	}

	public UserDTO findByUniqueKey(String attribute, String value) {

		List<UserDTO> list = null;
		UserDTO dto = null;

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<UserDTO> cq = builder.createQuery(UserDTO.class);

		Root<UserDTO> qRoot = cq.from(UserDTO.class);

		Predicate condition = builder.equal(qRoot.get(attribute), value);

		cq.where(condition);

		TypedQuery<UserDTO> tq = entityManager.createQuery(cq);

		list = tq.getResultList();
		System.out.println("list: " + list.size());
		if (list.size() == 1) {
			dto = new UserDTO();
			System.out.println("list: " + list.size());
			dto = list.get(0);
		}

		return dto;
	}
}
