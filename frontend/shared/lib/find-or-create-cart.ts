// import { prisma } from '@/prisma/prisma-client';

export const findOrCreateCart = async (token: string) => {
  // let userCart = await prisma.cart.findFirst({
  //   where: {
  //     token,
  //   },
  // });

  // if (!userCart) {
  //   userCart = await prisma.cart.create({
  //     data: {
  //       token,
  //     },
  //   });
  // }

  // return userCart;

  // Заглушка
  return {
    id: "stub-cart-id",
    token,
    createdAt: new Date(),
    updatedAt: new Date(),
    items: [],
  };
};
