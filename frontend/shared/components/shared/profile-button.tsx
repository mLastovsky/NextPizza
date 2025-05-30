import React from "react";
import { Button } from "../ui/button";
import { CircleUser, User } from "lucide-react";
import Link from "next/link";

interface Props {
  onClickSignIn?: () => void;
  className?: string;
  isAuthenticated?: boolean; // Added optional prop
}

export const ProfileButton: React.FC<Props> = ({
  className,
  onClickSignIn,
  isAuthenticated = false, // Default to false
}) => {
  // Commented out useSession
  // const { data: session } = useSession();

  return (
    <div className={className}>
      {!isAuthenticated ? ( // Changed from !session to !isAuthenticated
        <Button
          onClick={onClickSignIn}
          variant="outline"
          className="flex items-center gap-1"
        >
          <User size={16} />
          Войти
        </Button>
      ) : (
        <Link href="/profile">
          <Button variant="secondary" className="flex items-center gap-2">
            <CircleUser size={18} />
            Профиль
          </Button>
        </Link>
      )}
    </div>
  );
};
