package com.besco.innova.hymnenational;

import com.orm.SugarRecord;

/**
 * Created by root on 4/10/16.
 */



public class Listeobject extends SugarRecord{

    private int liste_id;
    private int imagename;
    private String description;
    private String filename;
    private String datecreation;


    public Listeobject(){

    }

    public Listeobject(int imagename,String description,String filename, String datecreation){


        this.imagename = imagename;
        this.description = description;
        this.filename = filename;
        this.datecreation = datecreation;

    }





    //Getter
    public int getListe_id() { return liste_id; }
    public int getImagename() { return imagename; }
    public String getDescription() { return description; }
    public String getFilename() { return filename; }
    public String getDatecreation() { return datecreation; }


    //Setter
    public void setListe_id(int liste_id) { this.liste_id = liste_id;}
    public void setImagename(int imagename) { this.imagename = imagename;}
    public void setDescription(String description) { this.description = description;}
    public void setFilename(String filename) { this.filename = filename;}
    public void setDatecreation(String datecreation) { this.datecreation = datecreation;}


    @Override
    public String toString() {
        return this.description;
    }

}
