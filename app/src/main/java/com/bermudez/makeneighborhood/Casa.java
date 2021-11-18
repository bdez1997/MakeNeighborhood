package com.bermudez.makeneighborhood;

public class Casa {

    private int iId;
    private String sCalle;
    private int iNumero;
    private double doSperficie;

    public Casa() {

    }

    public Casa(String sCalle, int iNumero, double doSperficie) {

        setsCalle(sCalle);
        setiNumero(iNumero);
        setDoSperficie(doSperficie);

    }

    public Casa(int iId, String sCalle, int iNumero, double doSperficie) {

        setiId(iId);
        setsCalle(sCalle);
        setiNumero(iNumero);
        setDoSperficie(doSperficie);

    }

    public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public String getsCalle() {
        return sCalle;
    }

    public void setsCalle(String sCalle) {
        this.sCalle = sCalle;
    }

    public int getiNumero() {
        return iNumero;
    }

    public void setiNumero(int iNumero) {
        this.iNumero = iNumero;
    }

    public double getDoSperficie() {
        return doSperficie;
    }

    public void setDoSperficie(double doSperficie) {
        this.doSperficie = doSperficie;
    }
}
