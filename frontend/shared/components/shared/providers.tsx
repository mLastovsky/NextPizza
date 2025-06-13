"use client";

import React from "react";
import { Toaster } from "react-hot-toast";
// import { SessionProvider } from 'next-auth/react';
import NextTopLoader from "nextjs-toploader";

export const Providers: React.FC<React.PropsWithChildren> = ({ children }) => {
  return (
    <>
      {/* Commented out SessionProvider until auth is needed */}
      {/* <SessionProvider>{children}</SessionProvider> */}

      {/* Render children directly */}
      {children}

      <Toaster />
      <NextTopLoader />
    </>
  );
};
