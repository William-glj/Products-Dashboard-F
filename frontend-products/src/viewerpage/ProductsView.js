import React, { useContext, useEffect } from 'react';
import { ProductOnline } from "../class/ProductOnline";
import { UserOnline } from '../class/UserOnline';

function ProductsView() {
  const { user, setUser } = useContext(UserOnline);
  const { products, setProducts } = useContext(ProductOnline);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await fetch('/api/products/all', {
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
    <>
      <header>
         <h1>Bienvenido {user?.firstName || "invitado"}</h1>
          <button onClick={() => { setUser(null); }}>
          Cerrar sesión
          </button>
      </header>

      <div className='firstBox'>
        {products?.map(p => (

          


          <div key={p.isbn}>
            <div className='imageBox'>
              <p>{p.images}</p>



            </div>
            <h3>{p.productName}</h3>
            <p>{p.information}</p>
          </div>
        ))}
      </div>

      <footer>
         <ul>
          <li>Lorem ipsum dolor sit amet.</li>
          <li>Lorem ipsum dolor sit amet.</li>
          <li>Lorem ipsum dolor sit amet.</li>
        </ul>
      </footer>
    </>
  );
}

export default ProductsView;
