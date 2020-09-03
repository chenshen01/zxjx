package com.zxjx.controller;

import com.zxjx.entity.Result;
import com.zxjx.service.IAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * description
 *
 * @author liuzhixiang 2020/04/03 22:55
 */
@RestController
public class AttachmentController {
    @Autowired
    private IAttachmentService service;

    @GetMapping("/zxjx/file/download")
    public Result download(HttpServletResponse response,String fileName){
        service.downloadResume(response, fileName);
        return null;
    }
}
