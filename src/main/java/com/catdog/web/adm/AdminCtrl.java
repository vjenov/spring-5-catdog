package com.catdog.web.adm;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catdog.web.cmm.IConsumer;
import com.catdog.web.cmm.IFunction;

@RestController
@RequestMapping("/admins")
public class AdminCtrl {
	@Autowired Admin admin;
	@Autowired AdminMapper adminMapper;
	@Autowired Map<String, Object> map;
	
	@PostMapping("/")
	public Map<?,?> register(@RequestBody Admin param) {
		IConsumer<Admin> c = o-> adminMapper.insertAdmin(o);
		c.accept(param);
		map.put("msg", "Success");
		return map;
	}
	@PostMapping("/{aid}")
	public String access(@PathVariable String aid, @RequestBody Admin param) {
		IFunction<Admin, Admin> f = t-> adminMapper.selectAdminById(param);
		return (f.apply(param)!=null) ? "Success" : "fail";
	}
	@PutMapping("/{aid}")
	public Map<?,?> editState(@PathVariable String aid, @RequestBody Admin param) {
		return map;
	}
	@DeleteMapping("/{aid}")
	public Map<?,?> removeAdmin(@PathVariable String aid, @RequestBody Admin param) {
		return map;
	}
}
