import { useState } from "react";

// Список валидных промокодов и их скидки
const VALID_PROMO_CODES: Record<string, number> = {
  SUMMER20: 20, // 20% скидка
  WELCOME10: 10, // 10% скидка
  SPECIAL30: 30, // 30% скидка
  TEST15: 15, // 15% скидка
};

export const usePromoCode = (totalAmount: number) => {
  const [promoCode, setPromoCode] = useState("");
  const [promoError, setPromoError] = useState("");
  const [discountAmount, setDiscountAmount] = useState(0);
  const [isValidatingPromo, setIsValidatingPromo] = useState(false);

  const handleApplyPromo = async () => {
    setPromoError("");
    setIsValidatingPromo(true);

    // Эмуляция задержки запроса к серверу
    await new Promise((resolve) => setTimeout(resolve, 500));

    try {
      // Проверяем, есть ли промокод в нашем списке
      const discountPercent = VALID_PROMO_CODES[promoCode];

      if (discountPercent !== undefined) {
        // Если промокод найден - рассчитываем скидку
        const discount = Math.round((totalAmount * discountPercent) / 100);
        setDiscountAmount(discount);
      } else {
        // Если промокод не найден
        setDiscountAmount(0);
        setPromoError("Промокод недействителен или истек");
      }
    } catch (error) {
      setDiscountAmount(0);
      setPromoError("Произошла ошибка при проверке промокода");
      console.error(error);
    } finally {
      setIsValidatingPromo(false);
    }
  };

  return {
    promoCode,
    setPromoCode,
    promoError,
    discountAmount,
    isValidatingPromo,
    handleApplyPromo,
  };
};
