import React, { useContext, useEffect } from 'react';
import { ProductOnline } from "./ProductOnline";

function ProductsView() {
  const { products, setProducts } = useContext(ProductOnline);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/products/all', {
          method: 'GET'
        });

        const data = await response.json();
        const productList = data.List;

        if (Array.isArray(productList)) {
          setProducts(productList);
          console.log(productList);
        } else {
          console.error("La respuesta no contiene una lista válida");
        }
      } catch (err) {
        alert("Error de conexión con el servidor. Ha fallado la obtención de productos.");
      }
    };

    fetchProducts();
  }, [setProducts]);

  return (
    <div className='firstBox'>
      <header>
        <h1>BLA BLA BLA</h1>
      </header>

      <div>
        {products?.map(p => (
          <div key={p.isbn}>
            <h3>{p.productName}</h3>
            <p>{p.information}</p>
          </div>
        ))}
      </div>

      <footer>
        <h1>BLA BLA BLA</h1>
      </footer>
    </div>
  );
}

export default ProductsView;
