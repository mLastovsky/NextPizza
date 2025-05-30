// Enums
export enum OrderStatus {
  PENDING = "PENDING",
  SUCCEEDED = "SUCCEEDED",
  CANCELLED = "CANCELLED",
}

export enum UserRole {
  USER = "USER",
  ADMIN = "ADMIN",
}

export interface FilterParams {
  priceFrom?: number;
  priceTo?: number;
  pizzaTypes?: number[];
  sizes?: number[];
  ingredients?: number[];
}

// Main DTOs
export interface User {
  id: number;
  fullName: string;
  email: string;
  password: string;
  role: UserRole;
  verified?: Date | null;
  provider?: string | null;
  providerId?: string | null;
  cart?: Cart | null;
  orders: Order[];
  verificationCode?: VerificationCode | null;
  createdAt: Date;
  updatedAt: Date;
}

export interface Type {
  id: number;
  type: string;
}

export interface Category {
  id: number;
  name: string;
  products: Product[];
  createdAt: Date;
  updatedAt: Date;
}

export interface Product {
  id: number;
  name: string;
  imageUrl: string;
  ingredients: Ingredient[];
  category: Category;
  items: ProductItem[];
  createdAt: Date;
  updatedAt: Date;
}

export interface ProductItem {
  id: number;
  price: number;
  size?: number | null;
  pizzaType?: number | null;
  cartItems: CartItem[];
  product: Product;
}

export interface Ingredient {
  id: number;
  name: string;
  price: number;
  imageUrl: string;
  products: Product[];
  cartItems: CartItem[];
  createdAt: Date;
  updatedAt: Date;
}

export interface Cart {
  id: number;
  user?: User | null;
  userId?: number | null;
  items: CartItem[];
  token: string;
  totalAmount: number;
  createdAt: Date;
  updatedAt: Date;
}

export interface CartItem {
  id: number;
  cart: Cart;
  cartId: number;
  productItem: ProductItem;
  productItemId: number;
  quantity: number;
  ingredients: Ingredient[];
  createdAt: Date;
  updatedAt: Date;
}

export interface Order {
  id: number;
  user?: User | null;
  userId?: number | null;
  token: string;
  totalAmount: number;
  status: OrderStatus;
  paymentId?: string | null;
  items: any; // Json type - можно заменить на конкретный интерфейс
  fullName: string;
  email: string;
  phone: string;
  address: string;
  comment?: string | null;
  createdAt: Date;
  updatedAt: Date;
}

export interface VerificationCode {
  id: number;
  user: User;
  userId: number;
  code: string;
  createdAt: Date;
}

export interface Story {
  id: number;
  previewImageUrl: string;
  items: StoryItem[];
  createdAt: Date;
}

export interface StoryItem {
  id: number;
  storyId: number;
  story: Story;
  sourceUrl: string;
  createdAt: Date;
}
