package com.lay.mybatis.mapper;

import com.lay.mybatis.entity.MerchantConfigEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2020/9/21 15:09
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020/9/21 lei.yue 1.0 create file
 */

public interface IMerchantConfigMapper {
    /**
     * #{}:参数占位符，即sql预编译，变量替换后，自动加上单引号
     * ${}:字符串替换，即sql拼接，变量替换后，不会加上单引号
     * 1.不论是单个参数，还是多个参数，一律都建议使用注解@Param("")
     * 2.能用 #{} 的地方就用 #{}，不用或少用 ${}
     * 3.表名作参数时，必须用 ${}。如：select * from ${tableName}
     * 4.order by 时，必须用 ${}。如：select * from t_user order by ${columnName}
     * 5.使用 ${} 时，要注意何时加或不加单引号，即 ${} 和 '${}'
     */

    @Select("select * from tbl_merchant_config limit 5")
    @Results(id = "MerchantConfigEntity",
    value = {
            @Result(column = "merchant_id",property = "merchantId"),
            @Result(column = "merchant_code",property = "merchantCode"),
            @Result(column = "merchant_name",property = "merchantName"),
            @Result(column = "begin_time",property = "beginTime"),
            @Result(column = "end_time",property = "endTime"),
            @Result(column = "deposit_scale",property = "depositScale"),
            @Result(column = "merchant_name",property = "merchant",one = @One(select = "com.yl.mybatis.mapper.IMerchantMapper.getMerchantInfo",fetchType = FetchType.EAGER))
    })
    List<MerchantConfigEntity> findAll();

    @Insert("insert into tbl_merchant_config(id,version,created_time,created_by,modified_by,modified_time,merchant_name) values(#{id},#{version},#{createdTime},#{createdBy},#{modifiedBy},#{modifiedTime},#{merchantName})")
    void insertMerchant(MerchantConfigEntity entity);

    @Update("update tbl_merchant_config set version = #{version},merchant_code = #{merchantCode} where id = #{id}")
    void updateMerchant(MerchantConfigEntity entity);

    @Delete("delete from tbl_merchant_config where id = #{id}")
    void deleteMerchant(String id);

    /**
     * @ResultMap 引用Results对结果集的封装(只可引用当前类中的Results)
     */
    @Select("select * from tbl_merchant_config where merchant_id = #{merchantId}")
    @ResultMap(value = {"MerchantConfigEntity"})
    List<MerchantConfigEntity> findMerchantById(@Param("merchantId") String merchantId);
}
