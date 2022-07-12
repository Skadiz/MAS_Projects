import attribute.Customer;
import bag.*;
import ordered.*;
import subset.*;


import static subset.Employee.prepares;
import static subset.Employee.proceeds;
import static subset.Order.isPrepared;
import static subset.Order.isProceeded;


public class Main
{

    public static void main(String[] args) throws Exception
    {
        System.out.println("Attribute constraint example:");
        attributeConstraintExample();

        System.out.println("\n"+"Unique constraint example: ");
        uniqueConstraintExample();

        System.out.println("\n"+"Subset constraint example:");
        subsetConstraintExample();

        System.out.println("\n"+"ordered constraint example: ");
        orderedConstraintExample();

        System.out.println("\n"+"bag constraint example: ");
        bagConstraintExample();

        System.out.println("\n"+"XOR constraint example: ");
        xorConstraintExample();

        System.out.println("\n"+"custom constraint example: ");
        customConstraintExample();
    }

    public static void attributeConstraintExample() throws Exception
    {
        Customer customer1 = new Customer("Vsevolod", "Kurochka", 22);
        Customer customer2 = new Customer("Vsevolod", "Mlosdzsy", 18); //17 - error

        System.out.println("Customer 1: " + customer1);
        customer1.buyDrink();
        System.out.println();

        System.out.println("Customer 2: " + customer2);
        customer2.buyDrink();
        System.out.println();
    }

    public static void uniqueConstraintExample() throws Exception
    {
        unique.Book book1 = new unique.Book("Harry Potter: the Philosopher's Stone", "J. K. Rowling", "English", 1234567890);
        unique.Book book2 = new unique.Book("The Hunger Games", "Suzanne Collins", "English", 1234567891);

        System.out.println("Book 1: " + book1);
        System.out.println("Book 2: " + book2);
        System.out.println();

        book1.setNumberISBN(1111111111);
        System.out.println("Book 1: " + book1);

        book2.setNumberISBN(1111111112); // 1111111111- already exist
        System.out.println("Book 2: " + book2);
        System.out.println();
    }

    public static void subsetConstraintExample() throws Exception {
        Employee employee1 = new Employee("Vsevolod", "Kurochka");
        subset.Order order1 = new subset.Order("AAA1234");

        employee1.addLink(prepares, isPrepared, order1);
        employee1.addLink_subset(proceeds, isProceeded, prepares, order1);

        employee1.showLinks(proceeds, System.out);
        employee1.showLinks(prepares, System.out);
        System.out.println();
    }

    public static void orderedConstraintExample() throws Exception
    {
        ordered.Book book1 = new ordered.Book("Harry Potter: the Philosopher's Stone", "J. K. Rowling");
        ordered.Book book2 = new ordered.Book("Harry Potter: the Chamber of Secrets", "J. K. Rowling");
        ordered.Book book3 = new ordered.Book("Harry Potter: the Goblet of Fire", "J. K. Rowling");

        PublishingCompany publishingCompany1 = new PublishingCompany("Best Books", "Koszykowa 85");

        publishingCompany1.addBook(book1);
        publishingCompany1.addBook(book2);
        publishingCompany1.addBook(book3);

        System.out.println(publishingCompany1.getBookSet());
        System.out.println();
    }

    public static void bagConstraintExample()
    {
        Clinic clinic = new Clinic("ClinkaJava", "Koszykowa 56");
        Patient patient = new Patient("Vsevolod", "Kurochka", 23, "123 456 789");

        Visit visit1 = new Visit("15.05.2022", patient, clinic);
        Visit visit2 = new Visit("21.04.2022", patient, clinic);

        System.out.println(visit1);
        System.out.println();

        System.out.println(visit2);
        System.out.println();
    }

    public static void xorConstraintExample()
    {
        System.out.println();
    }

    public static void customConstraintExample() throws Exception
    {
        customConstraint.Order order1 = new customConstraint.Order(123456789, "Paid");
        System.out.println(order1);
        System.out.println();

        order1.changeStatus("Sent");
        System.out.println(order1);
        System.out.println();
    }

}
