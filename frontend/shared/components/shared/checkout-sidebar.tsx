'use client'

import React, { useState } from 'react';
import { WhiteBlock } from './white-block';
import { CheckoutItemDetails } from './checkout-item-details';
import { ArrowRight, BadgePercent, Package, Percent, Truck } from 'lucide-react';
import { Button, Skeleton } from '../ui';
import { cn } from '@/shared/lib/utils';
import { PromoCodeInput } from './checkout-promocode-input';
import { usePromoCode } from '@/shared/hooks/use-promocode';

const VAT = 15;
const DELIVERY_PRICE = 250;

interface Props {
  totalAmount: number;
  loading?: boolean;
  className?: string;
}

export const CheckoutSidebar: React.FC<Props> = ({
  totalAmount,
  loading,
  className,
}) => {

  const {
    promoCode,
    setPromoCode,
    promoError,
    discountAmount,
    isValidatingPromo,
    handleApplyPromo,
  } = usePromoCode(totalAmount);

  const vatPrice = (totalAmount * VAT) / 100;
  const totalPrice = totalAmount + DELIVERY_PRICE + vatPrice - discountAmount;

  return (
    <WhiteBlock className={cn('p-6 sticky top-4', className)}>
      <div className="flex flex-col gap-1">
        <span className="text-xl">Итого:</span>
        {loading ? (
          <Skeleton className="h-11 w-48" />
        ) : (
          <span className="h-11 text-[34px] font-extrabold">{totalPrice} ₽</span>
        )}
      </div>

      <CheckoutItemDetails
        title={
          <div className="flex items-center">
            <Package size={18} className="mr-2 text-gray-400" />
            Стоимость корзины:
          </div>
        }
        value={loading ? <Skeleton className="h-6 w-16 rounded-[6px]" /> : `${totalAmount} ₽`}
      />

      <CheckoutItemDetails
        title={
          <div className="flex items-center">
            <Percent size={18} className="mr-2 text-gray-400" />
            Налоги:
          </div>
        }
        value={loading ? <Skeleton className="h-6 w-16 rounded-[6px]" /> : `${vatPrice} ₽`}
      />

      <CheckoutItemDetails
        title={
          <div className="flex items-center">
            <Truck size={18} className="mr-2 text-gray-400" />
            Доставка:
          </div>
        }
        value={loading ? (<Skeleton className="h-6 w-16 rounded-[6px]" />) : (`${DELIVERY_PRICE} ₽`)
        }
      />

      {discountAmount > 0 && (
        <CheckoutItemDetails
          title={
            <div className="flex items-center text-green-600">
              <BadgePercent size={18} className="mr-2" />
              Скидка:
            </div>
          }
          value={loading ? (<Skeleton className="h-6 w-16 rounded-[6px]" />) : (`- ${discountAmount} ₽`)
          }
        />
      )}

      <PromoCodeInput
        promoCode={promoCode}
        onChange={setPromoCode}
        onApply={handleApplyPromo}
        loading={isValidatingPromo}
        errorText={promoError}
      />

      <Button
        loading={loading}
        type="submit"
        className="w-full h-14 rounded-2xl mt-6 text-base font-bold">
        Перейти к оплате
        <ArrowRight className="w-5 ml-2" />
      </Button>
    </WhiteBlock>
  );
};
