package com.tjoeun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.tjoeun.dto.BoardInfoDTO;

public interface TopMenuMapper {
	
  @Select("SELECT * FROM board_info_table ORDER BY board_info_idx")
  List<BoardInfoDTO> getTopMenuList();
  
  
}
