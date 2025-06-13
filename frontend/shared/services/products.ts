import { axiosInstance } from "./instance";
import { ApiRoutes } from "./constants";
import { FilterParams, Product } from "@/dto/models";

export const search = async (query: string): Promise<Product[]> => {
  return (
    await axiosInstance.get<Product[]>(ApiRoutes.SEARCH_PRODUCTS, {
      params: { query },
    })
  ).data;
};

export const getById = async (id: string): Promise<Product> => {
  return (await axiosInstance.get<Product>(`${ApiRoutes.PRODUCTS}/${id}`)).data;
};

export const getFiltered = async (params: FilterParams): Promise<Product[]> => {
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
    await axiosInstance.get<Product[]>(ApiRoutes.PRODUCTS, {
      params: cleanedParams,
    })
  ).data;
};
