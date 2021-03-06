package com.cafe24.jblog.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insertFirstCategory(String id) {
		return 1 == sqlSession.insert("category.insertFirstCategory", id);
	}

	public ArrayList<CategoryVo> getCategoryList(String id) {
		return (ArrayList)sqlSession.selectList("category.getBlogContent", id);
	}

	public ArrayList<CategoryVo> getAdminCategory(String id) {
		return (ArrayList)sqlSession.selectList("category.getAdminCategory", id);
	}

	public int deleteCategory(Long cateNo) {
		return sqlSession.delete("category.deleteCategory", cateNo);
	}

	public int insertCategory(CategoryVo categoryVo) {
		return sqlSession.insert("category.insertCategory", categoryVo);
	}

	public Long getFirstCategory(String id) {
		return sqlSession.selectOne("category.getFirstCategory", id);
	}

}
