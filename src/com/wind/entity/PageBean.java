package com.wind.entity;

import java.util.List;

public class PageBean {

	private int allRow; // �ܼ�¼��
	private int currentPage; // ��ǰҳ
	private boolean firstPage; // ��ҳ
	private boolean hasNextPage; // ��һҳ
	private boolean hasPreviousPage; // ǰһҳ
	private boolean lastPage; // βҳ
	private List list; // Ҫ���ص�ĳһҳ�ļ�¼�б�
	private int offset; // ��ǰҳ��ʼ��¼
	private int pageSize; // ÿҳ��¼��
	private int totalPage; // ��ҳ��
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
