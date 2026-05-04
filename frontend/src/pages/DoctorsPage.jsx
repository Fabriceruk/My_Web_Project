import { useEffect, useState } from 'react'
import client from '../api/client'

const initialDoctorForm = {
  fullName: '',
  specialization: '',
  department: '',
  available: true,
}

function DoctorsPage() {
  const [doctors, setDoctors] = useState([])
  const [formData, setFormData] = useState(initialDoctorForm)
  const [message, setMessage] = useState('')

  const loadDoctors = async () => {
    try {
      const response = await client.get('/api/doctors')
      setDoctors(response.data)
    } catch {
      setMessage('Failed to load doctors.')
    }
  }

  useEffect(() => {
    client
      .get('/api/doctors')
      .then((response) => {
        setDoctors(response.data)
      })
      .catch(() => {
        setMessage('Failed to load doctors.')
      })
  }, [])

  const handleChange = (event) => {
    const { name, value, type, checked } = event.target
    setFormData((prev) => ({
      ...prev,
      [name]: type === 'checkbox' ? checked : value,
    }))
  }

  const handleSubmit = async (event) => {
    event.preventDefault()
    setMessage('')
    try {
      await client.post('/api/doctors', formData)
      setFormData(initialDoctorForm)
      setMessage('Doctor added successfully.')
      loadDoctors()
    } catch {
      setMessage('Failed to add doctor.')
    }
  }

  return (
    <section className="space-y-8">
      <div>
        <h2 className="text-2xl font-semibold">Doctors</h2>
        <p className="text-sm text-slate-600">Manage doctors and availability.</p>
      </div>

      <div className="overflow-x-auto rounded border bg-white">
        <table className="min-w-full text-left text-sm">
          <thead className="bg-slate-100">
            <tr>
              <th className="px-4 py-3">Full Name</th>
              <th className="px-4 py-3">Specialization</th>
              <th className="px-4 py-3">Department</th>
              <th className="px-4 py-3">Availability</th>
            </tr>
          </thead>
          <tbody>
            {doctors.map((doctor) => (
              <tr key={doctor.id} className="border-t">
                <td className="px-4 py-3">{doctor.fullName}</td>
                <td className="px-4 py-3">{doctor.specialization}</td>
                <td className="px-4 py-3">{doctor.department}</td>
                <td className="px-4 py-3">{doctor.available ? 'Available' : 'Unavailable'}</td>
              </tr>
            ))}
            {doctors.length === 0 && (
              <tr>
                <td className="px-4 py-3 text-slate-500" colSpan="4">
                  No doctors found.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>

      <form onSubmit={handleSubmit} className="space-y-4 rounded border bg-white p-4">
        <h3 className="text-lg font-medium">Add Doctor</h3>
        <div className="grid gap-4 md:grid-cols-2">
          <input
            className="rounded border px-3 py-2"
            name="fullName"
            placeholder="Full Name"
            value={formData.fullName}
            onChange={handleChange}
            required
          />
          <input
            className="rounded border px-3 py-2"
            name="specialization"
            placeholder="Specialization"
            value={formData.specialization}
            onChange={handleChange}
            required
          />
          <input
            className="rounded border px-3 py-2"
            name="department"
            placeholder="Department"
            value={formData.department}
            onChange={handleChange}
            required
          />
          <label className="flex items-center gap-2 text-sm">
            <input
              type="checkbox"
              name="available"
              checked={formData.available}
              onChange={handleChange}
            />
            Available
          </label>
        </div>
        <button type="submit" className="rounded bg-blue-600 px-4 py-2 text-white hover:bg-blue-700">
          Add Doctor
        </button>
        {message && <p className="text-sm text-slate-700">{message}</p>}
      </form>
    </section>
  )
}

export default DoctorsPage
