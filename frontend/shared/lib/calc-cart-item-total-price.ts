import { CartItemDTO } from "@/dto/models";

export const calcCartItemTotalPrice = (item: CartItemDTO): number => {
  const ingredientsPrice = item.ingredients.reduce(
    (acc, ingredient) => acc + ingredient.price,
    0
  );

  const totalBeforeRounding =
    (ingredientsPrice + item.productItem.price) * item.quantity;

  const roundedTotal = Math.ceil(totalBeforeRounding * 10) / 10;

  return parseFloat(roundedTotal.toFixed(1));
};
