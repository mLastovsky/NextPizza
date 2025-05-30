import { axiosInstance } from "./instance";
import { ApiRoutes } from "./constants";
import { Category, FilterParams } from "@/dto/models";

export const getAll = async (): Promise<Category[]> => {
  return (await axiosInstance.get<Category[]>(ApiRoutes.CATEGORIES)).data;
};

export const getFilteredProducts = async (
  params: FilterParams
): Promise<Category[]> => {
  const queryParams = {
    priceFrom: params.priceFrom,
    priceTo: params.priceTo,
    pizzaTypes: Array.isArray(params.pizzaTypes)
      ? params.pizzaTypes.join(",")
      : undefined,
    sizes: Array.isArray(params.sizes) ? params.sizes.join(",") : undefined,
    ingredients: Array.isArray(params.ingredients)
      ? params.ingredients.join(",")
      : undefined,
  };

  const cleanedParams = Object.fromEntries(
    Object.entries(queryParams).filter(([_, value]) => value !== undefined)
  );

  return (
    await axiosInstance.get<Category[]>(ApiRoutes.CATEGORIES, {
      params: cleanedParams,
    })
  ).data;
};
