package com.catdog.web.lambda;

import com.catdog.web.usr.User;

public class GenericTest {
	static class Box<T> {
		T item;
		void setItem(T item) {this.item = item;}
		T getItem() {return item;}
	}
	public static void main(String[] args) {
		GenericTest s = new GenericTest();
		GenericTest.Box<String> s2 = new GenericTest.Box<>();
		GenericTest.Box<String> s3 = new GenericTest.Box<>();
		GenericTest.Box<User> ubox = new GenericTest.Box<>();
	}
}
