package com.ohgiraffers.section03.remix.model;

import com.ohgiraffers.dto.MenuDTO;

import java.util.List;

public interface MenuMapper {
    
    List<MenuDTO> selectAllMenu();

    int deleteMenu(int code);

    int updateMenu(MenuDTO menu);

    MenuDTO selectMenuByCode(int code);

    int insertMenu(MenuDTO menu);

}
