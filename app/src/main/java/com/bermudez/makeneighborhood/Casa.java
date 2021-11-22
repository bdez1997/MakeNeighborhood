package com.bermudez.makeneighborhood;

public class Casa {

    private Integer iId;
    private String sCalle;
    private int iNumero;
    private double doSperficie;

    public Casa(Integer iId, String sCalle, int iNumero, double doSperficie) {
        this.iId = iId;
        this.sCalle = sCalle;
        this.iNumero = iNumero;
        this.doSperficie = doSperficie;
    }

    public Casa() {
    }

    public Casa(String sCalle, int iNumero, double doSperficie) {
        this.sCalle = sCalle;
        this.iNumero = iNumero;
        this.doSperficie = doSperficie;
    }

    public Integer getiId() {
        return iId;
    }

    public String getsCalle() {
        return sCalle;
    }

    public int getiNumero() {
        return iNumero;
    }

    public double getDoSperficie() {
        return doSperficie;
    }

    public void setiId(Integer iId) {
        this.iId = iId;
    }

    public void setsCalle(String sCalle) {
        this.sCalle = sCalle;
    }

    public void setiNumero(int iNumero) {
        this.iNumero = iNumero;
    }

    public void setDoSperficie(double doSperficie) {
        this.doSperficie = doSperficie;
    }
}
