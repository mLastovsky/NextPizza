'use client';

import React from 'react';
import { AddressSuggestions } from 'react-dadata';
import 'react-dadata/dist/react-dadata.css';

interface Props {
  onChange?: (value?: string) => void;
  placeholder?: string;
  className?: string;
}

export const AddressInput: React.FC<Props> = ({
  onChange,
  placeholder = 'Введите адрес...',
  className = ''
}) => {
  const filterLocations = [
    { country: 'Беларусь' }
  ];

  return (
    <AddressSuggestions
      token="b5b8bb983ddcd08648080e0271d9dd367bb7aa65"
      onChange={(data) => onChange?.(data?.value)}
      filterLocations={filterLocations}
      count={5}
      customInput={(props) => (
        <input
          {...props}
          placeholder={placeholder}
          className={`
            w-full px-4 py-2 border border-gray-200 rounded-md
            focus:outline-none focus:border-gray-200
            bg-white text-gray-700 placeholder-gray-500
            ${className}
          `}
        />
      )}
      containerClassName="w-full"
      suggestionsClassName="border border-gray-200 shadow-sm mt-1 rounded-md overflow-hidden"
      suggestionClassName="px-4 py-2 hover:bg-gray-50 whitespace-nowrap overflow-hidden text-ellipsis"
    />
  );
};