import java.util.List;

import java.util.ArrayList;

class SumWorker implements Runnable {

    private List<Integer> numbers;

    private int sum;



    public SumWorker(List<Integer> numbers) {

        this.numbers = numbers;

        this.sum = 0;

    }



    @Override

    public void run() {

        for (int number : numbers) {

            sum += number;

        }

    }



    public int getSum() {

        return sum;

    }

}



class AverageWorker implements Runnable {

    private List<Integer> numbers;

    private double average;



    public AverageWorker(List<Integer> numbers) {

        this.numbers = numbers;

        this.average = 0;

    }



    @Override

    public void run() {

        int sum = 0;

        for (int number : numbers) {

            sum += number;

        }

        average = (double) sum / numbers.size();

    }



    public double getAverage() {

        return average;

    }

}



class MaxWorker implements Runnable {

    private List<Integer> numbers;

    private int max;



    public MaxWorker(List<Integer> numbers) {

        this.numbers = numbers;

        this.max = Integer.MIN_VALUE;

    }



    @Override

    public void run() {

        for (int number : numbers) {

            if (number > max) {

                max = number;

            }

        }

    }



    public int getMax() {

        return max;

    }

}



public class assignmentCalc {

    public static void main(String[] args) throws InterruptedException {

        // Parse the command line arguments as integers

        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {

            numbers.add(Integer.parseInt(args[i]));

        }



        // Create worker threads

        SumWorker sumWorker = new SumWorker(numbers);

        AverageWorker averageWorker = new AverageWorker(numbers);

        MaxWorker maxWorker = new MaxWorker(numbers);



        Thread sumThread = new Thread(sumWorker);

        Thread averageThread = new Thread(averageWorker);

        Thread maxThread = new Thread(maxWorker);



        // Start worker threads

        sumThread.start();

        averageThread.start();

        maxThread.start();



        // Wait for worker threads to finish

        sumThread.join();

        averageThread.join();

        maxThread.join();



        // Output the result

        System.out.println("Sum = " + sumWorker.getSum());

        System.out.println("Average = " + averageWorker.getAverage());

        System.out.println("Max = " + maxWorker.getMax());

    }

}
