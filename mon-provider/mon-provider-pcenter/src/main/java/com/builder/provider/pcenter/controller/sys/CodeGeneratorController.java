package com.builder.provider.pcenter.controller.sys;


import com.builder.common.utils.PageUtils;
import com.builder.common.utils.R;
import com.builder.common.utils.constant.StatusEnum;
import com.builder.provider.pcenter.service.CodeGeneratorService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @CreateTime 2018-09-13 14:16:02
 * @Description 代码生成器
 * @Contactemail lcbiao34@gmail.com
 * @Author builder34
 */
@Controller
@RequestMapping("/sys/code")
public class CodeGeneratorController {
    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @ResponseBody
    @RequestMapping("/list")
    public R list(@RequestBody Map<String, Object> params) {
        PageUtils page = codeGeneratorService.queryTable(params);
        return R.ok().setData(page);
    }
    @ResponseBody
    @RequestMapping("/table")
    public R table(@RequestBody Map<String, String> params) {
        String tableName = params.get("tableName");
        if(StringUtils.isBlank(tableName)){
            return R.error(StatusEnum.PARAM_MISS.getStatus(), StatusEnum.PARAM_MISS.getMessage()+": tableName");
        }
        Map<String, String> data = codeGeneratorService.queryTableByName(tableName);
        return R.ok().setData(data);
    }
    @ResponseBody
    @RequestMapping("/column")
    public R column(@RequestBody Map<String, String> params) {
        String tableName = params.get("tableName");
        if(StringUtils.isBlank(tableName)){
            return R.error(StatusEnum.PARAM_MISS.getStatus(), StatusEnum.PARAM_MISS.getMessage()+": tableName");
        }
        List<Map<String, String>> data = codeGeneratorService.queryColumns(tableName);
        return R.ok().setData(data);
    }

    /**
     * 生成代码
     */
    @RequestMapping("/generator")
    public void code(@RequestBody Map<String, Object> params, HttpServletResponse response) throws IOException {
        String moduleName = (String) params.get("moduleName");
        String[] tableNames = (String[])params.get("tableNames");

        byte[] data = codeGeneratorService.generatorCode(moduleName, tableNames);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"mon-code.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }
}
