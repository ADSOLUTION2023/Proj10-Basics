package com.rays.ctl;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.AttachmentDTO;
import com.rays.dto.UserDTO;
import com.rays.form.UserForm;
import com.rays.service.AttachmentService;
import com.rays.service.UserService;

@RestController
@RequestMapping(value = "user")
public class UserCtl extends BaseCtl {
	
	@Autowired
	UserService userService;
	
	@Autowired
	public AttachmentService attachmentService;
	
	@PostMapping("save")
	public ORSResponse save(@RequestBody UserForm form) {

		ORSResponse res = new ORSResponse();

		UserDTO dto = new UserDTO();

		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setLoginId(form.getLoginId());
		dto.setPassword(form.getPassword());
		dto.setRoleId(form.getRoleId());
		dto.setRoleName(form.getRoleName());

		long id = userService.add(dto);
		// long id = 0;

		if (id != 0 && id > 0) {
			res.setSuccess(true);
			res.addData(dto);
			res.addMessage("User Saved Successfulluy");
		} else {
			res.addMessage("Error to Save User");
		}

		return res;
	}
	
	@PostMapping("update")
	public ORSResponse update(@RequestBody UserForm form) {

		ORSResponse res = new ORSResponse();
		UserDTO dto = new UserDTO();

		dto.setId(form.getId());
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setLoginId(form.getLoginId());
		dto.setPassword(form.getPassword());
		dto.setRoleId(form.getRoleId());

		userService.update(dto);
		res.addData(dto);
		res.addMessage("User Updated Successfully");
		res.setSuccess(true);
		return res;
	}
	
	@PostMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable(required = false) long[] ids) {
		ORSResponse res = new ORSResponse();

		if (ids != null && ids.length > 0) {
		    try {
		        for (long id : ids) {
		            userService.delete(id);
		        }
		        res.addMessage("Record(s) Deleted Successfully");
		        res.setSuccess(true);
		    } catch (Exception e) {
		        res.addMessage("Error while deleting records");
		        res.setSuccess(false);
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
		UserDTO dto = userService.findById(id);
		if (dto != null) {
			res.addData(dto);
			res.setSuccess(true);
		} else {
			res.addMessage("Record not Found");
		}
		return res;
	}
	@RequestMapping(value = "/search/{pageNo}", method = {RequestMethod.GET, RequestMethod.POST})
	public ORSResponse search(@RequestBody(required = false) UserForm form, @PathVariable int pageNo) {
		ORSResponse res = new ORSResponse();
		UserDTO dto = new UserDTO();
		int pageSize = 5;
		
		dto.setId(form.getId());
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setLoginId(form.getLoginId());
		dto.setPassword(form.getPassword());
		dto.setRoleId(form.getRoleId());
		 
		List<UserDTO> list = userService.search(dto, pageNo, pageSize);
		//System.out.println("list: " + list.size());

		if (list != null && list.size() > 0) {
			res.setSuccess(true);
		}

		res.addData(list);

		return res;
	}
	
	@PostMapping("/profilePic/{userId}")
	public ORSResponse uploadPic(@PathVariable Long userId, @RequestParam("file") MultipartFile file,
			HttpServletRequest req) {

		AttachmentDTO attachmentDto = new AttachmentDTO(file);

		attachmentDto.setDescription("profile pic");

		attachmentDto.setUserId(userId);

		UserDTO userDto = userService.findById(userId);

		if (userDto.getImageId() != null && userDto.getImageId() > 0) {
			attachmentDto.setId(userDto.getImageId());
		}

		Long imageId = attachmentService.save(attachmentDto);

		if (userDto.getImageId() == null) {
			userDto.setImageId(imageId);
			userService.update(userDto);
		}

		ORSResponse res = new ORSResponse();
		res.addResult("imageId", imageId);
		res.addResult("userId", userId);
		res.setSuccess(true);

		return res;
	}
	
	@GetMapping("/profilePic/{userId}")
	public void downloadPic(@PathVariable Long userId, HttpServletResponse response) {
		try {

			UserDTO userDto = userService.findById(userId);

			AttachmentDTO attachmentDTO = null;

			if (userDto != null) {
				attachmentDTO = attachmentService.findById(userDto.getImageId());
			}

			if (attachmentDTO != null) {

				response.setContentType(attachmentDTO.getType());
				OutputStream out = response.getOutputStream(); // write byte code on response according to contenttype
				out.write(attachmentDTO.getDoc());
				out.close();

			} else {

				response.getWriter().write("ERROR: File not found");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
