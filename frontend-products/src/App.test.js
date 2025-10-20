import { render, screen } from '@testing-library/react';
import App from './App';

test('renders greeting text', () => {
  render(<App/>);
  const headingElement = screen.getByText(/Hola, React está funcionando/i);
  expect(headingElement).toBeInTheDocument();
});
