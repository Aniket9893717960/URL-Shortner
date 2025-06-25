import React from 'react' // Add this at the top of main.jsx
import { StrictMode } from 'react'
import { StoreProvider } from './contextApi/ContextApi' // Adjust path as needed
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'

createRoot(document.getElementById('root')).render(
  <React.StrictMode>
      <StoreProvider>
         <App />
      </StoreProvider>
  </React.StrictMode>,
)
