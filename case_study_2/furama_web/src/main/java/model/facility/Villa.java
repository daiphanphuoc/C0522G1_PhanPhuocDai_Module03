package model.facility;

import java.util.Objects;

public class Villa extends Facility {
    private String room;
    private int floor;
    private double areaPool;


    public Villa() {
    }

    @Override
    public String getInfo() {
        return String.format("%s@@%s@@%s@@%s@@%s@@%s@@%s@@%s@@%s@@%s@@%s@@%s",getFacilityType().getName(),getIDFacility(),getNameFacility(),getLeasedArea(),
                getRentalCosts(),getMaxPerson(),getRentalType().getName(),
                getDescription(),getRoom(),getFloor(),getAreaPool(),"null");
    }

    public Villa(int iDFacility, String nameFacility, double leasedArea, double rentalCosts,
                 int maxPerson, RentType rentalType, FacilityType facilityType, String description,
                 String room, int floor, double areaPool) {
        super(iDFacility, nameFacility, leasedArea, rentalCosts, maxPerson, rentalType,facilityType,
                description);
        this.room = room;
        this.floor = floor;
        this.areaPool = areaPool;
    }



    public double getAreaPool() {
        return areaPool;
    }

    public void setAreaPool(double areaPool) {
        this.areaPool = areaPool;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Villa villa = (Villa) o;
        return getIDFacility()==villa.getIDFacility();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIDFacility());
    }


}
