package com.wind.entity;

import java.util.List;

public class PageBean {

	private int allRow; // 总记录数
	private int currentPage; // 当前页
	private boolean firstPage; // 首页
	private boolean hasNextPage; // 后一页
	private boolean hasPreviousPage; // 前一页
	private boolean lastPage; // 尾页
	private List list; // 要返回的某一页的记录列表
	private int offset; // 当前页开始记录
	private int pageSize; // 每页记录数
	private int totalPage; // 总页数
	private List count;

	public List getCount() {
		return count;
	}

	public void setCount(List count) {
		this.count = count;
	}

	public int getAllRow() {
		return allRow;
	}

	public void setAllRow(int allRow) {
		this.allRow = allRow;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public boolean isFirstPage() {
		return firstPage;
	}

	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public static int countCurrentPage(int page) {
		final int curPage = (page == 0 ? 1 : page);
		return curPage;
	}

	public static int countOffset(final int pageSize, final int currentPage) {
		final int offset = pageSize * (currentPage - 1);
		return offset;
	}

	public static int countTotalPage(final int pageSize, final int allRow) {
		int totalPage = (allRow % pageSize == 0) ? (allRow / pageSize) : (allRow / pageSize + 1);
		System.out.println("totalpage = " + totalPage);
		return totalPage;
	}
}
