/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

public class PixAdditionalInformationBuilder {
    //the instance to build
    private PixAdditionalInformation pixAdditionalInformation;

    /**
     * Default constructor to initialize the instance
     */
    public PixAdditionalInformationBuilder() {
        pixAdditionalInformation = new PixAdditionalInformation();
    }

    public PixAdditionalInformationBuilder name(String name) {
        pixAdditionalInformation.setName(name);
        return this;
    }

    public PixAdditionalInformationBuilder value(String value) {
        pixAdditionalInformation.setValue(value);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public PixAdditionalInformation build() {
        return pixAdditionalInformation;
    }
}