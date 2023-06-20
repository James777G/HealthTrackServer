package com.healthtrack.calculator.mapper;

import com.healthtrack.calculator.annotation.Warning;
import com.healthtrack.calculator.pojo.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
@Warning(Warning.Type.DELETE_IN_FUTURE)
public interface ItemMapper {
    @Select("select * from itemtable where itemId = #{id}")
    Item getById(Integer id);
}
