package com.cafe24.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.UserVo;

@Repository
public class BlogDao {

	
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insertFirstBlog(BlogVo blogVo) {
		
		return 1 == sqlSession.insert("blog.insertFirstBlog", blogVo);
	}

	public BlogVo getBlogContent(String id) {
		return sqlSession.selectOne("blog.getBlogContent", id);
	}

	public void updateBlog(BlogVo blogVo) {
		sqlSession.update("blog.updateBlog", blogVo);
	}

}
