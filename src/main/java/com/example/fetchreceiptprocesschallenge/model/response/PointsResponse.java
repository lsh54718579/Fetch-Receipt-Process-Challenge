package com.example.fetchreceiptprocesschallenge.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class PointsResponse {

  private Long points;

  public PointsResponse(Long points) {
    this.points = points;
  }

  public Long getPoints() {
    return points;
  }

  public void setPoints(Long points) {
    this.points = points;
  }


}

