import './App.css'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import LandingPage from './components/LandingPage'
import AboutPage from './components/AboutPage'
import Card from './components/Card'

function App() {
   throw new Error("Debug: Check if React is working!"); // Force an error
 return (
    <>
    <BrowserRouter>
    <Routes>
       <Route path='/' element={<LandingPage />} />
       <Route path='/about' element={<AboutPage />} />
    </Routes>
    </BrowserRouter>
    </>
  )
}

export default App
