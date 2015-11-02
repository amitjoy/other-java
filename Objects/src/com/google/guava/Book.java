package com.google.guava;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

public class Book implements Comparable<Book> {

	private String name;
	private String author;
	private int no_of_pages;

	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				.add("name", this.name).add("author", this.author)
				.add("pages", this.no_of_pages).toString();
	}

	public Book(String name, String author, int no_of_pages) {
		super();
		this.name = name;
		this.author = author;
		this.no_of_pages = no_of_pages;
	}

	@Override
	public int compareTo(Book o) {
		return ComparisonChain.start().compare(this.no_of_pages, o.no_of_pages)
				.result();
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(name, author, no_of_pages);
	}

}
