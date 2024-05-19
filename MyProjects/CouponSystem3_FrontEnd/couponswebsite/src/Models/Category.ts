const Category = {
    Food: 'Food',
    Electricity: 'Electricity',
    Restaurant: 'Restaurant',
    Vacation: 'Vacation',
    Toys: 'Toys',
    Automotive: 'Automotive',
    Tires: 'Tires',
    BabyToddler: 'BabyToddler',
    Computers: 'Computers',
    CellPhones: 'CellPhones',
    Televisions: 'Televisions',
    VideoGamesConsoles: 'VideoGamesConsoles',
  } as const
  
  type Category = typeof Category[keyof typeof Category]

  export {Category}