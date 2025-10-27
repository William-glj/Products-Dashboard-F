import { createContext, useState, useEffect } from 'react';

export const UserOnline = createContext();

export function UserProvider({ children }) {
  const [user, setUser] = useState(null);


  useEffect(() => {
    const storedUser = localStorage.getItem('user');
    if (storedUser) {
      setUser(JSON.parse(storedUser));
    }
  }, []);

  
  useEffect(() => {
    if (user) {
      localStorage.setItem('user', JSON.stringify(user));
    } else {
      localStorage.removeItem('user');
    }
  }, [user]);

  return (
    <UserOnline.Provider value={{ user, setUser }}>
      {children}
    </UserOnline.Provider>
  );
}


