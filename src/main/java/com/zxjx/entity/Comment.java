package com.zxjx.entity;

import lombok.*;
import org.springframework.data.relational.core.sql.In;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * description 评论
 *
 * @author liuzhixiang 2020/04/04 11:01
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "zxjx_comment")
public class Comment implements Serializable {
    public static final String GOOD = "好评";
    public static final String BAD = "差评";

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**
     * 评论的课程
     */
    @Column(name = "course_id")
    private Long courseId;

    /**
     * 评论内容
     */
    @Column(name = "description")
    private String description;

    /**
     * 评分
     */
    @Column(name = "score")
    private Integer score;

    /**
     * 评分状态（好评，差评）
     */
    @Column(name = "status")
    private String status;

    /**
     * 评论人
     */
    @Column(name = "comment_by")
    private String commentBy;
}
