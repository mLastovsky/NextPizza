import { getCart, updateTotalAmount } from "../services/cart";
import { calcCartItemTotalPrice } from "./calc-cart-item-total-price";

export const updateCartTotalAmount = async (token: string) => {
  const userCart = await getCart(token);

  if (!userCart) {
    return;
  }

  const totalAmount = userCart.items.reduce((acc, item) => {
    return acc + calcCartItemTotalPrice(item);
  }, 0);

  return await updateTotalAmount(userCart.id, totalAmount);
};
