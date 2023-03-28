package no.hvl.dat102;

import java.util.Random;

public class Main {
	public static void main(String[] args) {

		Integer[] tab = { 7, 4, 1, 9, 5, 2 };
		
		
		Random tilfeldig = new Random();
		int n = 75003;
		

		Integer[] a = new Integer[n];

		for (int i = 0; i < n; i++) {
			a[i] = tilfeldig.nextInt(75003) + 1;
		}

		
		long startTime = System.nanoTime();
		System.out.println();
		System.out.println("Sort med Inssetting under");

		// Sorterer tabellen n ved SorterVedInssetting
		SorterTabell.sorteringVedInssetting(a, n);

		for (Integer e : a) {
			System.out.println(e + " ");
		}
		long sluttTid = System.nanoTime();
		long tid = ((sluttTid-startTime) / 1000000);
		System.out.println(tid + " Milisekund");
		//

	}	
}
