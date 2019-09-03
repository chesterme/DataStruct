package ch01_text;

public class Person {

    private String country;

    public void setCountry(String country) {
        this.country = country;
    }

    public Person(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
