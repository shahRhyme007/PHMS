package com.example.phms_main;

public class MedicationModel
{
    private String medicationName;
    private String route;
    private String lastTimeTaken;
    private int control;
    private int dosage;
    private int image;
    private int intervals;

    public MedicationModel(String medicationName, String route, int image,
                           int dosage, int intervals)
    {
        this.medicationName = medicationName;
        this.route = route;
        this.image = image;
        this.dosage = dosage;
        this.intervals = intervals;
    }

    public MedicationModel(String medicationName, String route, int dosage, int intervals)
    {
        this.medicationName = medicationName;
        this.route = route;
        this.lastTimeTaken = lastTimeTaken;
        this.dosage = dosage;
        this.intervals = intervals;
    }

    public String getMedicationName()
    {
        return medicationName;
    }

    public String getRoute()
    {
        return route;
    }

    public String getLastTimeTaken()
    {

        return lastTimeTaken;
    }

    public int getControl()
    {
        return control;
    }

    public int getImage()
    {
        return image;
    }

    public int getDosage()
    {
        return dosage;
    }

    public int getIntervals()
    {
        return intervals;
    }

}
