package com.zxjx.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * description 文件
 *
 * @author liuzhixiang 2020/04/03 22:44
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "zxjx_attachment")
public class Attachment implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**
     * 所属文件所属班级
     */
    @Column(name = "class_id")
    private Long classId;

    /**
     * 上传者
     */
    @Column(name = "upload_by")
    private Long uploadBy;

    /**
     * 上传时间
     */
    @Column(name = "upload_date")
    private Date uploadDate;

    /**
     * 文件名字
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 文件上传路径
     */
    @Column(name = "path")
    private String path;

    /**
     * 文件存储路径
     */
    @Column(name = "url")
    private String url;
}
