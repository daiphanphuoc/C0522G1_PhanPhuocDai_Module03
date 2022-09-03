package model.facility;

import java.util.Objects;

public class Room extends Facility{
    String free;

    public Room() {
    }

    @Override
    public String getInfo() {
        return String.format("%s@@%s@@%s@@%s@@%s@@%s@@%s@@%s@@%s@@%s@@%s@@%s",getFacilityType().getName(),getIDFacility(),getNameFacility(),getLeasedArea(),
                getRentalCosts(),getMaxPerson(),getRentalType().getName(),
                getDescription(),"null","null","null",getFree());
    }

    public Room(int iDFacility, String nameFacility, double leasedArea, double rentalCosts,
                int maxPerson, RentType rentalType,FacilityType facilityType, String descriptionOtherConvenience,
                String free) {
        super(iDFacility, nameFacility, leasedArea, rentalCosts, maxPerson, rentalType,facilityType,
                descriptionOtherConvenience);
        this.free = free;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    @Override
    public String toString() {
        return "Room{ " + super.toString() +
                "free='" + free + '\'' +
                "} ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(getIDFacility(), room.getIDFacility());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIDFacility());
    }
}
