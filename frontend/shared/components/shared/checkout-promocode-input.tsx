import React from "react";
import { cn } from "@/shared/lib/utils";
import { Button, Input } from "../ui";
import { ErrorText } from "./error-text";

interface Props {
    promoCode: string;
    onChange: (value: string) => void;
    onApply: () => void;
    loading: boolean;
    errorText?: string;
    className?: string;
}

export const PromoCodeInput: React.FC<Props> = ({
    promoCode,
    onChange,
    onApply,
    loading,
    errorText,
    className,
}) => {
    return (
        <div className={cn("mt-4 mb-2", className)}>
            <div className="flex gap-2">
                <Input
                    placeholder="У меня есть промокод"
                    value={promoCode}
                    onChange={(e) => onChange(e.target.value)}
                    className={cn(
                        "flex-1 h-11 text-base", // Чуть меньше высота (44px), но увеличенный шрифт
                        errorText && "border-red-500"
                    )}
                />
                <Button
                    type="button"
                    variant="secondary"
                    className="flex items-center gap-1 h-11 px-5 text-base" // Согласованная высота
                    onClick={onApply}
                    loading={loading}
                >
                    Применить
                </Button>
            </div>
            {errorText && <ErrorText text={errorText} className="mt-2 text-base" />}
        </div>
    );
};