package com.exam.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.exam.vo.MemberVO;

public interface MemberMapper {

	@Select("SELECT COUNT(*) AS cnt FROM jspdb.member WHERE id = #{id}")
	public int countMemberById(String id);
	
	
	public void insertMember(MemberVO vo);
	
	@Select("SELECT * FROM jspdb.member WHERE id = #{id}")
	public MemberVO getMemberById(String id);
	
	//전체 회원정보 가져오기
	@Select("SELECT * FROM jspdb.member ORDER BY id ASC")
	public List<MemberVO> getMembers();
	
	//회원정보 수정하기
	@Update("UPDATE jspdb.member SET name=#{name}, age=#{age}, gender=#{gender}, email=#{email} WHERE id=#{id}")
	public void updateMember(MemberVO memberVO);
	
	//회원정보 삭제하기
	@Delete("DELETE FROM jspdb.member WHERE id =#{id}")
	public void deleteMember(String id);
	
	
}
