package com.lay.mybatis;

import com.lay.mybatis.entity.MerchantConfigEntity;
import com.lay.mybatis.entity.MerchantEntity;
import com.lay.mybatis.mapper.IDynamicMapper;
import com.lay.mybatis.mapper.IMerchantConfigMapper;
import com.lay.mybatis.mapper.IMerchantMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2020/9/21 15:01
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020/9/21 lei.yue 1.0 create file
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class mybatisTest {
    @Autowired
    private IMerchantConfigMapper configMapper;

    @Autowired
    private IMerchantMapper merchantMapper;

    @Autowired
    private IDynamicMapper dynamicMapper;

    @Test
    public void doSimple(){
        //查询所有
//        List<MerchantConfigEntity> configEntityList = mapper.findAll();
//        configEntityList.stream().map(m -> m.getMerchantCode() + "：" + m.getMerchantName()).forEach(System.out::println);

        //插入一条
//        MerchantConfigEntity entity = new MerchantConfigEntity();
//        entity.setId("1");
//        entity.setCreatedBy("lay");
//        entity.setCreatedTime(new Date());
//        entity.setVersion(1);
//        entity.setModifiedBy("lay");
//        entity.setModifiedTime(new Date());
//        entity.setMerchantName("hhhhh");
//        mapper.insertMerchant(entity);

        //更新数据
//        MerchantConfigEntity entity1 = new MerchantConfigEntity();
//        entity1.setId("1");
//        entity1.setVersion(3);
//        entity1.setMerchantCode("eeee");
//        mapper.updateMerchant(entity1);

        //删除数据
//        mapper.deleteMerchant("1");

        //根据条件查询
        List<MerchantConfigEntity> configEntityList1 = configMapper.findMerchantById("ff9c20eb-b7a0-48ae-bf28-06c4d7a6519b");
        configEntityList1.stream().map(m -> m.getMerchantCode() + "：" + m.getMerchantName() + " " + m.getVersion()).limit(5).forEach(System.out::println);
    }

    @Test
    public void doComplexMappingQuery(){

        //通过name查询商户信息以及存在的所有配置(一对多)
        MerchantEntity merchant = merchantMapper.getMerchantInfo("乐山天姿医疗美容诊所");
        List<MerchantConfigEntity> configEntityList = merchant.getMerchantConfigEntityList();
        System.out.println(merchant.getMerchantCode() + " " + merchant.getMerchantName() + " 配置如下：");
        configEntityList.forEach(f -> {
            System.out.println(f.getBeginTime() + " " + f.getEndTime() + " " + f.getDepositScale());
        });

        //查询商户配置以及对应的商户(一对一：一个配置只对应一个商户)
        List<MerchantConfigEntity> configEntityList1 = configMapper.findAll();
        configEntityList1.forEach(f -> {
            System.out.println(f.getId() + "：" + f.getMerchant());
        });
    }

    @Test
    public void doDynamicSqlQuery(){
        MerchantConfigEntity configEntity = new MerchantConfigEntity();
        configEntity.setVersion(0);
        configEntity.setDepositScale("5");
        configEntity.setStatus(1);
        List<MerchantConfigEntity> configEntityList = dynamicMapper.getConfigsByCondition(configEntity);
        configEntityList.forEach(f -> {
            System.out.println(f.getVersion() + " " + f.getBeginTime() + " " + f.getEndTime() + " " + f.getDepositScale());
        });
    }
}
