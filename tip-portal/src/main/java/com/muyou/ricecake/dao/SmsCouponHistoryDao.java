package com.muyou.ricecake.dao;


import com.muyou.ricecake.domain.SmsCouponHistoryDetail;
import com.muyou.ricecake.vo.SmsCoupon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会员优惠券领取记录管理自定义Dao
 * Created by macro on 2018/8/29.
 */
public interface SmsCouponHistoryDao {
    /**
     * 获取优惠券历史详情
     */
    List<SmsCouponHistoryDetail> getDetailList(@Param("memberId") Long memberId);

    /**
     * 获取指定会员优惠券列表
     */
    List<SmsCoupon> getCouponList(@Param("memberId") Long memberId, @Param("useStatus")Integer useStatus);
}
