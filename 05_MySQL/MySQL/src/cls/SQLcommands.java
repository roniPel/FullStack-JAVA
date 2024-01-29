package cls;

public class SQLcommands {

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS `class169`.`students` (" +
                    "  `id` INT NOT NULL AUTO_INCREMENT," +
                    "  `name` VARCHAR(45) NULL," +
                    "  `tel` VARCHAR(45) NULL," +
                    "  `avgGrade` INT NULL," +
                    "  `city` VARCHAR(45) NULL," +
                    "  PRIMARY KEY (`id`));";

    public static final String addStudent = "INSERT INTO `class169`.`students`" +
            "(`name`, `tel`, `avgGrade`, `city`, `married`) " +
            "VALUES (?, ?, ?, ?, ?);" ;

    public static final String getAllStudents = "SELECT * FROM `class169`.`students`" ;

    public static final String getStudentsAvgOver95 = "SELECT * FROM `class169`.`students` WHERE avgGrade > 95";
    public static String getStudentsAbove(int grade) {
        return "SELECT * FROM `class169`.`students` WHERE avgGrade > "+ grade;
    }

    public static final String getStudentsAvgOver = "SELECT * FROM `class169`.`students` WHERE avgGrade > ?";
    public static final String getStudentBetwen = "SELECT * FROM `class169`.`students` WHERE avgGrade>? AND avgGrade<?";


    // *** SQL Exercises ***

    // SCALAR FUNCTIONS
    public static final String question13 = "WITH newTable AS" +
            "( SELECT CONCAT(ProductID, \" AND \",SupplierID) AS PRODUCT, ROUND(UnitPrice * 1.165) AS FULL_PRICE FROM northwind.products )" +
            "SELECT * FROM newTable WHERE FULL_PRICE>40";

    public static final String question10 = "SELECT products.productID AS prodID, c.Description AS prodDesc, s.Country AS prodCountry FROM northwind.products " +
            "INNER JOIN northwind.categories AS c ON products.categoryID = c.CategoryID " +
            "INNER JOIN northwind.suppliers AS s ON products.supplierID = s.supplierID " +
            "WHERE s.Country LIKE 'a%' ";

}
