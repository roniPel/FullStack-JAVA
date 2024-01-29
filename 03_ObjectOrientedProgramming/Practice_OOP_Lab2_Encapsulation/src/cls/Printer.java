package cls;

public class Printer {
    // fields
    private Long id;
    private String model;
    private String manufacturer;
    private Boolean isColorful;

    // constructors
    public Printer() {
        this(12345678L,"MP305+","RICOH",false);
    }
    public Printer(Long id, String model, String manufacturer) {
        this(id, model,manufacturer,true);
    }
    public Printer(Long id, String model, String manufacturer, Boolean isColorful) {
        setId(id);
        setModel(model);
        setManufacturer(manufacturer);
        setColorful(isColorful);
    }

    // Getters/Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Boolean getColorful() {
        return isColorful;
    }

    public void setColorful(Boolean colorful) {
        isColorful = colorful;
    }


    // toString, HashCode, Equals

    @Override
    public String toString() {
        return "Printer {" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", isColorful=" + isColorful +
                '}'+"\n";
    }

    // User Methods
    public static void printPrinters(Printer[] printers) {
        for (Printer printer : printers) {
            System.out.println(printer);
        }
    }
}
