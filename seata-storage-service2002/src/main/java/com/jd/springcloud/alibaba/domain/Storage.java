package com.jd.springcloud.alibaba.domain;

import lombok.Data;

/**
 * @author lijun
 * @create 2020-03-15 20:13
 */
@Data
public class Storage {

    private Long id;
    /**
     * 产品id
     */
    private Long productId;

    /**
     * 总库存
     */
    private Integer total;

    /**
     * 已用库存
     */
    private Integer used;

    /**
     * 剩余库存
     */
    private Integer residue;
}
