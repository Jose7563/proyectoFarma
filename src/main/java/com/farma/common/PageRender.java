package com.farma.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T>{
	
	
	private String url;
	private Page<T> page;
	
	private  int totalPages; 
	private  int numElementByPage;
	private int pageActual;
	
	private List<PageItem> pages; 
	
	public PageRender(String url, Page<T> page) {
		
		this.url = url;
		this.page = page;
		this.pages= new ArrayList<PageItem>();
		
		
		numElementByPage = page.getSize();
		totalPages= page.getTotalPages();
		pageActual= page.getNumber() +1;
		int start, end;
		if (totalPages <= numElementByPage) {
			start= 1;
			end= totalPages;
			
		}else {
			if(pageActual <= numElementByPage/2) {
				start=1;
				end= numElementByPage;
			}else if(pageActual >=totalPages - numElementByPage/2) {
				start= totalPages - numElementByPage +1;
				end= numElementByPage; 
			}else {
				start= pageActual -numElementByPage/2; 
				end = numElementByPage; 
			}
		}
		
		for(int i=0; i< end; i++) {
			pages.add(new PageItem(start +i ,pageActual == start +i));
			
		}
		
	}

	public String getUrl() {
		return url;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getPageActual() {
		return pageActual;
	}

	public List<PageItem> getPages() {
		return pages;
	}
	
	public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	public boolean isHasNext() {
		return page.hasNext();
	}
	
	public boolean isHasPrevious() {
		return page.hasPrevious();
	}
	
	
	
	

}
