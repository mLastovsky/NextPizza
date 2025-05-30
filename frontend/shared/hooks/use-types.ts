import { Type } from "@/dto/models";
import { Api } from "@/shared/services/api-client";
import React from "react";

export const useTypes = () => {
  const [types, setTypes] = React.useState<Type[]>([]);
  const [typesLoading, setLoading] = React.useState(true);

  React.useEffect(() => {
    async function fetchTypes() {
      try {
        setLoading(true);
        const types = await Api.types.getAll();
        console.log(types);
        setTypes(types);
      } catch (error) {
        console.log(error);
      } finally {
        setLoading(false);
      }
    }

    fetchTypes();
  }, []);

  return {
    types,
    typesLoading,
  };
};
