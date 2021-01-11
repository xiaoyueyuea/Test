package com.lay.mybatis.mapper;

import com.lay.mybatis.dynamicSql.MerchantDynamicSql;
import com.lay.mybatis.entity.MerchantConfigEntity;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2020/9/23 10:04
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020/9/23 lei.yue 1.0 create file
 */
public interface IDynamicMapper {

    @SelectProvider(type = MerchantDynamicSql.class,method = "getConfigByCondition")
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
    List<MerchantConfigEntity> getConfigsByCondition(MerchantConfigEntity entity);
}
