package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] luvut;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int lukujenMaara;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        alustaLuvut(kapasiteetti);
        this.kasvatuskoko = kasvatuskoko;
    }

    private void alustaLuvut(int kapasiteetti) {
        luvut = new int[kapasiteetti];
        for (int i = 0; i < luvut.length; i++) {
            luvut[i] = 0;
        }
        lukujenMaara = 0;
    }

    public boolean lisaa(int luku) {
        if (lukujenMaara == luvut.length) {
            luvut = laajennaLuvut();
        }
        if (!kuuluu(luku)) {
            luvut[lukujenMaara++] = luku;
            return true;
        }
        return false;
    }

    private int[] laajennaLuvut() {
        int[] uusi = new int[luvut.length + kasvatuskoko];
        kopioiTaulukko(luvut, uusi);
        return uusi;
    }

    public boolean kuuluu(int luku) {
        for (int i : luvut) {
            if (i == luku) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        if (kuuluu(luku)) {
            nollaaLuku(luku);
            litistaTaulukko();
            return true;
        }
        return false;
    }

    private void nollaaLuku(int luku) {
        for (int i = 0; i < luvut.length; i++) {
            if (luvut[i] == luku) {
                luvut[i] = 0;
                lukujenMaara--;
            }
        }
    }

    private void litistaTaulukko() {
        for (int i = 0; i < luvut.length; i++) {
            if (luvut[i] == 0 && i < luvut.length - 1) {
                luvut[i] = luvut[i + 1];
                luvut[i + 1] = 0;
            }
        }
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        System.arraycopy(vanha, 0, uusi, 0, vanha.length);
    }

    public int mahtavuus() {
        return lukujenMaara;
    }

    @Override
    public String toString() {
        String palautettava = "{";
        for (int i = 0; i < lukujenMaara; i++) {
            palautettava += luvut[i] + ", ";
        }
        return palautettava.subSequence(0, palautettava.length() - 2) + "}";
    }

    public int[] toIntArray() {
        int[] taulu = new int[lukujenMaara];
        System.arraycopy(luvut, 0, taulu, 0, taulu.length);
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }
        return z;
    }
}