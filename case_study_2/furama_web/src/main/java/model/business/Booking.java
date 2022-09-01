package model.business;




import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Booking implements Comparable<Booking>{
    private String iDBooking;
    private Date begin;
    private Date end;
    private String iDCustomer;
    private String nameFacility;
    private String iDFacility;

    public Booking() {
    }

    public Booking(String iDBooking, Date begin, Date end, String iDCustomer,
                   String nameFacility, String facilityType) {
        this.iDBooking = iDBooking;
        this.begin = begin;
        this.end = end;
        this.iDCustomer = iDCustomer;
        this.nameFacility = nameFacility;
        this.iDFacility = facilityType;
    }

    public String getInfo() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dateStringBegin = df.format(getBegin());
        String dateStringEnd = df.format(getEnd());
        return String.format("%s,%s,%s,%s,%s,%s", getIDBooking(), dateStringBegin, dateStringEnd,
                getIDCustomer(), getNameFacility(), getIDFacility());
    }

    public String getIDBooking() {
        return iDBooking;
    }

    public void setIDBooking(String iDBooking) {
        this.iDBooking = iDBooking;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getIDCustomer() {
        return iDCustomer;
    }

    public void setIDCustomer(String iDCustomer) {
        this.iDCustomer = iDCustomer;
    }

    public String getNameFacility() {
        return nameFacility;
    }

    public void setNameFacility(String nameFacility) {
        this.nameFacility = nameFacility;
    }

    public String getIDFacility() {
        return iDFacility;
    }

    public void setIDFacility(String iDFacility) {
        this.iDFacility = iDFacility;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "iDBooking='" + iDBooking + '\'' +
                ", begin=" + begin +
                ", end=" + end +
                ", iDCustomer='" + iDCustomer + '\'' +
                ", nameFacility='" + nameFacility + '\'' +
                ", facilityType='" + iDFacility + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return iDBooking.equals(booking.iDBooking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iDBooking);
    }

    @Override
    public int compareTo(@NotNull Booking o) {

        return getIDBooking().compareTo(o.getIDBooking());
    }
}

