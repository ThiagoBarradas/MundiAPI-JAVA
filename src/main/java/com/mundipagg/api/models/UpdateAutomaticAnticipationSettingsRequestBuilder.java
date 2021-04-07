/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

public class UpdateAutomaticAnticipationSettingsRequestBuilder {
    //the instance to build
    private UpdateAutomaticAnticipationSettingsRequest updateAutomaticAnticipationSettingsRequest;

    /**
     * Default constructor to initialize the instance
     */
    public UpdateAutomaticAnticipationSettingsRequestBuilder() {
        updateAutomaticAnticipationSettingsRequest = new UpdateAutomaticAnticipationSettingsRequest();
    }

    public UpdateAutomaticAnticipationSettingsRequestBuilder enabled(Boolean enabled) {
        updateAutomaticAnticipationSettingsRequest.setEnabled(enabled);
        return this;
    }

    public UpdateAutomaticAnticipationSettingsRequestBuilder type(String type) {
        updateAutomaticAnticipationSettingsRequest.setType(type);
        return this;
    }

    public UpdateAutomaticAnticipationSettingsRequestBuilder volumePercentage(Integer volumePercentage) {
        updateAutomaticAnticipationSettingsRequest.setVolumePercentage(volumePercentage);
        return this;
    }

    public UpdateAutomaticAnticipationSettingsRequestBuilder delay(Integer delay) {
        updateAutomaticAnticipationSettingsRequest.setDelay(delay);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public UpdateAutomaticAnticipationSettingsRequest build() {
        return updateAutomaticAnticipationSettingsRequest;
    }
}