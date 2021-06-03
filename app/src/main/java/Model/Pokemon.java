package Model;

public class Pokemon {

    private String name;
    private String abilitati;
    private String tipuri;

    public Pokemon(String name, String abilitati, String tipuri) {
        this.name = name;
        this.abilitati = abilitati;
        this.tipuri = tipuri;
    }

    public String getAbilitati() {
        return abilitati;
    }

    public String getName() {
        return name;
    }

    public String getTipuri() {
        return tipuri;
    }

    public void setAbilitati(String abilitati) {
        this.abilitati = abilitati;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTipuri(String tipuri) {
        this.tipuri = tipuri;
    }
}
