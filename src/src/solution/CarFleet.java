package solution;

public class CarFleet {

  public static void main(String[] args) {

    int target = 100;
    int[] position = {0,2,4};
    int[] speed = {4,2,1};

    int result = carFleet(target, position, speed);

    System.out.println(result);
  }

  public static int carFleet(int target, int[] position, int[] speed) {

    if (position.length == 1) {
      return 1;
    }

    quickSort(position, 0, position.length - 1, speed);

    Car[] cars = new Car[position.length];

    for (int i = 0; i < position.length; i++) {
      cars[i] = new Car(position[i], speed[i], (double) (target - position[i]) / speed[i]);
    }

    int carFleet = 0;

    double lastCarrArrivalTime = 0;

    for (int i = cars.length -1; i>= 0; i--) {

      if (cars[i].arrivalTime > lastCarrArrivalTime) {
        carFleet++;
        lastCarrArrivalTime = cars[i].arrivalTime;
      }


    }

    return carFleet;

  }

  public static class Car {

    public int position;
    public int speed;
    public double arrivalTime;

    public Car(int position, int speed, double arrivalTime) {
      this.position = position;
      this.speed = speed;
      this.arrivalTime = arrivalTime;
    }

  }

  public static void quickSort(int[] arr, int low, int high, int[] speed) {
    if (low < high) {
      int pi = partition(arr, low, high, speed);

      quickSort(arr, low, pi - 1, speed);
      quickSort(arr, pi + 1, high, speed);
    }
  }


  private static int partition(int[] arr, int low, int high, int[] speed) {
    int pivot = arr[high];
    int i = (low - 1);
    for (int j = low; j < high; j++) {
      if (arr[j] < pivot) {
        i++;

        {
          int temp = arr[i];
          arr[i] = arr[j];
          arr[j] = temp;
        }

        {
          int temp = speed[i];
          speed[i] = speed[j];
          speed[j] = temp;
        }
      }
    }

    int temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;

    int temp2 = speed[i + 1];
    speed[i + 1] = speed[high];
    speed[high] = temp2;

    return i + 1;
  }

}
