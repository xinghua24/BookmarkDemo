import React from 'react'
import ReactDOM from 'react-dom';
import NotFoundPage from './NotFoundPage';
import {render, screen} from '@testing-library/react'

it('renders without crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<NotFoundPage />, div);
})

it('renders 404 message', () => {
    const { getByText } = render(<NotFoundPage />);
    const title = getByText(/^404/);
    expect(title).toBeInTheDocument();
})
