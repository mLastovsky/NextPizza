import { axiosInstance } from "./instance";
import { ApiRoutes } from "./constants";
import { CartDTO, CreateCartItemValues } from "@/dto/models";

export const getCart = async (): Promise<CartDTO> => {
  return (await axiosInstance.get<CartDTO>(ApiRoutes.CART)).data;
};

export const updateItemQuantity = async (
  id: number,
  quantity: number
): Promise<CartDTO> => {
  return (
    await axiosInstance.patch(`${ApiRoutes.CART_ITEMS}/${id}`, {
      quantity,
    })
  ).data;
};

export const removeCartItem = async (id: number): Promise<CartDTO> => {
  return (await axiosInstance.delete(`${ApiRoutes.CART_ITEMS}/${id}`)).data;
};

export const addCartItem = async (
  data: CreateCartItemValues
): Promise<CartDTO> => {
  return (await axiosInstance.post(ApiRoutes.CART, data)).data;
};
