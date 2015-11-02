package com.amiitnside.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Coll {

	private static List<String> list = new ArrayList<>();

	// private String name = "AMIT";

	public static void main(String[] args) {
		list.add("AMIT");
		list.add("ANIT");
		list.forEach((final String name) -> System.out.println(name));
		list.forEach(System.out::println);
		list.stream().map(name -> name.toLowerCase())
				.forEach(name -> System.out.print(name));
		list.stream().map(String::toLowerCase)
				.forEach(name -> System.out.println(name));
		Predicate<? super String> predicate = name -> name.startsWith("A");
		list.stream().filter(predicate).collect(Collectors.toList())
				.forEach(name -> System.out.print(name));

		final Function<String, Predicate<String>> startWithLetter = letter -> name -> name
				.startsWith(letter);
		list.stream().filter(startWithLetter.apply("A"))
				.forEach(name -> System.out.println(name));

		pickName(list, "B");

		System.out.println(list.stream().mapToInt(name -> name.length()).sum());

		final Optional<String> longestName = list.stream().reduce(
				(name1, name2) -> (name1.length() >= name2.length()) ? name1
						: name2);
		
		longestName.ifPresent(name -> System.out.print("Found "+name));
		System.out.println(String.join(",  ", list));
		System.out.println(list.stream().map(String::toLowerCase).collect(Collectors.joining(", ")));
		
	}

	public static Predicate<String> checkStartLetter(final String letter) {
		return name -> name.startsWith(letter);
	}

	public static void pickName(final List<String> names, final String letter) {
		final Optional<String> foundName = names.stream()
				.filter(name -> name.startsWith(letter)).findFirst();
		System.out.println(String.format("%s --- %s",
				foundName.orElse("Nothing found"), letter));
		foundName.ifPresent(name -> name.charAt(0));
	}

}
