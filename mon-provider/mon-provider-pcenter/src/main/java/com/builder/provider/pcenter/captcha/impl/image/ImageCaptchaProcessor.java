package com.builder.provider.pcenter.captcha.impl.image;

import com.builder.common.utils.JacksonUtil;
import com.builder.common.utils.R;
import com.builder.provider.pcenter.captcha.CaptchaGenerator;
import com.builder.provider.pcenter.captcha.CaptchaRepository;
import com.builder.provider.pcenter.captcha.impl.AbstractCaptchaProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * ImageCaptchaProcessor 图片验证码处理器
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-20 23:31:08
 */
@Component("imageCaptchaProcessor")
public class ImageCaptchaProcessor extends AbstractCaptchaProcessor<ImageCaptchaBean> {

    public ImageCaptchaProcessor(Map<String, CaptchaGenerator> generatorMap, CaptchaRepository captchaRepository) {
        super(generatorMap, captchaRepository);
    }

    @Override
    protected void send(ServletWebRequest request, ImageCaptchaBean captchaBean) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(captchaBean.getImage(), "JPEG", bos);
        R result = R.ok().setData(bos.toByteArray());

        HttpServletResponse response = request.getResponse();
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        response.getWriter().write(JacksonUtil.encode2(result));
    }
}
