package test;

import java.io.File;

import org.junit.Test;

import src.ParkingDemo;

public class ParkingDemoTest {

	@Test
	public void testParking() throws Exception
	{
		String[] args = {"bin/file_input.txt"};
		ParkingDemo parkingDemo= new ParkingDemo();
		parkingDemo.main(args);
		
		
		
	}
}
