import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class ParkingDemo {

	final static Map parkingMap = new HashMap<Integer, String>();

	public static void main(String[] args) throws Exception {

		/*
		 * System.out.println("Welcome to advance parking system. Type 0 for exit");
		 * File file = new File("bin/file_input.txt");
		 */
		File file = null;
		if (args.length > 0) {
			String filename = args[0];
			file = new File(filename);
		}
		BufferedReader br = new BufferedReader(new FileReader(file));

		String selection;
		while ((selection = br.readLine()) != null) {
			System.out.println(selection);
			if (selection.equals("status")) {
				status();
			} else if (selection.startsWith("create_parking_lot")) {
				create_parking_lot(
						Integer.parseInt(selection.substring(selection.indexOf(" ") + 1, selection.length())));
			} else if (selection.startsWith("park")) {
				park(selection.substring(selection.indexOf(" "), selection.length()));
			} else if (selection.startsWith("leave")) {
				String[] val = selection.split(" ");
				leave(val[1], Integer.parseInt(val[2]));
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void create_parking_lot(int capacity) {
		for (int i = 1; i <= capacity; i++) {
			parkingMap.put(i, "");
		}
		System.out.println("Created parking lot with " + capacity + " slots");
	}

	@SuppressWarnings("unchecked")
	public static void park(String car_number) {
		boolean flag = false;
		Iterator it = parkingMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (pair.getValue().equals("")) {
				flag = true;
				parkingMap.put(pair.getKey(), car_number);
				System.out.println("Allocated slot number: " + pair.getKey());
				break;
			}
		}

		if (!flag) {
			System.out.println("Sorry, parking lot is full");
		}
	}

	@SuppressWarnings("unchecked")
	public static void leave(String car_number, int hours) {
		boolean flag = false;
		int charge = 10;
		if (hours > 2) {
			charge = charge + (hours - 2) * 10;
		}
		Iterator it = parkingMap.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (car_number.trim().equals(pair.getValue().toString().trim())) {
				parkingMap.put(pair.getKey(), "");
				flag = true;
				System.out.println("Registration number " + car_number + " with Slot Number " + pair.getKey()
						+ " is free with Charge " + charge);
				break;
			}
		}
		if (!flag) {
			System.out.println("registration number " + car_number + " not found.");
		}
	}

	@SuppressWarnings("unchecked")
	public static void status() {
		System.out.println("Slot No. Registration No.");
		parkingMap.forEach((k, v) -> {
			System.out.println(k + " " + v);

		});
	}

}
