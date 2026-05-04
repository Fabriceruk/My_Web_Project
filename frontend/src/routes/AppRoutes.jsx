import { Navigate, Route, Routes } from 'react-router-dom'
import AppLayout from '../components/layout/AppLayout'
import AppointmentsPage from '../pages/AppointmentsPage'
import DoctorsPage from '../pages/DoctorsPage'

function AppRoutes() {
  return (
    <Routes>
      <Route element={<AppLayout />}>
        <Route path="/" element={<Navigate to="/doctors" replace />} />
        <Route path="/doctors" element={<DoctorsPage />} />
        <Route path="/appointments" element={<AppointmentsPage />} />
        <Route path="*" element={<Navigate to="/doctors" replace />} />
      </Route>
    </Routes>
  )
}

export default AppRoutes
