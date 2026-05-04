import { Link, Outlet } from 'react-router-dom'

function AppLayout() {
  return (
    <div className="min-h-screen bg-slate-50 text-slate-900">
      <header className="border-b bg-white">
        <div className="mx-auto flex max-w-5xl items-center justify-between px-6 py-4">
          <h1 className="text-xl font-semibold">Hospital Appointment System</h1>
          <nav className="flex gap-6 text-sm font-medium">
            <Link to="/doctors" className="hover:text-blue-600">
              Doctors
            </Link>
            <Link to="/appointments" className="hover:text-blue-600">
              Appointments
            </Link>
          </nav>
        </div>
      </header>

      <main className="mx-auto max-w-5xl px-6 py-8">
        <Outlet />
      </main>
    </div>
  )
}

export default AppLayout
