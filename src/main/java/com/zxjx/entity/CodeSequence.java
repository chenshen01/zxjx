package com.zxjx.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * description 序列号
 *
 * @author liuzhixiang 2020/04/05 20:59
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "zxjx_code_sequence")
public class CodeSequence implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "code_sequence")
    private Long codeSequence;
}
