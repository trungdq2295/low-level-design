package src.parking.slot;

public class SpotInformation {

  private final String vehicleInformation;
  private final int levelId;
  private final int spotId;
  private final long checkInTime;

  public SpotInformation(String vehicleInformation, int levelId, int spotId, long checkInTime) {
    this.vehicleInformation = vehicleInformation;
    this.levelId = levelId;
    this.spotId = spotId;
    this.checkInTime = checkInTime;
  }

  public String getVehicleInformation() {
    return vehicleInformation;
  }

  public int getLevelId() {
    return levelId;
  }

  public int getSpotId() {
    return spotId;
  }

  public long getCheckInTime() {
    return checkInTime;
  }
}
