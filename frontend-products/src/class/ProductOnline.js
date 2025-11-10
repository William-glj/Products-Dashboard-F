import { createContext, useState, useEffect } from 'react';

// Creamos el contexto
export const ProductOnline = createContext();

// Proveedor del contexto
export function ProductProvider({ children }) {
  const [products, setProducts] = useState([]);

  // Cargar productos desde localStorage al iniciar
  useEffect(() => {
    try {
      const stored = localStorage.getItem('products');
      if (stored) {
        const parsed = JSON.parse(stored);
        if (Array.isArray(parsed)) {
          setProducts(parsed);
        }
      }
    } catch (error) {
      console.error("Error al cargar productos del localStorage:", error);
    }
  }, []);

  // Guardar productos en localStorage cuando cambien
  useEffect(() => {
    try {
      if (Array.isArray(products) && products.length > 0) {
        localStorage.setItem('products', JSON.stringify(products));
      } else {
        localStorage.removeItem('products');
      }
    } catch (error) {
      console.error("Error al guardar productos en localStorage:", error);
    }
  }, [products]);

  return (
    <ProductOnline.Provider value={{ products, setProducts }}>
      {children}
    </ProductOnline.Provider>
  );
}
