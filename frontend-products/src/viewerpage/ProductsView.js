import React, { useContext, useEffect } from 'react';
import { ProductOnline } from "../class/ProductOnline";
import { UserOnline } from '../class/UserOnline';
import { Link} from 'react-router-dom';
import '../css/StandardFormat.css';
function ProductsView() {
  const { user } = useContext(UserOnline);
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
  <div className="container">
    <aside className="sidebar">
      <div className="logo">join</div>
      <nav>
        <ul>
          <li><Link to={`/home/${user?.firstName}/allUsers`}>Usuarios</Link></li>
          <li><Link to={`/home/${user?.firstName}/products`}>Productos</Link></li>
          <li><Link to={`/home/${user?.firstName}`}>Perfil</Link></li>
          <li><span>Registros</span></li>
        </ul>
      </nav>
    </aside>

    <main className="content">
      <header className="header">
        <p className="date">{new Date().toLocaleDateString()}</p>
      </header>

      <section className="applications">
        <h2>Productos</h2>
        <div className="card-list">
          {products?.map(p => (
            <div className="card" key={p.isbn}>
             
                    <div className='imageBox'>
                      <img src={`data:image/jpeg;base64,${p.images}`} alt="Imagen del producto" />

                    </div>
                    <h3>{p.productName}</h3>
                    <p>{p.information}</p>
              
              
            </div>
          ))}
        </div>
      </section>
    </main>
  </div>
);





}

export default ProductsView;
