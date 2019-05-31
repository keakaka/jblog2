package com.cafe24.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;

	public Boolean insertUser(UserVo userVo) {
		return 1 == sqlSession.insert("user.insertUser", userVo);
	}

	public UserVo getUser(UserVo userVo) {
		return sqlSession.selectOne("user.getUser", userVo);
	}

	public Boolean existId(String id) {
		Long count = sqlSession.selectOne("user.existId", id);
		return count == 0;
	}

	
	
}
