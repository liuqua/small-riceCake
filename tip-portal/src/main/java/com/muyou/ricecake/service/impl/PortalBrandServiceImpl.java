package com.muyou.ricecake.service.impl;

import com.github.pagehelper.PageHelper;
import com.muyou.ricecake.api.CommonPage;
import com.muyou.ricecake.dao.HomeDao;
import com.muyou.ricecake.mapper.PmsBrandMapper;
import com.muyou.ricecake.mapper.PmsProductMapper;
import com.muyou.ricecake.service.PortalBrandService;
import com.muyou.ricecake.vo.PmsBrand;
import com.muyou.ricecake.vo.PmsProduct;
import com.muyou.ricecake.vo.PmsProductExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 前台品牌管理Service实现类
 * Created by macro on 2020/5/15.
 */
@Service
public class PortalBrandServiceImpl implements PortalBrandService {
    @Autowired
    private HomeDao homeDao;
    @Autowired
    private PmsBrandMapper brandMapper;
    @Autowired
    private PmsProductMapper productMapper;

    @Override
    public List<PmsBrand> recommendList(Integer pageNum, Integer pageSize) {
        int offset = (pageNum - 1) * pageSize;
        return homeDao.getRecommendBrandList(offset, pageSize);
    }

    @Override
    public PmsBrand detail(Long brandId) {
        return brandMapper.selectByPrimaryKey(brandId);
    }

    @Override
    public CommonPage<PmsProduct> productList(Long brandId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andDeleteStatusEqualTo(0)
                .andBrandIdEqualTo(brandId);
        List<PmsProduct> productList = productMapper.selectByExample(example);
        return CommonPage.restPage(productList);
    }
}
