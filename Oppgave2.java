package no.hvl.dat102;

import java.util.Random;

public class Oppgave2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Lager tabell med lengde 10 og tilfeldige elementer fra 1-10
		Random tilfeldig = new Random();
		int n1 = 32000;
		int n2 = 64000;
		int n3 = 128000;
		int n4 = 500000;

		Integer[] a1 = new Integer[n1];
		Integer[] a2 = new Integer[n2];
		Integer[] a3 = new Integer[n3];
		Integer[] a4 = new Integer[n4];

		for (int i = 0; i < n1; i++) {
			a1[i] = tilfeldig.nextInt(32000) + 1;
		}
		for (int i = 0; i < n2; i++) {
			a2[i] = tilfeldig.nextInt(64000) + 1;
		}
		for (int i = 0; i < n3; i++) {
			a3[i] = tilfeldig.nextInt(128000) + 1;
		}
		for (int i = 0; i < n4; i++) {
			a4[i] = 2;
		}
		
		
		System.out.println("- Insertion Sort -");
		System.out.println("n	Antall målinger		Gjns Tid	Teo tid");
		testInsertionSort(a1, n1);
		testInsertionSort(a2, n2);
		testInsertionSort(a3, n3);

		System.out.println("- Selection Sort -");
		System.out.println("n	Antall målinger		Gjns Tid	Teo tid");
		testSelectionSort(a1, n1);
		testSelectionSort(a2, n2);
		testSelectionSort(a3, n3);
		
		System.out.println("- Merge Sort -");
		System.out.println("n	Antall målinger		Gjns Tid	Teo tid");
		testMergeSort(a1, n1);
		testMergeSort(a2, n2);
		testMergeSort(a3, n3);
		
		System.out.println("- Quick Sort -");
		System.out.println("n	Antall målinger		Gjns Tid	Teo tid");
		testQuicksort(a1, n1);
		testQuicksort(a2, n2);
		testQuicksort(a3, n3);

		// b) 
		
		System.out.println();
		long gjns = 0;
		for(int i = 0; i < 10; i++) {
			long StartTid = System.nanoTime();
			SorterTabell.quickSort(a4, a4.length);
			long SluttTid = System.nanoTime();
			long tid = ((SluttTid - StartTid) / 1000000);
			System.out.println(tid + " Millisek");
			gjns += tid / 10;
		}
		System.out.println("Gjennomsnitts tid: " + gjns + " Milliesek"); 
		
	}

	public static <T extends Comparable<T>> void testInsertionSort(Integer[] a, int tilfElement) {

		long gjns = 0;
		double c = 0;
		double teoTid = 0;
		for (int i = 0; i < 10; i++) {
			long StartTid = System.nanoTime();
			SorterTabell.sorteringVedInssetting(a, tilfElement);
			long SluttTid = System.nanoTime();
			long tid = ((SluttTid - StartTid) / 1000000);
			gjns += tid / 10;
		}
		//Teoretisk tid
		c = (gjns / 1000) / (Math.pow(tilfElement,2));
		teoTid = c*(gjns);

		System.out.println(tilfElement + "	     10" + "			" + gjns + " MilliSek	" + teoTid+ " MilliSek");

	}

	public static <T extends Comparable<T>> void testSelectionSort(Integer[] a, int tilfElement) {

		long gjns = 0;
		double c = 0;
		double teoTid = 0;
		for (int i = 0; i < 10; i++) {
			long StartTid = System.nanoTime();
			SorterTabell.utvalgssortering(a, tilfElement);
			long SluttTid = System.nanoTime();
			long tid = ((SluttTid - StartTid) / 1000000);
			gjns += tid / 10;
		}

		//Teoretisk tid
				c = (gjns / 1000) / (Math.pow(tilfElement,2));
				teoTid = c*(gjns);

				System.out.println(tilfElement + "	     10" + "			" + gjns + " MilliSek	" + teoTid+ " MilliSek");


	}

	public static <T extends Comparable<T>> void testQuicksort(T[] a, int elementer) {

		long gjns = 0;
		double c = 0;
		double teoTid = 0;
		for (int i = 0; i < 10; i++) {
			long StartTid = System.nanoTime();
			SorterTabell.quickSort(a, a.length);
			long SluttTid = System.nanoTime();
			long tid = ((SluttTid - StartTid) / 1000000);
			gjns += tid / 10;
		}
		//Teoretisk tid
		c = (gjns / 1000) / (Math.pow(elementer,2));
		teoTid = c*(gjns);
		System.out.println(elementer + "	     10" + "			" + gjns + " MilliSek	" + teoTid + " MilliSek");

	}
	public static <T extends Comparable<T>> void testMergeSort(Integer[] a, int tilfElement) {

		long gjns = 0;
		double c = 0;
		double teoTid = 0;
		for (int i = 0; i < 10; i++) {
			long StartTid = System.nanoTime();
			SorterTabell.flettesortering(a, a.length);
			long SluttTid = System.nanoTime();
			long tid = ((SluttTid - StartTid) / 1000000);
			gjns += tid / 10;
		}

		//Teoretisk tid
				c = (gjns / 1000) / (Math.pow(tilfElement,2));
				teoTid = c*(gjns);

				System.out.println(tilfElement + "	     10" + "			" + gjns + " MilliSek	" + teoTid+ " MilliSek");


	}
}

