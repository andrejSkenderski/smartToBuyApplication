export interface ProductCreateRequest{
  name: string,
  category: number,
  price: number,
  description?: string,
  image?: string
}
