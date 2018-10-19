package com.builder.provider.pcenter.controller.sys;

import com.builder.common.entity.pcenter.SysUserEntity;
import com.builder.common.utils.R;
import com.builder.common.utils.constant.StatusEnum;
import com.builder.provider.pcenter.form.SysLoginForm;
import com.builder.provider.pcenter.service.SysUserService;
import com.builder.provider.pcenter.service.SysUserTokenService;
import com.builder.provider.pcenter.util.ShiroUtils;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 系统登录接口
 * @CreateTime 2018-08-10 11:24:28
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@RestController
@RequestMapping("/")
public class SysLoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysLoginController.class);

    //kaptcha验证码
    @Autowired
    private Producer producer;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;

    @RequestMapping("captcha.jpg")
    public void captcha(HttpServletResponse response) throws IOException{
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        //生成图片
        BufferedImage image = producer.createImage(text);
        LOGGER.debug("========captcha text: "+text);
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 登录
     * */
    @PostMapping(value = "login")
    public R login(@RequestBody SysLoginForm form) {
        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if(!form.getCaptcha().equalsIgnoreCase(kaptcha)){
            return R.error(StatusEnum.CAPTCHA_ERR.getStatus(), StatusEnum.CAPTCHA_ERR.getMessage());
        }
        Map<String,Object> params = new HashMap<>();
        params.put("username",form.getUserName());
        List<SysUserEntity> userEntityList = sysUserService.selectByMap(params);
        if(userEntityList.size() == 0){
            //不直接说明是用户名错误，模糊说明是为了安全
            return R.error("账号或密码错误");
        }
        //用户信息
        SysUserEntity user = userEntityList.get(0);
        String encryptPwd = new Sha256Hash(form.getPassword(), user.getSalt()).toHex();
        if(!user.getPassword().equals(encryptPwd)){
            return R.error("账号或密码错误");
        }
        //账号锁定
        if(user.getStatus() == 0){
            return R.error("账号已被锁定,请联系管理员");
        }
        R r = sysUserTokenService.createToken(user.getUserId());
        return r;
    }
    @PostMapping(value = "logout")
    public R logout(){
        ShiroUtils.logout();
        return R.ok();
    }
}
