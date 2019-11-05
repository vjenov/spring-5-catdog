package com.catdog.web.test;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int totalCount = 31;
		int pageCount = (totalCount%5==0)?totalCount/5:(totalCount/5)+1;
		System.out.println(pageCount);
		totalCount = 30;
		pageCount = (totalCount%5==0)?totalCount/5:(totalCount/5)+1;
		System.out.println(pageCount);
	}

}
