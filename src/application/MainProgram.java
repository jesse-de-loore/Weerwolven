package application;

import java.util.Date;
import java.util.List;

import persistency.CustomerMapper;

public class MainProgram {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CustomerMapper cm = CustomerMapper.getInstance();
		
		long start1 = (new Date()).getTime();
		List<String> customerNames = cm.getCustomerNames();
		long end1 = (new Date()).getTime();
		
		for (String name: customerNames) {
			System.out.print(name + "|");
		}
		System.out.println();
		
		long start2 = (new Date()).getTime();
		List<String> customerNames2 = cm.getCustomerNames();
		long end2 = (new Date()).getTime();
		
		for (String name: customerNames2){
			System.out.print(name + "|");
		}
		System.out.println();
		
		System.out.println("timings:");
		System.out.println(end1 - start1);
		System.out.println(end2 - start2);
	}

}
