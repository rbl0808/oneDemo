package com.example.onedemo.model;

import lombok.Data;

/**
 * 物流状态变更轨迹
 * @author : rbl
 * @date : 2023/5/12
 */
@Data
public class Express {

    /**
     * 物流状态码
     */
    private String freightStateName;

    /**
     * 物流轨迹变更时间
     */
    private String stateDate;

    /**
     * 物流轨迹描述信息
     */
    private String remarks;

    private String attrTypeList;
}
