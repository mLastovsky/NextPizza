import { Api } from "../services/api-client";
import { FilterParams } from "@/dto/models";

const DEFAULT_MIN_PRICE = 0;
const DEFAULT_MAX_PRICE = 1000;

export const findPizzas = async (params: FilterParams) => {
  const sizes = params.sizes ?? [];
  const pizzaTypes = params.pizzaTypes ?? [];
  const ingredientsIdArr = params.ingredients ?? [];

  const minPrice = params.priceFrom ?? DEFAULT_MIN_PRICE;
  const maxPrice = params.priceTo ?? DEFAULT_MAX_PRICE;

  const apiParams: FilterParams = {
    priceFrom: minPrice,
    priceTo: maxPrice,
    pizzaTypes: pizzaTypes,
    sizes: sizes,
    ingredients: ingredientsIdArr,
  };

  return Api.categories.getFilteredProducts(apiParams);
};
