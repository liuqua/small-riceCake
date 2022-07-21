package com.muyou.ricecake.service;

import com.muyou.ricecake.vo.OmsOrderReturnApply;
import com.muyou.ricecake.vo.OmsOrderReturnApplyResult;
import com.muyou.ricecake.vo.OmsReturnApplyQueryParam;
import com.muyou.ricecake.vo.OmsUpdateStatusParam;

import java.util.List;

/**
 * 退货申请管理Service
 * Created by macro on 2018/10/18.
 */
public interface OmsOrderReturnApplyService {
    /**
     * 分页查询申请
     */
    List<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum);

    /**
     * 批量删除申请
     */
    int delete(List<Long> ids);

    /**
     * 修改指定申请状态
     */
    int updateStatus(Long id, OmsUpdateStatusParam statusParam);

    /**
     * 获取指定申请详情
     */
    OmsOrderReturnApplyResult getItem(Long id);
}
