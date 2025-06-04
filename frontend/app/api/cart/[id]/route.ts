import { NextRequest, NextResponse } from "next/server";
import {
  updateCartItemQuantity,
  deleteCartItem,
} from "@/shared/services/carts";

export async function PATCH(
  req: NextRequest,
  { params }: { params: { id: string } }
) {
  try {
    const id = Number(params.id);
    const data = (await req.json()) as { quantity: number };
    const token = req.cookies.get("cartToken")?.value;

    if (!token) {
      return NextResponse.json(
        { error: "Cart token not found" },
        { status: 400 }
      );
    }

    const result = await updateCartItemQuantity(id, data.quantity, token);

    return NextResponse.json(result);
  } catch (error) {
    console.error("[CART_PATCH] Ошибка при обновлении корзины:", error);
    return NextResponse.json(
      { message: "Не удалось обновить корзину" },
      { status: 500 }
    );
  }
}

export async function DELETE(
  req: NextRequest,
  { params }: { params: { id: string } }
) {
  try {
    const id = Number(params.id);
    const token = req.cookies.get("cartToken")?.value;

    if (!token) {
      return NextResponse.json(
        { error: "Cart token not found" },
        { status: 400 }
      );
    }

    const result = await deleteCartItem(id, token);

    return NextResponse.json(result);
  } catch (error) {
    console.error(
      "[CART_DELETE] Ошибка при удалении товара из корзины:",
      error
    );
    return NextResponse.json(
      { message: "Не удалось удалить товар из корзины" },
      { status: 500 }
    );
  }
}
