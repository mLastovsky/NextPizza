import { ChooseProductModal } from "@/shared/components/shared";
import { Api } from "@/shared/services/api-client";
import { notFound } from "next/navigation";

export default async function ProductModalPage({
  params: { id },
}: {
  params: { id: string };
}) {
  const product = await Api.products.getById(id);

  if (!product) {
    return notFound();
  }

  return <ChooseProductModal product={product} />;
}
