package com.builder.common.utils.constant;

/**
 * 常量
 *
 * @author builder34
 * @email lcbiao34@gmail.com
 * @date 2017-11-15 21:23:52
 */
public class Constant {
	/** 超级管理员ID */
	public static final int SUPER_ADMIN = 1;

	public static final String UTF_8 = "UTF-8";

	/**
	 * 菜单类型
	 */
    public enum MenuType {
        /**
         * 模块
         */
        MODULE(0),
        /**
         * 目录
         */
    	CATALOG(1),
        /**
         * 菜单
         */
        MENU(2),
        /**
         * 按钮
         */
        BUTTON(3);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 定时任务状态
     *
     * @author builder34
     * @email lcbiao34@gmail.com
     * @date 2016年12月3日 上午12:07:22
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
    	NORMAL(0),
        /**
         * 暂停
         */
    	PAUSE(1);

        private int value;

        private ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        private CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
