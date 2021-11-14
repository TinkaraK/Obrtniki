package com.example.obrtniki;

public class TipObrtnika {
    private int id;
    private String naziv;
    private int thumbnail;

    public TipObrtnika(){
    }
    public TipObrtnika(int id, String naziv, int thumbnail){
        this.id = id;
        this.naziv = naziv;
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
