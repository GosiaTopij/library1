package biblioteka;

import java.io.Serializable;

/**
 * @author Malgorzata Topij
 */
public class Ksiazka implements Serializable {

	static final long serialVersionUID = 487854513;

	public int id;
	public String tytul;
	public String nazwiskoAutora;
	public String imionaAutora;
	public int rok;
	public String kategoria;
	public boolean czyWypozyczona;
	public int liczbaWypozyczen;

	
	public Ksiazka(int id, String tytul, String nazwiskoAutora, String imionaAutora, int rok, String kategoria) {
		this.id = id;
		this.tytul = tytul;
		this.nazwiskoAutora = nazwiskoAutora;
		this.imionaAutora = imionaAutora;
		this.rok = rok;
		this.kategoria = kategoria;
		this.czyWypozyczona = false;
		this.liczbaWypozyczen = 0;
	}

	public int getId() {
		return id;
	}

	public String getTytul() {
		return tytul;
	}

	public String getNazwisko() {
		return nazwiskoAutora;
	}

	public String getImiona() {
		return imionaAutora;
	}

	public int getRok() {
		return rok;
	}

	public String getKategoria() {
		return kategoria;
	}

	public boolean getCzyWypozyczona() {
		return czyWypozyczona;
	}

	public String getCzyWypozyczona1() {
		if (czyWypozyczona) {
			return "tak";
		} else {
			return "nie";
		}
	}

	public int getLiczbaWypozyczen() {
		return liczbaWypozyczen;
	}


	public String getInicjaly() {
		char s2;
		String inicjaly = "";
		for (int i = 0; i < imionaAutora.length(); i++) {
			if (Character.isWhitespace(imionaAutora.charAt(i))) {
				s2 = imionaAutora.charAt(i + 1);
				inicjaly = s2 + ".";
			}
		}
		return imionaAutora.charAt(0) + "."+inicjaly;

	}

	@Override
	public String toString() {
		return String.format("%4d %-30s %-20s %-20s %4d %-30s %3s", id, tytul, nazwiskoAutora, imionaAutora, rok,
				kategoria, getCzyWypozyczona1());
	}
}
