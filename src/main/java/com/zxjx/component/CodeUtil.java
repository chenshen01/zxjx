package com.zxjx.component;

import com.zxjx.entity.CodeSequence;
import com.zxjx.mapper.CodeSequenceMapper;
import com.zxjx.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * description
 *
 * @author liuzhixiang 2020/04/05 20:55
 */
@Component
@Transactional(rollbackFor = Exception.class)
public class CodeUtil {
    private static final String STU_CODE_PREFFRX = "s";
    private static final String TEA_CODE_PREFFRX = "t";
    private static final String ADM_CODE_PREFFRX = "a";
    private static final String ORDER_PREFFRX = "zxjx";
    @Autowired
    private CodeSequenceMapper mapper;

    private static CodeSequence codeSequence;

    private static CodeSequence orderSequence;

    /**
     * <p>
     *管理员账号
     * </p>
     *
     * @author liuzhixiang 2020/04/05 21:17
     */
    public String getAdminCode(){
        String adminCode = ADM_CODE_PREFFRX + this.formatCode(this.getCodeSequence());
        codeSequence.setCodeSequence(this.getCodeSequence() + 1);
        mapper.updateByPrimaryKey(codeSequence);
        return adminCode;
    }

    /**
     * <p>
     * 学号
     * </p>
     *
     * @author liuzhixiang 2020/04/05 21:17
     */
    public String getStudyCode(){
        String studyCode = STU_CODE_PREFFRX + this.formatCode(this.getCodeSequence());
        codeSequence.setCodeSequence(this.getCodeSequence() + 1);
        mapper.updateByPrimaryKey(codeSequence);
        return studyCode;
    }

    /**
     * <p>
     *教师账号
     * </p>
     *
     * @author liuzhixiang 2020/04/05 21:18
     */
    public String getTeacherCode(){
        String teacherCode = TEA_CODE_PREFFRX + this.formatCode(this.getCodeSequence());
        codeSequence.setCodeSequence(this.getCodeSequence() + 1);
        mapper.updateByPrimaryKey(codeSequence);
        return teacherCode;
    }

    private synchronized Long getCodeSequence(){
        List<CodeSequence> codeSequences = mapper.selectAll(CodeSequence.builder().build());
        codeSequence = codeSequences.get(0);
        return codeSequence.getCodeSequence();
    }

    private String formatCode(Long codeSequence){
        if (codeSequence.longValue() < 10) {
            return "00000" + codeSequence.longValue();
        } else if (codeSequence.longValue() < 100) {
            return "0000" + codeSequence.longValue();
        } else if (codeSequence.longValue() < 1000) {
            return "000" + codeSequence.longValue();
        } else if (codeSequence.longValue() < 10000) {
            return "00" + codeSequence.longValue();
        } else if (codeSequence.longValue() < 100000) {
            return "0" + codeSequence.longValue();
        } else if (codeSequence.longValue() < 100000) {
            return String.valueOf(codeSequence.longValue());
        } else {
            throw new RuntimeException("序列以到最大值请联系管理员");
        }
    }

    public synchronized String getOrderNumber(){
        List<CodeSequence> codeSequences = mapper.selectAll(CodeSequence.builder().build());
        orderSequence = codeSequences.get(1);
        String orderNumber = ORDER_PREFFRX + DateUtil.getCurrentDateTamp() + formatOrderNumber(orderSequence.getCodeSequence());
        orderSequence.setCodeSequence(orderSequence.getCodeSequence() + 1);
        mapper.updateByPrimaryKey(orderSequence);
        return orderNumber;
    }

    private String formatOrderNumber(Long orderNumber){
        if (orderNumber.longValue() < 10) {
            return "000" + orderNumber.longValue();
        } else if (orderNumber.longValue() < 100) {
            return "00" + orderNumber.longValue();
        } else if (orderNumber.longValue() < 1000) {
            return "0" + orderNumber.longValue();
        } else if (orderNumber.longValue() < 100000) {
            return String.valueOf(orderNumber.longValue());
        } else {
            throw new RuntimeException("序列以到最大值请联系管理员");
        }
    }
}
