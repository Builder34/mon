package com.builder.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/***
 * JSON转换工具类
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-01 11:14:26
 * */
public class JacksonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JacksonUtil.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    /** 默认日期时间格式 */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /** 默认日期格式 */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /** 默认时间格式 */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    static {
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //设置不输出值为 null 的属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        //设置时间的转换配置, 支持LocalDateTime
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)));
        javaTimeModule.addSerializer(LocalDate.class,
                new LocalDateSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)));
        javaTimeModule.addSerializer(LocalTime.class,
                new LocalTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)));
        javaTimeModule.addDeserializer(LocalTime.class,
                new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));
        objectMapper.registerModule(javaTimeModule).registerModule(new ParameterNamesModule());

    }

    /**
     * 将JSON字符串根据指定的Class反序列化成Java对象。
     *
     * @param json      JSON字符串
     * @param pojoClass Java对象Class
     * @return 反序列化生成的Java对象
     * @throws Exception 如果反序列化过程中发生错误，将抛出异常
     */
    public static <T> T decode(String json, Class<T> pojoClass)
            throws Exception {
        try {
            return objectMapper.readValue(json, pojoClass);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * 将JSON字符串根据指定的Class反序列化成Java对象(转换过程中出现异常则返回null对象)
     *
     * @param json      JSON字符串
     * @param pojoClass Java对象Class
     * @return 反序列化生成的Java对象
     * */
    public static <T> T decode2(String json, Class<T> pojoClass) {
        try {
            return objectMapper.readValue(json, pojoClass);
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    /**
     * 将JSON字符串根据指定的Class反序列化成Java对象。
     *
     * @param json      JSON字符串
     * @param reference 类型引用
     * @return 反序列化生成的Java对象
     * @throws Exception 如果反序列化过程中发生错误，将抛出异常
     */
    public static <T> Object decode(String json, TypeReference<T> reference) throws Exception {
        try {
            return objectMapper.readValue(json, reference);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 将JSON字符串根据指定的Class反序列化成Java对象。
     *
     * @param inputStream 输入字符流
     * @param reference   类型引用
     * @return 反序列化生成的Java对象
     * @throws Exception 如果反序列化过程中发生错误，将抛出异常
     */
    public static <T> T decode(InputStream inputStream, Class<T> reference) throws Exception {
        try {
            return objectMapper.readValue(inputStream, reference);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 将Java对象序列化成JSON字符串。
     *
     * @param obj 待序列化生成JSON字符串的Java对象
     * @return JSON字符串
     * @throws Exception 如果序列化过程中发生错误，将抛出异常
     */
    public static String encode(Object obj) throws Exception {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * 将Java对象序列化成JSON字符串(转换过程中出现异常则返回空对象json串"{}")
     *
     * @param obj 待序列化生成JSON字符串的Java对象
     * @return JSON字符串
     */
    public static String encode2(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            LOGGER.error("",e);
        }
        return "{}";
    }

    /**
     * 将Java对象序列化成JSON字符串。
     *
     * @param obj 待序列化生成JSON字符串的Java对象
     * @return JSON字符串
     * @throws Exception 如果序列化过程中发生错误，将抛出异常
     */
    public static void encode(OutputStream outputStream, Object obj) throws Exception {
        try {
            objectMapper.writeValue(outputStream, obj);
        } catch (Exception e) {
            throw e;
        }
    }

}
