const ClientType = {
    Administrator: 'Administrator',
    Company: 'Company',
    Customer: 'Customer',
    Guest: 'Guest',
  } as const
  
  type ClientType = typeof ClientType[keyof typeof ClientType]

  export {ClientType}