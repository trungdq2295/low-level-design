package src.parking.slot;

public class ParkingSpot {


  private final int spotId;
  private boolean isOccupied;
  private final VehicleType type;
  private final double price;

  public ParkingSpot(int spotId, VehicleType type, double price) {
    this.spotId = spotId;
    this.type = type;
    this.price = price;
    this.isOccupied = false;
  }

  public boolean isAvailable() {
    return !isOccupied;
  }

  public VehicleType getType() {
    return type;
  }

  public int getSpotId() {
    return spotId;
  }

  public synchronized boolean checkOut() {
    if (!isOccupied) return false;
    this.isOccupied = false;
    return true;
  }

  public synchronized boolean checkIn() {
    if (isOccupied) return false;
    this.isOccupied = true;
    return true;
  }
}
