package com.tjoeun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.tjoeun.dto.ContentDTO;

public interface BoardMapper {
	
	// null 을 허용하는 column 에는 #{content_file, jdbcType=VARCHAR} 이렇게 해서
	// content_file 컬럼이 어떤 type 인지 명시적으로 기술해야 됨
	@SelectKey(statement="SELECT content_seq.nextval FROM dual", keyProperty="content_idx", before=true, resultType=int.class)   
	
	@Insert("insert into content_table(content_idx, content_subject, content_text, " +  
		      "content_file, content_writer_idx, content_board_idx, content_date) " + 
		      "values(#{content_idx}, #{content_subject}, #{content_text}, " + 
		          "#{content_file, jdbcType=VARCHAR}, #{content_writer_idx}, " +  
		          "#{content_board_idx}, sysdate)")
  void addContentInfo(ContentDTO writeContentDTO);
	
	// 게시판 번호를 parameter 에 전달하면
	// DB table 에서 게시판 이름 가져오기
	@Select("SELECT board_info_name " +
			    "FROM board_info_table " +
			    "WHERE board_info_idx=#{board_info_idx}")
	String getBoardInfoName(int board_info_idx);
	
	// board/main.jsp 화면이 보일 때
	// 회원이 입력한 글의 정보(글번호	제목	작성자	작성날짜)를	
	// DB table 에서 가져와서 보여주기
	@Select("SELECT c.content_idx, c.content_subject, u.user_name content_writer_name, "+
  			  "       to_char(c.content_date, 'YYYY-MM-DD') content_date "+
  			  "  FROM content_table c, user_table u "+
  			  " WHERE c.content_writer_idx = u.user_idx "+
  			  "   AND c.content_board_idx = #{board_info_idx} "+
  			  "ORDER BY c.content_idx desc")	
  List<ContentDTO> getContentList(int board_info_idx, RowBounds rowBounds);
	
	// 상세페이지에서 작성한 글 내용을 화면에 보여주기 위한 SQL
	// DB 에서 게시글의 정보를 가져올 때 content_writer_idx 도 가져와서
	// 현재 로그인한 회원에 정보(Session Scope 에 있음)의 uset_idx 와 비교해서
	// 같으면, 글쓴이와 로그인한 회원이 같은 사람임
	@Select("SELECT u.user_name content_writer_name, "+
			    "       to_char(c.content_date, 'YYYY-MM-DD') content_date, "+
			    "       c.content_subject, c.content_text, c.content_file, c.content_writer_idx "+
			    "FROM content_table c, user_table u "+
			    "WHERE c.content_writer_idx = u.user_idx "+
			    "  AND c.content_idx = #{content_idx} "+
			    "ORDER BY c.content_idx desc")
	ContentDTO getContentInfo(int content_idx);
	
	// 게시글 수정하기
	@Update("UPDATE content_table " +
			    "   SET content_subject = #{content_subject}, " +
			    "       content_text = #{content_text}, " +
			    "       content_file = #{content_file, jdbcType=VARCHAR} " +
			    " WHERE content_idx = #{content_idx}")
	void modifyContentInfo(ContentDTO modifyContentDTO);
	
	// 게시글 삭제하기
	@Delete("DELETE FROM content_table " +
			    " WHERE content_idx = #{content_idx}")
	void deleteContentInfo(int content_idx);
	
	// 각 게시판의 전체 게시글 개수 가져오기
	@Select("SELECT COUNT(*) FROM content_table " +
			    " WHERE content_board_idx = #{content_board_idx }")
	int getContentCount(int content_board_idx);
	
	
}







