package biblioteka;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Malgorzata Topij
 */
public class Biblioteka implements Serializable {

	static final long serialVersionUID = 487854513;
	Ksiazka[] ksiazka = new Ksiazka[1000];
	public static final String FILE_NAME = "biblioteka.txt";

	public void dodajKsiazke(Ksiazka k) {
		int id = 0;
		for (Ksiazka b : ksiazka) {
			if (b != null) {
				id++;
			}
		}
		ksiazka[id] = k;
	}

	public int iloscKsiazek() {
		int ilosc = 0;
		for (Ksiazka a : ksiazka) {
			if (a != null) {
				ilosc++;
			}
		}
		return ilosc;
	}

	public void wyswietlId(int id) {
		if (ksiazka[id] != null) {
			System.out.println(ksiazka[id]);
		}
	}

	public void wyswietlKatalogPelny() {
		for (Ksiazka a : ksiazka) {
			if (a != null) {
				System.out.println(a);
			}
		}
	}

	public void wyswietlKatalogSkrocony() {
		for (Ksiazka a : ksiazka) {
			if (a != null) {
				System.out.printf("%4d %-4s %-15s %-35s %-4s", a.getId(), a.getInicjaly(), a.getNazwisko(),
						a.getTytul(), a.getCzyWypozyczona1());
                                                System.out.println("");
			}
		}
	}

	public void wyswietlObecnieWypozyczone() {
		int ilosc = 0;
		for (Ksiazka a : ksiazka) {
			if (a != null) {
				if (a.getCzyWypozyczona() == true) {
					ilosc++;
				}
			}
		}
		System.out.println("Liczba obecnie wypożyczonych: " + ilosc);
	}

	public void setTytul(int id, String tytul) {
		ksiazka[id].tytul = tytul;
	}

	public void setImiona(int id, String imiona) {
		ksiazka[id].imionaAutora = imiona;
	}

	public void setNazwisko(int id, String nazwisko) {
		ksiazka[id].nazwiskoAutora = nazwisko;
	}

	public void setKategoria(int id, String kategoria) {
		ksiazka[id].kategoria = kategoria;
	}

	public void setRok(int id, int rok) {
		ksiazka[id].rok = rok;
	}

	public void setCzyWypozyczona(int id, boolean czyWypozyczona) {
		ksiazka[id].czyWypozyczona = czyWypozyczona;
	}

	public boolean getCzyWypozyczona(int id) {
		return ksiazka[id].getCzyWypozyczona();
	}


	public void wyswietlKategoria(String kategoria) {
		for (Ksiazka b : ksiazka) {
			if (b != null) {
				if (b.getKategoria().equals(kategoria)) {
					System.out.println(b);
				}
			}
		}
	}

	public void wyswietlTytul(String tytul) {
            for (Ksiazka ksiazka1 : ksiazka) {
                if (ksiazka1 != null) {
                    if (ksiazka1.tytul.contains(tytul)) {
                        System.out.println(ksiazka1);
                    }
                }
            }
	}

	public void wyswietlNazwisko(String nazwisko) {
            for (Ksiazka ksiazka1 : ksiazka) {
                if (ksiazka1 != null) {
                    if (ksiazka1.nazwiskoAutora.contains(nazwisko)) {
                        System.out.println(ksiazka1);
                    }
                }
            }
	}

	public void zapisDoPliku() throws FileNotFoundException, IOException {
		ObjectOutputStream plik = null;
		try {
			plik = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
			plik.writeObject(this);
		}
		 finally {
			if (plik != null) {
				plik.close();
				System.out.println("Zapis do pliku powiódł się, program zakonczył swoje działanie");
			}
		}
	}

	public static Biblioteka odczytZPliku() throws ClassNotFoundException, FileNotFoundException, IOException {
		ObjectInputStream plik = null;
		try {
			plik = new ObjectInputStream(new FileInputStream(FILE_NAME));
			return (Biblioteka) plik.readObject();
		}
		catch (FileNotFoundException err) {System.out.println("Utworzono nowy plik");}
		catch (EOFException e){System.out.println("koniec pliku");} 
		catch (IOException e){}
		Biblioteka biblioteka;
		return biblioteka = new Biblioteka();

	}

}
