package no.hvl.dat102;

import java.util.Arrays;

public class SorterTabell {
	// Byter om a[i] og a[j]. Antar at b�de i og j er lovlege indeksar i tabellen.
	private static void swap(Object[] a, int i, int j) {
		Object temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	// Utvalgssortering / Plukksortering (DAT100) (Selction sort)
	// Sorterer dei f�rste n elmementa i tabellen. Kanskje litt uvanlig
	// Kunne ogs� utelatt n og sortert heile tabellen.

	public static <T extends Comparable<? super T>> void utvalgssortering(T[] a, int n) {
		for (int i = 0; i < n - 1; i++) {
			int minstePos = i;
			for (int j = i + 1; j < n; j++) {
				if (a[j].compareTo(a[minstePos]) < 0) {
					minstePos = j;
				}
			}

			swap(a, i, minstePos);
		}
	}

	// Sortering ved innsetting (Insertion sort)
	public static <T extends Comparable<? super T>> void sorteringVedInssetting(T[] a, int n) {
		sorteringVedInssetting(a, 0, n - 1);
	}

	// Sorterer ein del av tabellen, start ... slutt (begge grensene er med)
	public static <T extends Comparable<? super T>> void sorteringVedInssetting(T[] a, int start, int slutt) {
		for (int i = start + 1; i <= slutt; i++) {
			T tmp = a[i];
			int j = i - 1; // siste i sortert del
			boolean ferdig = false;

			while (!ferdig && j >= 0) {
				if (tmp.compareTo(a[j]) < 0) {
					a[j + 1] = a[j];
					j--;
				} else {
					ferdig = true;
				}
			}

			a[j + 1] = tmp;
		}
	}
	
	// Min quicksort som fungerer men ikke helt.
/* 
	public static <T extends Comparable<T>> void quicksort(Integer[] a) {

		quicksort(a, 0, a.length - 1);
	}

	public static <T extends Comparable<T>> void quicksort(T[] a, int start, int slutt) {

		if (start >= slutt) {
			return;
		}

		T pivot = a[slutt];

		int venstrePeker = start;
		int høyrePeker = slutt;

		while (venstrePeker < høyrePeker) {

			while (a[venstrePeker].compareTo(pivot) <= 0 && venstrePeker < høyrePeker) {
				venstrePeker++;
			}

			while (a[høyrePeker].compareTo(pivot) >= 0 && venstrePeker < høyrePeker) {
				høyrePeker--;
			}

			swap(a, venstrePeker, høyrePeker);
		}
		swap(a, venstrePeker, slutt);

		quicksort(a, start, venstrePeker - 1);
		quicksort(a, venstrePeker + 1, slutt);

	}
	 */
	public static final int MIN_SIZE = 5;
	public static <T extends Comparable<? super T>> void quickSort(T[] array, int n) {
		kvikksorter(array, 0, n - 1);
		
		// gjer sorteringa ferdig
		sorteringVedInssetting(array,  n);
	}

	// grovsorterer
	public static <T extends Comparable<? super T>> void kvikksorter(T[] a, int forste, int siste) {
		if (siste - forste + 1 > MIN_SIZE) {
			int delepkt = partition(a, forste, siste);
			kvikksorter(a, forste, delepkt - 1);
			kvikksorter(a, delepkt +1, siste);
		} 
	} 


	private static <T extends Comparable<? super T>> int partition(T[] a, int forste, int siste) {
		int midten = (forste + siste) / 2;

	
		sortFirstMiddleLast(a, forste, midten, siste);


		// Flyttar pivot til nest siste plass
		swap(a, midten, siste - 1);
		int pivotIndex = siste - 1;
		T pivotValue = a[pivotIndex];

	
		int fraVenstre = forste + 1;
		int fraHogre = siste - 2;

		boolean ferdig = false;
		while (!ferdig) {
			
			while (a[fraVenstre].compareTo(pivotValue) < 0) {
				fraVenstre++;
			}
			
			while (a[fraHogre].compareTo(pivotValue) > 0) {
				fraHogre--;
			}
			
			if (fraVenstre < fraHogre) {
				swap(a, fraVenstre, fraHogre);
				fraVenstre++;
				fraHogre--;
			} else
				ferdig = true;
		} 

	
		swap(a, pivotIndex, fraVenstre);
		pivotIndex = fraVenstre;

		return pivotIndex;
	} 
	private static <T extends Comparable<? super T>> void sortFirstMiddleLast(T[] a, int first, int mid, int last) {
		order(a, first, mid); // Make a[first] <= a[mid]
		order(a, mid, last); // Make a[mid] <= a[last]
		order(a, first, mid); // Make a[first] <= a[mid]
	} // end sortFirstMiddleLast

	// Orders two given array elements into ascending order
	// so that a[i] <= a[j].
	private static <T extends Comparable<? super T>> void order(T[] a, int i, int j) {
		if (a[i].compareTo(a[j]) > 0)
			swap(a, i, j);
	} // end order
	
	
	public static <T extends Comparable<? super T>> void flettesortering(T[] a, int n) {
		flettesortering(a, 0, n - 1);
	}

	public static <T extends Comparable<? super T>> void flettesortering(T[] a, int first, int last) {
		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] tempArray = (T[]) new Comparable<?>[a.length]; // unchecked cast
		flettesortering(a, tempArray, first, last);
	}

	private static <T extends Comparable<? super T>> void flettesortering(T[] a, T[] tempTab, int forste, int siste) {
		if (forste >= siste) {
			return;
		} else {
			int midtpkt = (forste + siste) / 2;
			flettesortering(a, tempTab, forste, midtpkt);
			flettesortering(a, tempTab, midtpkt + 1, siste);
			flette(a, tempTab, forste, midtpkt, siste);
		}
	}

	private static <T extends Comparable<? super T>> void flette(T[] a, T[] tempTab, int forste, int midten,
			int siste) {
		
		int fV = forste;
		int sV = midten;
		int fH = midten + 1;
		int sH = siste;

		int index = fV; // Next available location in tempArray
		for (; (fV <= sV) && (fH <= sH); index++) {
			if (a[fV].compareTo(a[fH]) < 0) {
				tempTab[index] = a[fV];
				fV++;
			} else {
				tempTab[index] = a[fH];
				fH++;
			}
		}

		// No vil ein av delane vere to. Kopierer over resten i den andre delen

		// Venstre del, kan vere tom
		for (;fV <= sV; fV++, index++) {
			tempTab[index] = a[fV];
		}

		// Høyre del, kan vere tom
		for (;fH <= sH; fH++, index++) {
			tempTab[index] = a[fH];
		}

		// Kopier den sorterte delen tilbake
		for (index = forste; index <= siste; index++) {
			a[index] = tempTab[index];
		}
	}
		
	//Min kode som ikke fungerer
	/*
	 * public static <T extends Comparable<T>> void mergesort(T[] a, int start, int
	 * slutt) {
	 * 
	 * int lengde = a.length;
	 * 
	 * if (lengde < 2) { return; }
	 * 
	 * int midpunkt = lengde / 2; 
	 * int [] venstre = new int [midpunkt]; 
	 * int [] høyre
	 * = new int [lengde - midpunkt];
	 * 
	 * for (int i = 0; i < midpunkt; i++) { venstre[i] = (int) a[i]; } for (int i =
	 * midpunkt; i < lengde; i++) { høyre[i - midpunkt] = (int) a[i]; }
	 * 
	 * mergesort(a, 0, midpunkt); mergesort(a, midpunkt, a.length);
	 * 
	 * merge(a, venstre, midpunkt, høyre); }
	 * 
	 * public static <T extends Comparable<T>> void merge(T[] tabell,int første, int
	 * midten, int siste) {
	 * 
	 * 
	 * int storrelse = siste - første + 1; T[] hjelpeTabell = (T[]) (new
	 * Comparable[storrelse]);
	 * 
	 * int forsteV = første; int sisteV = midten;
	 * 
	 * int forsteH = midten + 1; int sisteH = siste;
	 * 
	 * int indeks = 0;
	 * 
	 * while ((forsteV <= sisteV) && (forsteH <= sisteH)) {
	 * if(tabell[forsteV].compareTo(tabell[forsteH]) <= 0) { hjelpeTabell[indeks] =
	 * tabell[forsteV]; forsteV++;; } else { hjelpeTabell[indeks] = tabell[forsteH];
	 * forsteH++; } indeks++; } while (forsteV <= sisteV) { hjelpeTabell[indeks] =
	 * tabell[forsteV]; forsteV++; indeks++; } while(forsteH <= sisteH) {
	 * hjelpeTabell[indeks] = tabell[forsteH]; forsteH++; indeks++; }
	 * 
	 * int h = 0; for(indeks = første; indeks <= siste; indeks++) { tabell[indeks] =
	 * hjelpeTabell[h]; h++; }
	 * 
	 * }
	 */
}
