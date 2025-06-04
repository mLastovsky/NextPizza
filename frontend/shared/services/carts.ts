import { axiosInstance } from "./instance";
import { ApiRoutes } from "./constants";
import { CartDTO, CreateCartItemValues } from "@/dto/models";

/**
 * Получение текущей корзины.
 */
export const getCart = async (token?: string): Promise<CartDTO> => {
  const response = await axiosInstance.get<CartDTO>(ApiRoutes.CART, {
    headers: token ? { Cookie: `cartToken=${token}` } : undefined,
    withCredentials: true,
  });

  return response.data;
};

/**
 * Добавление товара в корзину.
 */
export const addToCart = async (
  data: CreateCartItemValues,
  token?: string
): Promise<{ token: string; message: string }> => {
  const response = await axiosInstance.post(ApiRoutes.CART, data, {
    headers: token ? { Cookie: `cartToken=${token}` } : undefined,
    withCredentials: true,
  });

  return response.data;
};

/**
 * Обновление количества товара в корзине.
 */
export const updateCartItemQuantity = async (
  id: number,
  quantity: number,
  token?: string
): Promise<{ message: string }> => {
  const response = await axiosInstance.patch(
    `${ApiRoutes.CART}/${id}`,
    { quantity },
    {
      headers: token ? { Cookie: `cartToken=${token}` } : undefined,
      withCredentials: true,
    }
  );

  return response.data;
};

/**
 * Удаление товара из корзины.
 */
export const deleteCartItem = async (
  id: number,
  token?: string
): Promise<{ message: string }> => {
  const response = await axiosInstance.delete(`${ApiRoutes.CART}/${id}`, {
    headers: token ? { Cookie: `cartToken=${token}` } : undefined,
    withCredentials: true,
  });

  return response.data;
};
