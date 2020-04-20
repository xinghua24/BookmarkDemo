import React from 'react'
import ReactDOM from 'react-dom';
import AboutPage from './AboutPage';

test('renders without crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<AboutPage />, div);
})

