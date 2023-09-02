package com.luv2code.testdb;

import java.util.Date;
import java.util.TimeZone;

public class timetest {

	public static void main(String[] args) {
		TimeZone zone = TimeZone.getDefault();
        System.out.println(zone.getDisplayName());
        System.out.println(zone.getID());
        Date date = new Date();
        System.out.println(date);

	}

}
