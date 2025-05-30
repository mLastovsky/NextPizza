import { axiosInstance } from "./instance";
import { ApiRoutes } from "./constants";
import { Type } from "@/dto/models";

export const getAll = async (): Promise<Type[]> => {
  return (await axiosInstance.get<Type[]>(ApiRoutes.TYPES)).data;
};
