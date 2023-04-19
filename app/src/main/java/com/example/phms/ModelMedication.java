package com.example.phms;

public class ModelMedication
{
    String medicationName;
    String route;
    String lastTimeTaken;
    int control;
    int dosage;
    int image;
    int intervals;

    public ModelMedication(String medicationName, String route, String lastTimeTaken,
                           int dosage, int intervals)
    {
        this.medicationName = medicationName;
        this.route = route;
        this.lastTimeTaken = lastTimeTaken;
        this.dosage = dosage;
        this.intervals = intervals;
    }

    public ModelMedication(String medicationName, String route, String lastTimeTaken,
                           int image, int dosage, int intervals)
    {
        this.medicationName = medicationName;
        this.route = route;
        this.lastTimeTaken = lastTimeTaken;
        this.image = image;
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
