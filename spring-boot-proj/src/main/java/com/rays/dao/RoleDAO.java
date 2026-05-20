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

@Repository
public class RoleDAO {

	@PersistenceContext
	public EntityManager entityManager;

	public long add(RoleDTO dto) {
		entityManager.persist(dto);
		return dto.getId();

	}

	public void update(RoleDTO dto) {
		entityManager.merge(dto);

	}

	public void delete(RoleDTO dto) {
		entityManager.remove(dto);

	}

	public RoleDTO findByPk(long pk) {
		RoleDTO dto = entityManager.find(RoleDTO.class, pk);
		return dto;
	}

	public List<RoleDTO> search(RoleDTO dto, int pageNo, int pageSize) {

		List<RoleDTO> list = null;

		// CriteriaBuilder SQL query programmatically banane ke kaam aata hai(sql query
		// banane ke liye)
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		// RoleDTO type ki query banate hain (result RoleDTO hoga)(CriteriaQueiry DTO
		// Type ki query banane ke liye)
		CriteriaQuery<RoleDTO> cq = builder.createQuery(RoleDTO.class);

		// Query kis table/entity par chalegi - yaha RoleDTO table search filter lagane
		// k liye
		Root<RoleDTO> qRoot = cq.from(RoleDTO.class);

		List<Predicate> predicateList = new ArrayList<Predicate>();

		if (dto != null) {
			if (dto.getId() != null && dto.getId() > 0) {
				predicateList.add(builder.equal(qRoot.get("id"), dto.getId()));
			}
			if (dto.getName() != null && dto.getName().length() > 0) {
				predicateList.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
			}
			if (dto.getDescription() != null && dto.getDescription().length() > 0) {
				predicateList.add(builder.like(qRoot.get("description"), dto.getDescription() + "%"));
			}
		}

		// final query = select * from RoleDTO
		cq.where(predicateList.toArray(new Predicate[predicateList.size()]));

		TypedQuery<RoleDTO> tq = entityManager.createQuery(cq);

		if (pageSize > 0) {
			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageSize);
		}

//		select * from RoleDTO where 1=1 limit 0,5;

		list = tq.getResultList();

		return list;
	}

}
