package com.own.web.chain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author liuChang
 * @date 2023/4/12 17:18
 * @describe
 */
@Data
@Accessors(chain = true)
public class TimeFilterDetailDto {

    /**
     * 执行配置 1:周期执行 2：单次执行 3:周期不执行：4：单次不执行
     */
    private Integer execConfig;
    /**
     * 任务频次
     */
    private Integer frequency;
    /**
     * 时段 1:全天 2:指定
     */
    private Integer period;

    /**
     * 日期枚举 {@link DateEnums}
     */
    private DateEnums dateEnums;
    /**
     * 具体日期 6.7
     */
    private String date;

    /**
     * 时段指定时间 7,8,9
     */
    private String periodTime;
    /**
     * 排序
     */
    private Integer sortNum;

    /**
     * 是否生效
     */
    private Boolean isEffect;

    /**
     * 最终结果
     */
    private Boolean result;
}
