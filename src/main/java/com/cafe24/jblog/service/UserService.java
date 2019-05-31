package com.cafe24.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.repository.UserDao;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	
	@Transactional
	public Boolean insertUser(UserVo userVo) {
		Boolean insertUser = userDao.insertUser(userVo);
		
		BlogVo blogVo = new BlogVo();
		blogVo.setId(userVo.getId());
		blogVo.setTitle(userVo.getName() + "'s Blog");
		blogVo.setLogo("assets/images/spring-logo.jpg");
		Boolean insertBlog = blogDao.insertFirstBlog(blogVo);
		
		Boolean insertCategory = categoryDao.insertFirstCategory(userVo.getId());
		
		return true;
	}

	public UserVo getUser(UserVo userVo) {
		return userDao.getUser(userVo);
	}

	public Boolean existId(String id) {
		return userDao.existId(id);
	}

}
