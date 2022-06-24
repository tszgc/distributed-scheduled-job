package org.nina.dsj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.nina.dsj.model.DsjTask;

import java.util.List;

@Mapper
public interface DsjTaskMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dsj_task
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dsj_task
     *
     * @mbg.generated
     */
    int insert(DsjTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dsj_task
     *
     * @mbg.generated
     */
    int insertSelective(DsjTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dsj_task
     *
     * @mbg.generated
     */
    DsjTask selectByPrimaryKey(Integer id);

    DsjTask selectByCode(String code);

    List<DsjTask> selectPage();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dsj_task
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DsjTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dsj_task
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DsjTask record);
}