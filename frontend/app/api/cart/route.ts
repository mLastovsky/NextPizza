import { NextRequest, NextResponse } from "next/server";
import crypto from "crypto";
// import { findOrCreateCart } from "@/shared/lib/find-or-create-cart";
// import { CreateCartItemValues } from "@/shared/services/dto/cart.dto";
// import { updateCartTotalAmount } from "@/shared/lib/update-cart-total-amount";

export async function GET(req: NextRequest) {
  try {
    const token = req.cookies.get("cartToken")?.value;

    if (!token) {
      return NextResponse.json({ totalAmount: 0, items: [] });
    }

    // Закомментирован код работы с Prisma
    /*
    const userCart = await prisma.cart.findFirst({
      where: {
        OR: [
          {
            token,
          },
        ],
      },
      include: {
        items: {
          orderBy: {
            createdAt: "desc",
          },
          include: {
            productItem: {
              include: {
                product: true,
              },
            },
            ingredients: true,
          },
        },
      },
    });
    */

    // Возвращаем заглушку вместо реальных данных
    return NextResponse.json({
      totalAmount: 0,
      items: [],
      message: "Cart data (stub)",
    });
  } catch (error) {
    console.log("[CART_GET] Server error", error);
    return NextResponse.json(
      { message: "Не удалось получить корзину" },
      { status: 500 }
    );
  }
}

export async function POST(req: NextRequest) {
  try {
    let token = req.cookies.get("cartToken")?.value;

    if (!token) {
      token = crypto.randomUUID();
    }

    // Закомментирован вызов функции findOrCreateCart
    // const userCart = await findOrCreateCart(token);

    // Закомментирован парсинг тела запроса
    // const data = (await req.json()) as CreateCartItemValues;

    // Закомментирован код работы с Prisma
    /*
    const findCartItem = await prisma.cartItem.findFirst({
      where: {
        cartId: userCart.id,
        productItemId: data.productItemId,
        ingredients: {
          every: {
            id: { in: data.ingredients },
          },
        },
      },
    });

    // Если товар был найден, делаем +1
    if (findCartItem) {
      await prisma.cartItem.update({
        where: {
          id: findCartItem.id,
        },
        data: {
          quantity: findCartItem.quantity + 1,
        },
      });
    } else {
      await prisma.cartItem.create({
        data: {
          cartId: userCart.id,
          productItemId: data.productItemId,
          quantity: 1,
          ingredients: { connect: data.ingredients?.map((id) => ({ id })) },
        },
      });
    }

    const updatedUserCart = await updateCartTotalAmount(token);
    */

    // Возвращаем заглушку
    const resp = NextResponse.json({
      message: "Cart created/updated (stub)",
      token,
    });
    resp.cookies.set("cartToken", token || "");
    return resp;
  } catch (error) {
    console.log("[CART_POST] Server error", error);
    return NextResponse.json(
      { message: "Не удалось создать корзину" },
      { status: 500 }
    );
  }
}
