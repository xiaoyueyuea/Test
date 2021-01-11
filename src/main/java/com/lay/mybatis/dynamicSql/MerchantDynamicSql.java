package com.lay.mybatis.dynamicSql;

import com.lay.mybatis.entity.MerchantConfigEntity;
import org.apache.ibatis.jdbc.SQL;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2020/9/23 10:05
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020/9/23 lei.yue 1.0 create file
 */

//动态sql
public class MerchantDynamicSql {

    public String getConfigByCondition(MerchantConfigEntity entity){
        return new SQL(){
            {
                SELECT("*");
                FROM("tbl_merchant_config");
                if(entity.getStatus() == 0){
                    WHERE("deposit_scale = #{depositScale}");
                }else {
                    WHERE("version = #{version}");
                }
            }
        }.toString();
    }
}
