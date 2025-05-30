import { NextResponse } from "next/server";

export async function GET() {
  const mockStories = [{}];

  return NextResponse.json(mockStories);
}
