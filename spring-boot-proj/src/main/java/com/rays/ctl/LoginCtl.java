package com.rays.ctl;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.UserDTO;
import com.rays.form.LoginForm;
import com.rays.form.UserRegistrationForm;
import com.rays.service.UserService;

@RestController
@RequestMapping(value = "Auth")
public class LoginCtl extends BaseCtl {

	@Autowired
	UserService userService;

	@PostMapping("signup")
	public ORSResponse save(@RequestBody @Valid UserRegistrationForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		UserDTO dto = (UserDTO) form.getDto();
		
		try {
			long pk = userService.add(dto);
			res.addData(dto);
			res.addMessage("User added Successfully..!!");
			res.setSuccess(true);
		} catch (RuntimeException e) {
			res.addMessage("Login Id already Exist");
			res.setSuccess(false);
		}

		return res;
	}
	

	@PostMapping("/login")
	public ORSResponse login(@RequestBody @Valid LoginForm form, BindingResult bindingResult, HttpSession session) {

		ORSResponse res = new ORSResponse();

		res = validate(bindingResult);

		if (res.isSuccess() == false) {
			return res;
		}

		UserDTO dto = new UserDTO();
		dto.setLoginId(form.getLoginId());
		dto.setPassword(form.getPassword());

		dto = userService.authenticate(dto.getLoginId(), dto.getPassword());

		if (dto != null) {
			session.setAttribute("user", dto);
			res.addData(dto);
			res.addMessage("User Logged In Succesfully");
			res.setSuccess(true);
		} else {
			res.addMessage("Invalid Login or Password");
		}

		return res;

	}

}
