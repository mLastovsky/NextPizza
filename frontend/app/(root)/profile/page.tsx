// import { prisma } from '@/prisma/prisma-client';
import { ProfileForm } from "@/shared/components";
// import { getUserSession } from '@/shared/lib/get-user-session';
import { redirect } from "next/navigation";

export default async function ProfilePage() {
  //   const session = await getUserSession();
  const session = null;

  if (!session) {
    return redirect("/not-auth");
  }

  //   const user = await prisma.user.findFirst({ where: { id: Number(session?.id) } });
  const user = null;

  if (!user) {
    return redirect("/not-auth");
  }

  return <ProfileForm data={user} />;
}
