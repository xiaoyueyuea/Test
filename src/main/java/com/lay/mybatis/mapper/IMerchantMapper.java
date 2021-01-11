package com.lay.mybatis.mapper;

import com.lay.mybatis.entity.MerchantEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2020/9/22 14:20
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020/9/22 lei.yue 1.0 create file
 */

public interface IMerchantMapper {

    //一对多关系(一个商户可对应多个配置)
    @Select("select * from tbl_merchant where name = #{merchantName}")
    @Results(id = "merchantEntity",
    value = {
            @Result(column = "code",property = "merchantCode"),
            @Result(column = "name",property = "merchantName"),
            @Result(column = "id",property = "merchantConfigEntityList",many = @Many(select = "com.yl.mybatis.mapper.IMerchantConfigMapper.findMerchantById",fetchType = FetchType.EAGER))
    })
    MerchantEntity getMerchantInfo(@Param("merchantName") String merchantName);
}
