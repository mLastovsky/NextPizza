import { Container, ProductForm } from "@/shared/components/shared";
import { notFound } from "next/navigation";
import { getById } from "@/shared/services/products";

export default async function ProductPage({
  params: { id },
}: {
  params: { id: string };
}) {
  const product = await getById(id);

  if (!product) {
    return notFound();
  }

  return (
    <Container className="flex flex-col my-10">
      <ProductForm product={product} />
    </Container>
  );
}
