package com.cafe24.jblog.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.service.JBlogService;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;
import com.cafe24.jblog.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
public class JblogController {
	
	@Autowired
	private JBlogService jblogService;
	
	@RequestMapping(value= {"/{id:(?!assets).*}", 
							"/{id:(?!assets).*}/{categoryNo:.*}",
							"/{id:(?!assets).*}/{categoryNo:.*}/{postNo:.*}"}, method = RequestMethod.GET)
	public String blogMain(@PathVariable("id") String id, 
							@PathVariable("categoryNo") Optional<Long> categoryNo,
							@PathVariable("postNo") Optional<Long> postNo,
							Model model) 
	{
		
		Map map = jblogService.getBlogMain(id, categoryNo, postNo);
		model.addAttribute("blogVo", jblogService.getBlogInfo(id));
		model.addAttribute("map", map);
		return "blog/blog-main";
	}
	
	@Auth
	@RequestMapping(value="/{id:(?!assets).*}/admin/basic", method = RequestMethod.GET)
	public String adminBasic(@PathVariable("id") String id, Model model, @AuthUser UserVo authUser) {
		if(!authUser.getId().equals(id)) {
			return "redirect:/"+id;
		}
		
		model.addAttribute("blogVo", jblogService.getBlogInfo(id));
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value="/{id:(?!assets).*}/admin/basic", method = RequestMethod.POST)
	public String blogUpdate(@PathVariable("id") String id, 
							@ModelAttribute(value="blogVo") BlogVo blogVo, 
							@RequestParam(value="file") MultipartFile multipartFile, 
							Model model) 
	{
		
		jblogService.updateBlog(blogVo, multipartFile);
		
		return "redirect:/"+id+"/admin/basic";
	}
	
	
	//                   /*/admin/**
	@Auth
	@RequestMapping(value="/{userId:(?!assets).*}/admin/category", method = RequestMethod.GET)
	public String adminCategory(@PathVariable("id") String id, Model model, @AuthUser UserVo authUser) {
		
		if(!authUser.getId().equals(id)) {
			return "redirect:/"+id;
		}
		
		model.addAttribute("blogVo", jblogService.getBlogInfo(id));
		model.addAttribute("map", jblogService.getAdminCategory(id));
		
		return "blog/blog-admin-category";
	}
	
	@RequestMapping(value="/{id:(?!assets).*}/admin/category", method = RequestMethod.POST)
	public String categoryUpdate(@PathVariable("id") String id, Model model) {
		
		return "redirect:/"+id+"/admin/basic";
	}
	
	@RequestMapping(value="/{id:(?!assets).*}/admin/category/delete", method = RequestMethod.POST)
	@ResponseBody
	public String categoryDelete(@PathVariable("id") String id, @RequestParam(value="$cateNo") Long cateNo, Model model) {
		String result = "";
		if(0 <jblogService.deleteCategory(cateNo, id)) {
			result = "success";
		}else {
			result = "fail";
		}
		
		return result;
	}
	
	@RequestMapping(value="/{id:(?!assets).*}/admin/category/insert", method = RequestMethod.POST)
	@ResponseBody
	public String categoryInsert(@PathVariable("id") String id, @ModelAttribute CategoryVo categoryVo, Model model) {
		String result = "";
		
		if( 0 < jblogService.insertCategory(categoryVo) ) {
			result = "success";
			model.addAttribute("data", jblogService.getAdminCategory(id));
		}else {
			result = "fail";
		}
		
		return result;
	}
	
	@Auth
	@RequestMapping(value="/{id:(?!assets).*}/admin/write", method = RequestMethod.GET)
	public String adminWrite(@PathVariable("id") String id, Model model, @AuthUser UserVo authUser) {
		
		if(!authUser.getId().equals(id)) {
			return "redirect:/"+id;
		}
		
		List list = jblogService.getCategoryList(id);
		model.addAttribute("blogVo", jblogService.getBlogInfo(id));
		model.addAttribute("list", list);
		
		return "blog/blog-admin-write";
	}
	
	@RequestMapping(value="/{id:(?!assets).*}/admin/write", method = RequestMethod.POST)
	public String adminWrite(@PathVariable("id") String id, @ModelAttribute PostVo postVo, Model model) {
		
		jblogService.insertPost(postVo);
		return "redirect:/"+id;
	}
	
	
}
