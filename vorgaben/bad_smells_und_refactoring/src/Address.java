public class Address {
    private final String street;
    private final String streetNumber;
    private final int postalCode;
    private final String city;

    public Address(String street, String streetNumber, int postalCode, String city) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    public String getStreet(){
        String result = street + " " + streetNumber + "\n";
        return result;
    }

    public String getCity(){
        String result =  postalCode + " " + city + "\n";
        return result;
    }
}
