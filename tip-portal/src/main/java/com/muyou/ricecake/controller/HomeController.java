package com.muyou.ricecake.controller;

import com.muyou.ricecake.api.CommonResult;
import com.muyou.ricecake.domain.HomeContentResult;
import com.muyou.ricecake.service.HomeService;
import com.muyou.ricecake.vo.CmsSubject;
import com.muyou.ricecake.vo.PmsProduct;
import com.muyou.ricecake.vo.PmsProductCategory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页内容管理Controller
 * Created by macro on 2019/1/28.
 */
@Controller
@Api(tags = "HomeController", description = "首页内容管理")
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @ApiOperation("首页内容信息展示")
    @RequestMapping(value = "/content", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<HomeContentResult> content() {
        HomeContentResult contentResult = homeService.content();
        return CommonResult.success(contentResult);
    }

    @ApiOperation("分页获取推荐商品")
    @RequestMapping(value = "/recommendProductList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProduct>> recommendProductList(@RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProduct> productList = homeService.recommendProductList(pageSize, pageNum);
        return CommonResult.success(productList);
    }

    @ApiOperation("获取首页商品分类")
    @RequestMapping(value = "/productCateList/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProductCategory>> getProductCateList(@PathVariable Long parentId) {
        List<PmsProductCategory> productCategoryList = homeService.getProductCateList(parentId);
        return CommonResult.success(productCategoryList);
    }

    @ApiOperation("根据分类获取专题")
    @RequestMapping(value = "/subjectList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CmsSubject>> getSubjectList(@RequestParam(required = false) Long cateId,
                                                         @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<CmsSubject> subjectList = homeService.getSubjectList(cateId,pageSize,pageNum);
        return CommonResult.success(subjectList);
    }

    @ApiOperation("分页获取人气推荐商品")
    @RequestMapping(value = "/hotProductList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProduct>> hotProductList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                         @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {
        List<PmsProduct> productList = homeService.hotProductList(pageNum,pageSize);
        return CommonResult.success(productList);
    }

    @ApiOperation("分页获取新品推荐商品")
    @RequestMapping(value = "/newProductList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProduct>> newProductList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                         @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {
        List<PmsProduct> productList = homeService.newProductList(pageNum,pageSize);
        return CommonResult.success(productList);
    }
}
