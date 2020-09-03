package com.zxjx.entity;

import com.zxjx.base.BaseMassage;
import lombok.*;
/**
 * description
 *
 * @author liuzhixiang 2020/04/09 23:16
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Result {
    public static final String SUCCESS_CODE = "200";
    public static final String FAIL_CODE = "500";
    public static final String WAIT_CODE = "501";

    private String message;

    private Object result;

    private String code;

    // private String role;

    public Result(Object result){
        this.message = BaseMassage.QUERY_SUCCESS;
        this.code = Result.SUCCESS_CODE;
        this.result = result;
    }
}
