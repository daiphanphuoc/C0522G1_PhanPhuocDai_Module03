package model.facility;

public abstract class Facility {
    private int iDFacility;
    private String nameFacility;
    private double leasedArea;
    private double rentalCosts;
    private int maxPerson;
    private RentType rentalType;
    private String descriptionOtherConvenience;

    public Facility() {
    }

    public Facility(int iDFacility, String nameFacility, double leasedArea, double rentalCosts,
                    int maxPerson, RentType rentalType, String descriptionOtherConvenience) {
        this.iDFacility = iDFacility;
        this.nameFacility = nameFacility;
        this.leasedArea = leasedArea;
        this.rentalCosts = rentalCosts;
        this.maxPerson = maxPerson;
        this.rentalType = rentalType;
        this.descriptionOtherConvenience = descriptionOtherConvenience;
    }



    public String getNameFacility() {
        return nameFacility;
    }

    public void setNameFacility(String nameFacility) {
        this.nameFacility = nameFacility;
    }

    public double getLeasedArea() {
        return leasedArea;
    }

    public void setLeasedArea(double leasedArea) {
        this.leasedArea = leasedArea;
    }

    public double getRentalCosts() {
        return rentalCosts;
    }

    public void setRentalCosts(double rentalCosts) {
        this.rentalCosts = rentalCosts;
    }

    public int getMaxPerson() {
        return maxPerson;
    }

    public void setMaxPerson(int maxPerson) {
        this.maxPerson = maxPerson;
    }

    public RentType getRentalType() {
        return rentalType;
    }

    public void setRentalType(RentType rentalType) {
        this.rentalType = rentalType;
    }

    public int getIDFacility() {
        return iDFacility;
    }

    public void setIDFacility(int iDFacility) {
        this.iDFacility = iDFacility;
    }

    public String getDescriptionOtherConvenience() {
        return descriptionOtherConvenience;
    }

    public void setDescriptionOtherConvenience(String descriptionOtherConvenience) {
        this.descriptionOtherConvenience = descriptionOtherConvenience;
    }

    @Override
    public String toString() {
        return "iDFacility='" + iDFacility + '\'' +
                ", nameFacility='" + nameFacility + '\'' +
                ", leasedArea='" + leasedArea + '\'' +
                ", rentalCosts=" + rentalCosts +
                ", maxPerson=" + maxPerson +
                ", rentalType='" + rentalType.getName() + '\'';
    }
}
