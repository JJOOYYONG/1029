package com.exam.dao;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.exam.dao.mapper.MemberMapper;
import com.exam.vo.MemberVO;

public class MemberDao {

	private static final MemberDao instance = new MemberDao();
	
	
	public static MemberDao getInstance() {
		return instance;
	}
	
	private MemberDao() {	
	}

	
	public boolean isIdDuplicated(String id) {
		//중복이면 true, 아니면 false
		boolean isIdDuplicated = false;
		
		//Connection 가져오기
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		//Interface를 구현한 Mapper객체를 만들어서 리턴함
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		//맴버 수 도출
		int count = mapper.countMemberById(id);
		
		if(count>0) {
			isIdDuplicated = true;
		}
		
		//JDBC 자원 닫기
				sqlSession.close();
		
		return isIdDuplicated;
		
		
	} //0827_아이디중복체크
	
	
	//맴버추가
	public void insertMember(MemberVO vo) {
		
		//Connection 가져오기
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		//Interface를 구현한 Mapper객체를 만들어서 리턴함
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		//회원가입(추가)
		mapper.insertMember(vo);
		//커밋
		sqlSession.commit();
		//JDBC 자원 닫기
		sqlSession.close();
		
	} // insertMember method
	
	
	
	//id 값에 대한 맴버 가져오기
	public MemberVO getMember(String id) {
		
		//Connection 가져오기
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		//Interface를 구현한 Mapper객체를 만들어서 리턴함
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		
		//회원정복 가져오기
		MemberVO memberVO = mapper.getMemberById(id);
		
		//JDBC 자원 닫기
		sqlSession.close();
		
		
		return memberVO;
	} // getMember method
	
	
	
	//유저있는지 확인 
	public int userCheck(String id, String passwd) {
		int check = -1; // -1 아이디 불일치. 0 패스워드 불일치. 1 일치함
		
		//Connection 가져오기
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		//Interface를 구현한 Mapper객체를 만들어서 리턴함
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
				
		//회원정복 가져오기
		MemberVO memberVO = mapper.getMemberById(id);
		if(memberVO != null) {
			if(passwd.equals(memberVO.getPasswd())) {
				check=1;
			}else {
				check=0;
			}
		}else {
			check=-1;
			
		}
		
		//JDBC 자원 닫기
		sqlSession.close();
		
		
		
		return check;
	} // userCheck method
	
	
	// 전체회원정보 가져오기
	public List<MemberVO> getMembers() {
		
		//Connection 가져오기
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		//Interface를 구현한 Mapper객체를 만들어서 리턴함
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		//전체회원정보 가져오기
		List<MemberVO> list = mapper.getMembers();
		
		//JDBC 자원 닫기
		sqlSession.close();
		
		
		return list;
	} // getMembers method
	
	
	// 회원정보 수정하기 메소드
	// 매개변수 memberVO에 passwd필드는 수정의 대상이 아니라
	// 본인 확인 용도로 사용
	public void updateMember(MemberVO memberVO) {
		
		
		//Connection 가져오기
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		//Interface를 구현한 Mapper객체를 만들어서 리턴함
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		//회원정보 수정하기
		mapper.updateMember(memberVO);
		
		//커밋
		sqlSession.commit();
		//JDBC 자원 닫기
		sqlSession.close();
		
		
		
	} // updateMember method
	
	
	
	//회원정보 삭제
	public void deleteMember(String id) {
		
		//Connection 가져오기
		SqlSession sqlSession = DBManager.getSqlSessionFactory().openSession();
		//Interface를 구현한 Mapper객체를 만들어서 리턴함
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		//회원정보 수정하기
		mapper.deleteMember(id);;
		
		//커밋
		sqlSession.commit();
		//JDBC 자원 닫기
		sqlSession.close();
		
		
		
	}// deletemember
	
	
	
	

} // class MemberDao



