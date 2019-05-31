package com.cafe24.jblog.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<PostVo> getBlogContent(Long no) {
		return (ArrayList)sqlSession.selectList("post.getBlogContent", no);
	}

	public int getPostCount(Long no) {
		return sqlSession.selectOne("post.getPostCount", no);
	}


	public void updateCategoryNo(Map<String, Long> map) {
		sqlSession.update("post.updateCategoryNo", map);
	}

	public void insertPost(PostVo postVo) {
		sqlSession.insert("post.insertPost", postVo);
	}

	public ArrayList<PostVo> getPostList(Long postNo) {
		
		return (ArrayList)sqlSession.selectList("post.getPostList", postNo);
	}

	public ArrayList<PostVo> getTrueCtNoPostList(Long categoryNo) {
		return (ArrayList)sqlSession.selectList("post.getTrueCtNoPostList", categoryNo);
	}

	public PostVo getPost(Long postNo) {
		return sqlSession.selectOne("post.getPost", postNo);
	}

	public PostVo getTrueCtNoPost(Long categoryNo) {
		return sqlSession.selectOne("post.getTrueCtNoPost", categoryNo);
	}

	public PostVo getLatePost(String id) {
		return sqlSession.selectOne("post.getLatePost", id);
	}

}
