package org.ticket.booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Builder
public class Train {

    private String trainId;
    private String trainNo;
    private List<List<Integer>> seats;
    private Map<String, String> stations;

    public Train() {}

    public Train(String trainId, String trainNo, List<List<Integer>> seats, Map<String, String> stations) {
        this.trainId = trainId;
        this.trainNo = trainNo;
        this.seats = seats;
        this.stations = stations;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public Map<String, String> getStations() {
        return stations;
    }

    public void setStations(Map<String, String> stations) {
        this.stations = stations;
    }

    public String getTrainInfo() {
        return String.format("Train ID: %s Train No: %s", trainId, trainNo);
    }
}
