package com.cafe24.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.repository.PostDao;
import com.cafe24.jblog.repository.UserDao;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;

@Service
public class JBlogService {
	private static final String SAVE_PATH = "/mysite-uploads/";
	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PostDao postDao;
	
	@Transactional
	public Map getBlogMain(String id, Optional<Long> categoryNo, Optional<Long> postNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(postNo.isPresent()) {
			map.put("latePost", postDao.getPost(postNo.get()));
			map.put("postList", postDao.getPostList(postNo.get()));
			map.put("categoryList", categoryDao.getCategoryList(id));
			map.put("categoryNo", categoryNo.get());
			return map;
		}else if(categoryNo.isPresent()) {
			map.put("latePost", postDao.getTrueCtNoPost(categoryNo.get()));
			map.put("postList", postDao.getTrueCtNoPostList(categoryNo.get()));
			map.put("categoryList", categoryDao.getCategoryList(id));
			map.put("categoryNo", categoryNo.get());
			return map;
		}else {
			
			PostVo postVo = postDao.getLatePost(id);
			List<CategoryVo> categoryList = categoryDao.getCategoryList(id);
			List<PostVo> postList = null;
			if(postVo != null) {
				postList = postDao.getBlogContent(postVo.getCategoryNo());
				map.put("postList", postList);
			}
			map.put("latePost", postVo);
			map.put("categoryList", categoryList);
			return map;
		}
	}
	

	public BlogVo getBlogInfo(String id) {
		
		return blogDao.getBlogContent(id);
	}

	@Transactional
	public void updateBlog(BlogVo blogVo, MultipartFile multipartFile) {
		if(!multipartFile.isEmpty()) {
			try {
				byte[] fileData = multipartFile.getBytes();
				OutputStream os = new FileOutputStream(SAVE_PATH + blogVo.getId()+"_"+multipartFile.getOriginalFilename() );
				os.write(fileData);
				os.close();
			} catch (IOException e) {
				throw new RuntimeException();
			}
			blogVo.setLogo("assets/images/"+blogVo.getId()+"_"+multipartFile.getOriginalFilename());
		}
		blogDao.updateBlog(blogVo);
		
	}

	public Map getAdminCategory(String id) {
		Map map = new HashMap();
		ArrayList<CategoryVo> categoryList = categoryDao.getAdminCategory(id);
		
		ArrayList postCount = new ArrayList();
		for(CategoryVo vo : categoryList) {
			postCount.add(postDao.getPostCount(vo.getNo()));
		}
		map.put("categoryList", categoryList);
		map.put("postCount", postCount);
		
		return map;
	}

	@Transactional
	public int deleteCategory(Long cateNo, String id) {
		int postCheck = postDao.getPostCount(cateNo);
		
		if(postCheck > 0) {
			Long firstCateNo = categoryDao.getFirstCategory(id);
			Map<String, Long> map = new HashMap<String, Long>();
			map.put("originNo", cateNo);
			map.put("changeNo", firstCateNo);
			postDao.updateCategoryNo(map);
		}
		
		return categoryDao.deleteCategory(cateNo);
	}

	@Transactional
	public int insertCategory(CategoryVo categoryVo) {
		return categoryDao.insertCategory(categoryVo);
	}

	public ArrayList<CategoryVo> getCategoryList(String id) {
		return categoryDao.getAdminCategory(id);
	}
	@Transactional
	public void insertPost(PostVo postVo) {
		postDao.insertPost(postVo);
	}

	
}
