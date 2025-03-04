import React from "react";

export const Alert = ({ children }) => {
  return (
    <div className="bg-red-500 text-white p-3 rounded-lg mb-4">{children}</div>
  );
};
