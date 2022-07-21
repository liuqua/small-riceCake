package com.muyou.ricecake.service.impl;
import com.muyou.ricecake.vo.CmsPrefrenceArea;
import com.muyou.ricecake.vo.CmsPrefrenceAreaExample;
import com.muyou.ricecake.mapper.CmsPrefrenceAreaMapper;
import com.muyou.ricecake.service.CmsPrefrenceAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品优选管理Service实现类
 * Created by macro on 2018/6/1.
 */
@Service
public class CmsPrefrenceAreaServiceImpl implements CmsPrefrenceAreaService {
    @Autowired
    private CmsPrefrenceAreaMapper prefrenceAreaMapper;

    @Override
    public List<CmsPrefrenceArea> listAll() {
        return prefrenceAreaMapper.selectByExample(new CmsPrefrenceAreaExample());
    }
}
