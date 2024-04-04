import java.util.LinkedList;

public class Graf {

    static class Polaczenie { //Klasa dla krawedzi z waga, oznaceniem dokad zmierza i skad
        int waga;
        int doCelu;
        int zPunktu;

        public Polaczenie(int waga, int doCelu, int zPunktu) { //Konstruktor klasy Polaczenie, czyli krawedzi, ktory przypisuje defaltowe wartosci zmiennym
            this.waga = waga;
            this.doCelu = doCelu;
            this.zPunktu = zPunktu;
        }


        static class PolaczenieGraf { //
            int liczbaWierzcholkow; //ustalenie liczby wierzcholkow naszego grafu
            LinkedList<Polaczenie>[] listaPolaczen; //stworzenie listy wszytskich polaczen

            public PolaczenieGraf(int liczbaWierzcholkow) { //Konstruktor, ktory inicjuje liczbe wierzcholkow, liste i nadaje kazdemu wierzcholkowi liste
                this.liczbaWierzcholkow = liczbaWierzcholkow;
                listaPolaczen = new LinkedList[liczbaWierzcholkow];
                for (int i = 0; i < liczbaWierzcholkow; i++) {
                    listaPolaczen[i] = new LinkedList<>();
                }
            }

            public void dodajPolaczenie(int waga, int doCelu, int zPunktu) { // Dodawanie polaczen do grafu, dodaje jako pierwszy element wartosc z ktorego punktu idzie krawedz ido ktorego punktu
                Polaczenie polaczenie = new Polaczenie(waga, doCelu, zPunktu);
                listaPolaczen[zPunktu].addFirst(polaczenie);

                listaPolaczen[doCelu].addFirst(new Polaczenie(waga, zPunktu, doCelu));

                //czyli krawedz nieskierowana czyli obustronna, bo jakby byla skierowana to mozna zrobic tylko polaczenei z punktu, bez robienia od punktu
            }

            public void wypiszGrafa() { // Metoda wypisujaca grafa
                for (int i = 0; i < liczbaWierzcholkow; i++) { // Iteracja przez wsyztskie wierzcholki
                    LinkedList<Polaczenie> polaczenia = listaPolaczen[i];
                    for (int j = 0; j < polaczenia.size(); j++) { //Iteracja przez liste kazdego wierzcholka
                        System.out.println("Węzeł " + i + " jest połączony z " +
                                polaczenia.get(j).doCelu + " o wadze " + polaczenia.get(j).waga);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int liczbaWierzcholkow = 10;
        Graf.Polaczenie.PolaczenieGraf graf = new Graf.Polaczenie.PolaczenieGraf(liczbaWierzcholkow);
        graf.dodajPolaczenie(4, 1, 0);
        graf.dodajPolaczenie(3, 2, 0);
        graf.dodajPolaczenie(7, 3, 1);

        graf.wypiszGrafa();
    }
}
