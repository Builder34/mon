package com.builder.common.utils;

import com.baomidou.mybatisplus.plugins.Page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 *
 * @author Builder34
 * @email lcbiao34@gmail.com
 * @date 2018年-07-31 12:59:00
 */
public class PageUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	//总记录数
	private int totalCount;
	//每页记录数
	private int size;
	//总页数
	private int totalPage;
	//当前页数
	private int current;
	//列表数据
	private List<?> records;

	/**
	 * 分页
	 * @param records     列表数据
	 * @param totalCount  总记录数
	 * @param size    每页记录数
	 * @param current    当前页数
	 */
	public PageUtils(List<?> records, int totalCount, int size, int current) {
		this.records = records;
		this.totalCount = totalCount;
		this.size = size;
		this.current = current;
		this.totalPage = (int)Math.ceil((double)totalCount/size);
	}

	/**
	 * 分页
	 */
	public PageUtils(Page<?> page) {
		this.records = page.getRecords();
		this.totalCount = (int)page.getTotal();
		this.size = page.getSize();
		this.current = page.getCurrent();
		this.totalPage = (int)page.getPages();
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


	public int getTotalPage() {
		return totalPage;
	}

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

    public List<?> getRecords() {
        return records;
    }

    public void setRecords(List<?> records) {
        this.records = records;
    }
}
