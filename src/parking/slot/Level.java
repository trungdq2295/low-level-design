package src.parking.slot;

import java.util.List;
import java.util.Optional;

public class Level {

  public static final int NO_AVAILABLE_SPOT = -1;

  private final int levelId;
  private final List<ParkingSpot> parkingSpots;

  public Level(int levelId, List<ParkingSpot> parkingSpots) {
    this.levelId = levelId;
    this.parkingSpots = parkingSpots;
  }

  public int getLevelId() {
    return levelId;
  }

  public long getAvailableSpots() {
    return parkingSpots.stream().filter(ParkingSpot::isAvailable).count();
  }

  private Optional<ParkingSpot> getAvailableParkingSpot(VehicleType type) {
    return parkingSpots.stream().filter(spot -> spot.isAvailable() && spot.getType().equals(type)).findFirst();
  }

  public int checkIn(VehicleType type) {
    var optional = parkingSpots.stream().filter(spot -> spot.isAvailable() && spot.getType().equals(type)).findFirst();
    if (optional.isPresent()) {
      var spot = optional.get();
      return spot.checkIn() ? spot.getSpotId() : NO_AVAILABLE_SPOT;
    }
    return NO_AVAILABLE_SPOT;
  }

  public boolean checkOut(int spotId) {
    return parkingSpots.stream()
      .filter(spot -> spot.getSpotId() == spotId)
      .findFirst()
      .map(ParkingSpot::checkOut)
      .orElse(false);
  }
}
