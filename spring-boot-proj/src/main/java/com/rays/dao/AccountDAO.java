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

import com.rays.dto.AccountDTO;
import com.rays.dto.RoleDTO;
import com.rays.dto.UserDTO;

@Repository
public class AccountDAO {

	@PersistenceContext
	public EntityManager entityManager;

	public long add(AccountDTO dto) {
		entityManager.persist(dto);
		return dto.getId();
	}

	public void update(AccountDTO dto) {
		entityManager.merge(dto);
	}

	public void delete(AccountDTO dto) {
		entityManager.remove(dto);
	}

	public AccountDTO findById(long Id) {
		 AccountDTO accNo = entityManager.find(AccountDTO.class, Id);
		 return accNo;
	}

	public List<AccountDTO> search(AccountDTO dto, int pageNo, int pageSize) {

		List<AccountDTO> list = null;

		// CriteriaBuilder SQL query programmatically banane ke kaam aata hai(sql query
		// banane ke liye)
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		// AccountDTO type ki query banate hain (result AccountDTO hoga)(CriteriaQueiry DTO
		// Type ki query banane ke liye)
		CriteriaQuery<AccountDTO> cq = builder.createQuery(AccountDTO.class);

		// Query kis table/entity par chalegi - yaha AccountDTO table search filter lagane
		// k liye
		Root<AccountDTO> qRoot = cq.from(AccountDTO.class);

		List<Predicate> predicateList = new ArrayList<Predicate>();

		if (dto != null) {
			// ID
			if (dto.getId() != null && dto.getId() > 0) {
			    predicateList.add(builder.equal(qRoot.get("id"), dto.getId()));
			}

			// Name
			if (dto.getName() != null && dto.getName().length() > 0) {
			    predicateList.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
			}

			// Date of Birth
			if (dto.getDob() != null) {
			    predicateList.add(builder.equal(qRoot.get("dob"), dto.getDob()));
			}

			// Account Number
			if (dto.getAccountNO() != null && dto.getAccountNO().length() > 0) {
			    predicateList.add(builder.equal(qRoot.get("accountNo"), dto.getAccountNO()));
			}

			// Balance (only if > 0)
			if (dto.getBalance() > 0) {
			    predicateList.add(builder.equal(qRoot.get("balance"), dto.getBalance()));
			}

			// Type (Savings / Current etc.)
			if (dto.getType() != null && dto.getType().length() > 0) {
			    predicateList.add(builder.like(qRoot.get("type"), dto.getType() + "%"));
			}
		}

		// final query = select * from AccountDTO
		cq.where(predicateList.toArray(new Predicate[predicateList.size()]));

		TypedQuery<AccountDTO> tq = entityManager.createQuery(cq);

		if (pageSize > 0) {
			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageSize);
		}

//		select * from AccountDTO where 1=1 limit 0,5;

		list = tq.getResultList();

		return list;
	}
}
