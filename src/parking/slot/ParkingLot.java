package src.parking.slot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingLot {

  private static ParkingLot instance;


  private final List<Level> levels;

  private ParkingLot() {
    this.levels = new ArrayList<>();
  }


  public synchronized ParkingLot getInstance() {
    if (instance == null) {
      instance = new ParkingLot();
    }
    return instance;
  }

  public void addLevel(Level level) {
    levels.add(level);
  }

  public SpotInformation checkIn(VehicleType type, String vehicleInfo) throws Exception {
    Level availableLevel = getAvailableLevel().orElseThrow(() -> new Exception("No space available"));
    int spotId = availableLevel.checkIn(type);
    if (spotId == Level.NO_AVAILABLE_SPOT) {
      throw new Exception("No space available");
    }
    return new SpotInformation(vehicleInfo, availableLevel.getLevelId(), spotId, System.currentTimeMillis());
  }

  public synchronized boolean checkOut(SpotInformation spotInformation) {
    return levels.stream()
      .filter(level -> level.getLevelId() == spotInformation.getLevelId())
      .findFirst()
      .map(level -> level.checkOut(spotInformation.getSpotId()))
      .orElse(false);
  }

  private Optional<Level> getAvailableLevel() {
    return levels.stream().filter(level -> level.getAvailableSpots() > 0).findFirst();
  }
}
