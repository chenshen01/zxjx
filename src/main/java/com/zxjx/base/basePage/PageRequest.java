package com.zxjx.base.basePage;

import lombok.*;

/**
 * description 分页请求
 *
 * @author liuzhixiang 2020/04/10 22:13
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest {

    /**
     * 当前页码
     */
    private int pageNum;

    /**
     * 每页大小
     */
    private int pageSize;
}
