package com.devcru.journowatch.api.objects;

/**
 * Created by Monitored on 12/27/2015. Journo model class (POJO)
 */

public class Journo {

	private static String name;
	private static String[] publications;
	private static String status; // enum?
	private static String bio;
	private static int rating; // (0-100 scale)

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		Journo.name = name;
	}

	public static String[] getPublications() {
		return publications;
	}

	public static void setPublications(String[] publications) {
		Journo.publications = publications;
	}

	public static String getStatus() {
		return status;
	}

	public static void setStatus(String status) {
		Journo.status = status;
	}

	public static String getBio() {
		return bio;
	}

	public static void setBio(String bio) {
		Journo.bio = bio;
	}

	public static int getRating() {
		return rating;
	}

	public static void setRating(int rating) {
		Journo.rating = rating;
	}

}
