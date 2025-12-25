package com.example.mynotes.models;


public class Note {
    private String Nom;
    private String Description;
    private String Date;
    private String Priorite;
    public Note(){
        this.Nom="";
        this.Description="";
        this.Date="";
        this.Priorite="";

    }
    public Note(String Nom, String Description, String Date, String Priorite){
        this.Nom=Nom;
        this.Description=Description;
        this.Date=Date;
        this.Priorite=Priorite;

    }
    public String getNom(){
        return this.Nom;
    }
    public String getDescription(){
        return this.Description;
    }
    public String getDate(){
        return this.Date;
    }
    public String getPriorite(){
        return this.Priorite;

    }
    public void setNom(String Nom){
        this.Nom=Nom;
    }
    public void setDescription(String Description){
        this.Description=Description;
    }

    public void setDate(String Date){
        this.Date=Date;
    }

    public void setPriorite(String Priorite){
        this.Priorite=Priorite;
    }

}


