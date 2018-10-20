package ru.otus;

public class MainCasheEngine {

    public static void main(String[] args) throws InterruptedException {


        int casheSize = 5;
        EmployeeCasheEngine casheEngine = new EmployeeCasheEngine(casheSize, 10000);

        casheEngine.put(new Employee(1, "fio01" ));
        casheEngine.put(new Employee(2, "fio02" ));
        casheEngine.put(new Employee(3, "fio03" ));
        casheEngine.put(new Employee(4, "fio04" ));
        casheEngine.put(new Employee(5, "fio05" ));


        for (int i = 1; i <= casheSize; i++) {
            Employee element = casheEngine.get(i);
            System.out.println("Employee " + i + ": " + (element != null ? element.getFio() : "null"));
        }

        System.out.println("Cache hits: " + casheEngine.getHitCount());
        System.out.println("Cache misses: " + casheEngine.getMissCount());

        Thread.sleep(10000);

        for (int i = 1; i <= casheSize; i++) {
            Employee element = casheEngine.get(i);
            System.out.println("Employee " + i + ": " + (element != null ? element.getFio() : "null"));
        }

        System.out.println("Cache hits: " + casheEngine.getHitCount());
        System.out.println("Cache misses: " + casheEngine.getMissCount());

        casheEngine.dispose();
    }

}