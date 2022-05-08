package com.quest.entity;


import lombok.*;

import javax.persistence.*;

@Entity()
@Table(name="address")
@Data
@NoArgsConstructor

public class Address {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer Id;
        private String pin;
        private String city;
        private String state;
        private String country;
        private String flatNumber;
        private String addressLine1;
        private String addressLine2;

        @ManyToOne
        @JoinColumn(name="student_id")
        private Student student;

        public Address(String pin, String city, String state, String country, String flatNumber, String addressLine1, String addressLine2) {
                this.pin = pin;
                this.city = city;
                this.state = state;
                this.country = country;
                this.flatNumber = flatNumber;
                this.addressLine1 = addressLine1;
                this.addressLine2 = addressLine2;
        }


       /* public String getPin() {
        return pin;
    }

        public void setPin(String pin) {
        this.pin = pin;
    }

        public String getCity() {
        return city;
    }

        public void setCity(String city) {
        this.city = city;
    }

        public String getState() {
        return state;
    }

        public void setState(String state) {
        this.state = state;
    }

        public String getCountry() {
        return country;
    }

        public void setCountry(String country) {
        this.country = country;
    }

        public String getFlatNumber() {
        return flatNumber;
    }

        public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

        public String getAddressLine1() {
        return addressLine1;
    }

        public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

        public String getAddressLine2() {
        return addressLine2;
    }

        public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
    }*/
}
