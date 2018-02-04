package biblioteka;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Małgorzata Topij
 */
public class Aplikacja {

    public static final String FILE_NAME = "biblioteka.txt";
    static Scanner zapis = new Scanner(System.in);
    static boolean warunekMenu;
    static String nrMenu;

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        Biblioteka biblioteka = Biblioteka.odczytZPliku();

        int nrMenuInt;
        System.out.println("Program katalog biblioteczny");
        do {
            System.out.println("");
            System.out.println("1. Wyświetl katalog ksiażek pełny");
            System.out.println("2. Wyświetl katalog książek skrócony");
            System.out.println("3. Dodaj książkę");
            System.out.println("4. Edytuj książkę");
            System.out.println("5. Wypożycz książkę");
            System.out.println("6. Zwrot książki");
            System.out.println("7. Wyszukiwanie");
            System.out.println("8. Dodatkowe funkcje");
            System.out.println("0. Koniec programu");
            System.out.println("Wybierz pozycję menu: ");
            nrMenu = zapis.nextLine();

            do {
                if (nrMenu.equals("0") || nrMenu.equals("1") || nrMenu.equals("2") || nrMenu.equals("3")
                        || nrMenu.equals("4") || nrMenu.equals("5") || nrMenu.equals("6") || nrMenu.equals("7")
                        || nrMenu.equals("8")) {
                    warunekMenu = true;
                    break;
                } else {
                    warunekMenu = false;
                }
                nrMenu = zapis.nextLine();
            } while (warunekMenu == false);
            nrMenuInt = Integer.valueOf(nrMenu);
            switch (nrMenuInt) {
                case 1:
                    biblioteka.wyswietlKatalogPelny();
                    break;
                case 2:
                    biblioteka.wyswietlKatalogSkrocony();
                    break;
                case 3:
                    DodajKsiazke(biblioteka);
                    break;
                case 4:
                    EdytujKsiazke(biblioteka);
                    break;
                case 5:
                    WypozyczKsiazke(biblioteka);
                    break;
                case 6:
                    ZwrocKsiazke(biblioteka);
                    break;
                case 7:
                    Wyszukiwanie(biblioteka);
                    break;
                case 8:
                    FunkcjeDodatkowe(biblioteka);
                    break;
            }
        } while (nrMenuInt != 0);
        zapis.close();
        biblioteka.zapisDoPliku();
    }

    public static void DodajKsiazke(Biblioteka biblioteka) {
        int id = biblioteka.iloscKsiazek() + 1;
        String tytul, imie, nazwisko, kategoria;
        int rok;
        do {
            System.out.print("Tytuł książki: ");
            tytul = zapis.nextLine();
        } while (tytul.isEmpty());

        do {
            System.out.print("Nazwisko autora: ");
            nazwisko = zapis.nextLine();
        } while (nazwisko.isEmpty());

        do {
            System.out.print("Imiona autora: ");
            imie = zapis.nextLine();
        } while (imie.isEmpty());

        do {
            System.out.print("Kategoria książki: ");
            kategoria = zapis.nextLine();
        } while (kategoria.isEmpty());

        System.out.print("Rok wydania: ");
        rok = zapis.nextInt();
        while (rok > 2012 || rok < 1700) {
            System.out.println("Rok wydania książki musi być w przedziale od 1700 do 2012");
            System.out.print("Rok wydania: ");
            rok = zapis.nextInt();
        }

        Ksiazka dodajKsiazke = new Ksiazka(id, tytul, nazwisko, imie, rok, kategoria);
        biblioteka.dodajKsiazke(dodajKsiazke);
        System.out.println("Ksiazka została dodana do katalogu");
    }

    public static void EdytujKsiazke(Biblioteka biblioteka) {

        int nrMenuInt;
        String tytul, imie, nazwisko, kategoria;
        int rok;
        System.out.print("Wprowadz id książki: ");
        int id1 = zapis.nextInt();
        zapis.nextLine();
        id1--;
        System.out.println("Wybrana ksiązka do edycji");
        biblioteka.wyswietlId(id1);
        System.out.println("");
        do {

            System.out.println("1. Edytuj imiona");
            System.out.println("2. Edytuj nazwisko");
            System.out.println("3. Edytuj tytul");
            System.out.println("4. Edytuj rok wydania");
            System.out.println("5. Edytuj kategorie");
            System.out.println("0. Koniec edycji");
            System.out.println("Wybierz pozycję menu: ");
            nrMenu = zapis.nextLine();
            do {
                if (nrMenu.equals("0") || nrMenu.equals("1") || nrMenu.equals("2") || nrMenu.equals("3") || nrMenu.equals("4") || nrMenu.equals("5")) {
                    warunekMenu = true;
                    break;
                } else {
                    warunekMenu = false;
                    
                }
                nrMenu = zapis.nextLine();
            } while (warunekMenu == false);
            nrMenuInt = Integer.valueOf(nrMenu);
            switch (nrMenuInt) {
                case 1:
                    System.out.print("Imiona autora: ");
                    imie = zapis.nextLine();
                    biblioteka.setImiona(id1, imie);
                    break;
                case 2:
                    System.out.print("Nazwisko autora: ");

                    nazwisko = zapis.nextLine();
                    biblioteka.setNazwisko(id1, nazwisko);
                    break;
                case 3:
                    System.out.print("Tytuł książki: ");

                    tytul = zapis.nextLine();
                    biblioteka.setTytul(id1, tytul);
                    break;
                case 4:
                    System.out.print("Rok wydania książki: ");

                    rok = zapis.nextInt();
                    while (rok > 2017 || rok < 1700) {
                        System.out.println("Rok wydania książki musi być w przedziale od 1700 do 2017");
                        System.out.print("Rok wydania: ");
                        rok = zapis.nextInt();
                    }
                    biblioteka.setRok(id1, rok);
                    zapis.nextLine();
                    break;
                case 5:
                    System.out.print("Kategorie książki: ");
                    kategoria = zapis.nextLine();
                    biblioteka.setKategoria(id1, kategoria);
                    break;
            }

            System.out.println("Książka po edycji: ");
            biblioteka.wyswietlId(id1);
        } while (nrMenuInt != 0);
    }

    public static void WypozyczKsiazke(Biblioteka biblioteka) {
        System.out.print("Podaj id książki do wypożyczenia: ");
        int id1 = zapis.nextInt();
        id1--;
        if (biblioteka.getCzyWypozyczona(id1) == false) {
            biblioteka.setCzyWypozyczona(id1, true);
            System.out.println("Książka została wypożyczona.");
           
        } else {
            System.out.println("Brak książki w bibliotece");
        }
    }

    public static void ZwrocKsiazke(Biblioteka biblioteka) {
        System.out.print("Podaj id książki do oddania: ");
        int id1 = zapis.nextInt();
        id1--;
        if (biblioteka.getCzyWypozyczona(id1) == false) {
            System.out.println("Ksiązka nie została wypożyczona");
        } else {
            biblioteka.setCzyWypozyczona(id1, false);
            System.out.println("Książka została oddana.");
        }
    }

    public static void Wyszukiwanie(Biblioteka biblioteka) {
        int nrMenuInt;
        do {
            System.out.println("1. Szukaj po tytule");
            System.out.println("2. Szukaj po nazwisku");
            System.out.println("3. Szukaj po kategorii");
            System.out.println("0. Powrót do głównego menu");
            System.out.println("Wybierz pozycję menu: ");
            nrMenu = zapis.nextLine();
            do {
                if (nrMenu.equals("0") || nrMenu.equals("1") || nrMenu.equals("2") || nrMenu.equals("3")) {
                    warunekMenu = true;
                    break;
                } else {
                    warunekMenu = false;
                    
                }
                nrMenu = zapis.nextLine();
            } while (warunekMenu == false);
            nrMenuInt = Integer.valueOf(nrMenu);
            switch (nrMenuInt) {
                case 1:
                    System.out.print("Podaj tytul: ");

                    String tytul = zapis.nextLine();
                    biblioteka.wyswietlTytul(tytul);
                    break;
                case 2:
                    System.out.print("Podaj nazwisko: ");
                    String nazwisko = zapis.nextLine();
                    biblioteka.wyswietlNazwisko(nazwisko);
                    break;
                case 3:
                    System.out.print("Podaj kategorię: ");
                    String kategoria = zapis.next();
                    biblioteka.wyswietlKategoria(kategoria);
                    break;
            }
        } while (nrMenuInt != 0);
    }

    public static void FunkcjeDodatkowe(Biblioteka biblioteka) {
        int nrMenuInt;
        do {
            System.out.println("1. Wyświetl ilość aktualnie wypożyczonych ksiązek");
            System.out.println("2. Wyświetlanie ilości ksiązek znajdujących sie w bibliotece");
            System.out.println("0. Powrót do głównego menu");
            System.out.println("Wybierz pozycję menu: ");
            nrMenu = zapis.nextLine();
            do {
                if (nrMenu.equals("0") || nrMenu.equals("1") || nrMenu.equals("2") ) {
                    warunekMenu = true;
                    break;
                } else {
                    warunekMenu = false;
                    
                }
                nrMenu = zapis.nextLine();
            } while (warunekMenu == false);
            nrMenuInt = Integer.valueOf(nrMenu);
            switch (nrMenuInt) {
                case 1:
                    biblioteka.wyswietlObecnieWypozyczone();
                    break;

                case 2:
                    System.out.println("Liczba książek znajdujących sie w bibliotece wynosi " + biblioteka.iloscKsiazek());
                    break;
            }
        } while (nrMenuInt != 0);
    }

}
