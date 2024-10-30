package com.ohgiraffers.section01;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.Date;

public interface Mapper {

    @Select("SELECT CURDATE() FROM DUAL")  //현재 날짜출력해줄 mysql에서 제공해주는 함수이다!
    Date selectSysDate();


}


