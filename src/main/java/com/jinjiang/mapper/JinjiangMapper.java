package com.jinjiang.mapper;

import com.jinjiang.pojo.Jinjiang;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: xiangchao
 * @date: 2019/12/21 4:11 下午
 */
@Mapper
public interface JinjiangMapper {

    int saveJinJiang(Jinjiang jinjiang);    // 新增锦江卡

}
