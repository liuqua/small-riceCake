package com.muyou.ricecake.service;


import com.muyou.ricecake.vo.SmsFlashPromotionSession;
import com.muyou.ricecake.vo.SmsFlashPromotionSessionDetail;

import java.util.List;

/**
 * 限时购场次管理Service
 * Created by macro on 2018/11/16.
 */
public interface SmsFlashPromotionSessionService {
    /**
     * 添加场次
     */
    int create(SmsFlashPromotionSession promotionSession);

    /**
     * 修改场次
     */
    int update(Long id, SmsFlashPromotionSession promotionSession);

    /**
     * 修改场次启用状态
     */
    int updateStatus(Long id, Integer status);

    /**
     * 删除场次
     */
    int delete(Long id);

    /**
     * 获取详情
     */
    SmsFlashPromotionSession getItem(Long id);

    /**
     * 根据启用状态获取场次列表
     */
    List<SmsFlashPromotionSession> list();

    /**
     * 获取全部可选场次及其数量
     */
    List<SmsFlashPromotionSessionDetail> selectList(Long flashPromotionId);
}