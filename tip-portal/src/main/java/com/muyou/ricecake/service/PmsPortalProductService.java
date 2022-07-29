package com.muyou.ricecake.service;


import com.muyou.ricecake.domain.PmsPortalProductDetail;
import com.muyou.ricecake.domain.PmsProductCategoryNode;
import com.muyou.ricecake.vo.PmsProduct;

import java.util.List;

/**
 * 前台商品管理Service
 * Created by macro on 2020/4/6.
 */
public interface PmsPortalProductService {
    /**
     * 综合搜索商品
     */
    List<PmsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort);

    /**
     * 以树形结构获取所有商品分类
     */
    List<PmsProductCategoryNode> categoryTreeList();

    /**
     * 获取前台商品详情
     */
    PmsPortalProductDetail detail(Long id);
}
