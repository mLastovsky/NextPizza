import { Story, StoryItem } from "@/dto/models";
import { axiosInstance } from "./instance";

export type IStory = Story & {
  items: StoryItem[];
};

export const getAll = async () => {
  const { data } = await axiosInstance.get<IStory[]>("/stories");

  return data;
};
