package Beans;

public enum Category {
    Food, Electricity, Restaurant, Vacation, Toys, Automotive, Tires,
    BabyToddler, Computers, CellPhones, Televisions, VideoGamesConsoles,
    Entertainment, Books, Fashion, Shoes, Accessories, HealthBeauty, Delivery,
    GroceriesSupermarket, Pizza, HomeGarden, Furniture, BedMattress, LawnMowers,
    Sports, OutdoorsEssentials, Bikes, Travel, CarRental, Flights, Hotels,
    Students, SchoolSupplies, MilitaryVeterans, Senior;

    private static final int size = Category.values().length;

    /**
     * Provides a random category based on values in Category Enum
     * @return String with a random category name
     */
    public static Category GetRandomCategory() {
        String category = "";
        int rand = (int) (Math.round(Math.random()*(size-1)));
        category += Category.values()[rand];
        return Category.valueOf(category);
    }
}
