package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.AccountDTO;
import com.rays.dto.RoleDTO;
import com.rays.dto.UserDTO;
import com.rays.form.AccountForm;
import com.rays.form.UserForm;
import com.rays.service.AccountService;

@RestController
@RequestMapping(value = "account")
public class AccountCtl extends BaseCtl {

	@Autowired
	AccountService accService;

	@PostMapping("save")
	public ORSResponse save(@RequestBody AccountForm form) {
		ORSResponse res = new ORSResponse();
		AccountDTO dto = new AccountDTO();

		dto.setName(form.getName());
		dto.setAccountNO(form.getAccountNo());
		dto.setBalance(form.getBalance());
		dto.setDob(form.getDob());
		dto.setType(form.getType());

		long id = accService.add(dto);
		if (id != 0 && id > 0) {
			res.addData(dto);
			res.setSuccess(true);
			res.addMessage("Account added Succesfully");
		} else {
			res.addMessage("Unable to add account due to error");
		}
		return res;

	}

	@PostMapping("update")
	public ORSResponse update(@RequestBody AccountForm form) {
		ORSResponse res = new ORSResponse();
		AccountDTO dto = new AccountDTO();

		dto.setId(form.getId());
		dto.setName(form.getName());
		dto.setAccountNO(form.getAccountNo());
		dto.setBalance(form.getBalance());
		dto.setDob(form.getDob());
		dto.setType(form.getType());

		accService.update(dto);
		res.addData(dto);
		res.setSuccess(true);
		res.addMessage("Account added Succesfully");
		return res;
	}

	@PostMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable(required = false) long[] ids) {

		ORSResponse res = new ORSResponse();

		if (ids != null && ids.length > 0) {
			try {
				for (long id : ids) {
					accService.delete(id);
				}
				res.addMessage("Record Deleted Succesfully");
				res.setSuccess(true);
			} catch (Exception e) {
				res.addMessage("Error while deleting records");
			}
		} else {
			res.addMessage("Select at least one Record");
			res.setSuccess(false);
		}
		return res;
	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable(required = false) long id) {

		ORSResponse res = new ORSResponse();
		AccountDTO dto = accService.findById(id);
		if (dto != null) {
			res.addData(dto);
			res.setSuccess(true);
		} else {
			res.addMessage("Record not Found");
		}
		return res;
	}

	@GetMapping("search/{pageNo}")
	public ORSResponse search(@PathVariable(required = false) int pageNo) {
		ORSResponse res = new ORSResponse();
		int pageSize = 5;
		AccountDTO dto = new AccountDTO();
		List<AccountDTO> list = accService.search(dto, pageNo, pageSize);
		//System.out.println("list: " + list.size());
		if (list != null) {
			res.addData(list);
			res.setSuccess(true);
		} else {
			res.addMessage("Record not found");
		}

		return res;
	}
	@RequestMapping(value = "/search/{pageNo}", method = {RequestMethod.GET, RequestMethod.POST})
	public ORSResponse search(@RequestBody(required = false) AccountForm form, @PathVariable int pageNo) {
		ORSResponse res = new ORSResponse();
		AccountDTO dto = new AccountDTO();
		int pageSize = 5;
		
		dto.setId(form.getId());
		dto.setName(form.getName());
		dto.setAccountNO(form.getAccountNo());
		dto.setBalance(form.getBalance());
		dto.setDob(form.getDob());
		dto.setType(form.getType());
		 
		List<AccountDTO> list = accService.search(dto, pageNo, pageSize);
		//System.out.println("list: " + list.size());

		if (list != null && list.size() > 0) {
			res.setSuccess(true);
		}

		res.addData(list);

		return res;
	}
}
