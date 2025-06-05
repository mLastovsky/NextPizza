import { NextRequest, NextResponse } from "next/server";
import { addToCart, getCart } from "@/shared/services/cart";
import { CreateCartItemValues } from "@/dto/models";

export async function GET(req: NextRequest) {
  try {
    const token = req.cookies.get("cartToken")?.value;

    if (!token) {
      return NextResponse.json({ totalAmount: 0, items: [] });
    }

    const cart = await getCart(token);

    return NextResponse.json(cart);
  } catch (error) {
    console.error("[CART_GET] Ошибка при получении корзины:", error);
    return NextResponse.json(
      { message: "Не удалось получить корзину" },
      { status: 500 }
    );
  }
}

export async function POST(req: NextRequest) {
  try {
    const body = (await req.json()) as CreateCartItemValues;
    const token = req.cookies.get("cartToken")?.value;

    const result = await addToCart(body, token);

    const response = NextResponse.json(result);
    if (result.token) {
      response.cookies.set("cartToken", result.token);
    }

    return response;
  } catch (error) {
    console.error("[CART_POST] Ошибка при добавлении в корзину:", error);
    return NextResponse.json(
      { message: "Не удалось добавить товар в корзину" },
      { status: 500 }
    );
  }
}
