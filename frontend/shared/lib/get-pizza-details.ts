import { calcTotalPizzaPrice } from "./calc-total-pizza-price";
import { PizzaSize, PizzaType, mapPizzaType } from "../constants/pizza";
import { Ingredient, ProductItem } from "@/dto/models";

export const getPizzaDetails = (
  type: PizzaType,
  size: PizzaSize,
  items: ProductItem[],
  ingredients: Ingredient[],
  selectedIngredients: Set<number>
) => {
  const totalPrice = calcTotalPizzaPrice(
    type,
    size,
    items,
    ingredients,
    selectedIngredients
  );
  const textDetaills = `${size} см, ${mapPizzaType[type]} пицца`;

  return { totalPrice, textDetaills };
};
