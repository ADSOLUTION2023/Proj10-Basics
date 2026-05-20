package com.rays.ctl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.RoleDTO;
import com.rays.form.RoleForm;
import com.rays.service.RoleService;

@RestController
@RequestMapping(value = "role")
public class RoleCtl extends BaseCtl {

	@Autowired
	RoleService roleService;

	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid RoleForm form, BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();
		
		res = validate(bindingResult);

		if (res.isSuccess() == false) {
			return res;
		}

		RoleDTO dto = new RoleDTO();

		dto.setName(form.getName());
		dto.setDescription(form.getDescription());

		long id = roleService.add(dto);
		// long id = 0;

		if (id != 0 && id > 0) {
			res.setSuccess(true);
			res.addData(dto);
			res.addMessage("Role Saved Successfulluy");
		} else {
			res.addMessage("Error to Save Role");
		}

		return res;
	}

	@PostMapping("update")
	public ORSResponse update(@RequestBody RoleForm form) {

		ORSResponse res = new ORSResponse();
		RoleDTO dto = new RoleDTO();

		dto.setId(form.getId());
		dto.setName(form.getName());
		dto.setDescription(form.getDescription());

		roleService.update(dto);
		res.addData(dto);
		res.addMessage("Role Updated Successfully");
		res.setSuccess(true);
		return res;
	}

	@PostMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable(required = false) long[] ids) {
		ORSResponse res = new ORSResponse();

		if (ids != null && ids.length > 0) {
			for (long id : ids) {
				roleService.delete(id);
				res.addMessage("Record Deleted Successfully");
				res.setSuccess(true);
			}
		} else {
			res.addMessage("Select at least one Record");
		}
		return res;
	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable(required = false) long id) {
		ORSResponse res = new ORSResponse();
		RoleDTO dto = roleService.findById(id);
		if (dto != null) {
			res.addData(dto);
			res.setSuccess(true);
		} else {
			res.addMessage("Record not Found");
		}
		return res;
	}
	@RequestMapping(value = "/search/{pageNo}", method = {RequestMethod.GET, RequestMethod.POST})
	public ORSResponse search(@RequestBody(required = false) RoleForm form, @PathVariable int pageNo) {
		ORSResponse res = new ORSResponse();
		RoleDTO dto = new RoleDTO();
		int pageSize = 5;
		
		dto.setName(form.getName());
		dto.setDescription(form.getDescription());
		
		List<RoleDTO> list = roleService.search(dto, pageNo, pageSize);
		//System.out.println("list: " + list.size());

		if (list != null && list.size() > 0) {
			res.setSuccess(true);
		}

		res.addData(list);

		return res;
	}
}
