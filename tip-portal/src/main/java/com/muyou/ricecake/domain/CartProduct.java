package com.muyou.ricecake.domain;


import com.muyou.ricecake.vo.PmsProduct;
import com.muyou.ricecake.vo.PmsProductAttribute;
import com.muyou.ricecake.vo.PmsSkuStock;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 购物车中带规格和SKU的商品信息
 * Created by macro on 2018/8/2.
 */
@Getter
@Setter
public class CartProduct extends PmsProduct {
    @ApiModelProperty("商品属性列表")
    private List<PmsProductAttribute> productAttributeList;
    @ApiModelProperty("商品SKU库存列表")
    private List<PmsSkuStock> skuStockList;
}
