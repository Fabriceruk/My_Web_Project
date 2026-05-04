import { useEffect, useState } from 'react'
import client from '../api/client'

const timeSlots = ['08:00-09:00', '10:00-11:00', '14:00-15:00']

const initialAppointmentForm = {
  patientName: '',
  patientId: '',
  doctorId: '',
  appointmentDate: '',
  timeSlot: timeSlots[0],
}

function AppointmentsPage() {
  const [doctors, setDoctors] = useState([])
  const [appointments, setAppointments] = useState([])
  const [formData, setFormData] = useState(initialAppointmentForm)
  const [message, setMessage] = useState('')

  const loadAppointments = async () => {
    try {
      const response = await client.get('/api/appointments')
      setAppointments(response.data)
    } catch {
      setMessage('Failed to load appointments.')
    }
  }

  useEffect(() => {
    client
      .get('/api/doctors')
      .then((response) => {
        setDoctors(response.data.filter((doctor) => doctor.available))
      })
      .catch(() => {
        setMessage('Failed to load doctors.')
      })

    client
      .get('/api/appointments')
      .then((response) => {
        setAppointments(response.data)
      })
      .catch(() => {
        setMessage('Failed to load appointments.')
      })
  }, [])

  const handleChange = (event) => {
    const { name, value } = event.target
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }))
  }

  const handleSubmit = async (event) => {
    event.preventDefault()
    setMessage('')
    try {
      await client.post('/api/appointments', {
        ...formData,
        doctorId: Number(formData.doctorId),
      })
      setMessage('Appointment booked successfully.')
      setFormData(initialAppointmentForm)
      loadAppointments()
    } catch {
      setMessage('Failed to book appointment. Verify doctor availability.')
    }
  }

  const handleCancel = async (id) => {
    setMessage('')
    try {
      await client.delete(`/api/appointments/${id}`)
      setAppointments((prev) => prev.filter((appointment) => appointment.id !== id))
      setMessage('Appointment cancelled.')
    } catch {
      setMessage('Failed to cancel appointment.')
    }
  }

  return (
    <section className="space-y-8">
      <div>
        <h2 className="text-2xl font-semibold">Appointments</h2>
        <p className="text-sm text-slate-600">Book and manage clinic appointments.</p>
      </div>

      <form onSubmit={handleSubmit} className="space-y-4 rounded border bg-white p-4">
        <h3 className="text-lg font-medium">Book Appointment</h3>
        <div className="grid gap-4 md:grid-cols-2">
          <input
            className="rounded border px-3 py-2"
            name="patientName"
            placeholder="Patient Name"
            value={formData.patientName}
            onChange={handleChange}
            required
          />
          <input
            className="rounded border px-3 py-2"
            name="patientId"
            placeholder="Patient ID"
            value={formData.patientId}
            onChange={handleChange}
            required
          />
          <select
            className="rounded border px-3 py-2"
            name="doctorId"
            value={formData.doctorId}
            onChange={handleChange}
            required
          >
            <option value="">Select Doctor</option>
            {doctors.map((doctor) => (
              <option key={doctor.id} value={doctor.id}>
                {doctor.fullName} - {doctor.specialization}
              </option>
            ))}
          </select>
          <input
            type="date"
            className="rounded border px-3 py-2"
            name="appointmentDate"
            value={formData.appointmentDate}
            onChange={handleChange}
            required
          />
          <select
            className="rounded border px-3 py-2"
            name="timeSlot"
            value={formData.timeSlot}
            onChange={handleChange}
            required
          >
            {timeSlots.map((slot) => (
              <option key={slot} value={slot}>
                {slot}
              </option>
            ))}
          </select>
        </div>
        <button type="submit" className="rounded bg-blue-600 px-4 py-2 text-white hover:bg-blue-700">
          Book Appointment
        </button>
      </form>

      <div className="overflow-x-auto rounded border bg-white">
        <table className="min-w-full text-left text-sm">
          <thead className="bg-slate-100">
            <tr>
              <th className="px-4 py-3">Patient Name</th>
              <th className="px-4 py-3">Patient ID</th>
              <th className="px-4 py-3">Doctor ID</th>
              <th className="px-4 py-3">Date</th>
              <th className="px-4 py-3">Time Slot</th>
              <th className="px-4 py-3">Action</th>
            </tr>
          </thead>
          <tbody>
            {appointments.map((appointment) => (
              <tr key={appointment.id} className="border-t">
                <td className="px-4 py-3">{appointment.patientName}</td>
                <td className="px-4 py-3">{appointment.patientId}</td>
                <td className="px-4 py-3">{appointment.doctorId}</td>
                <td className="px-4 py-3">{appointment.appointmentDate}</td>
                <td className="px-4 py-3">{appointment.timeSlot}</td>
                <td className="px-4 py-3">
                  <button
                    type="button"
                    onClick={() => handleCancel(appointment.id)}
                    className="rounded bg-red-600 px-3 py-1 text-white hover:bg-red-700"
                  >
                    Cancel
                  </button>
                </td>
              </tr>
            ))}
            {appointments.length === 0 && (
              <tr>
                <td className="px-4 py-3 text-slate-500" colSpan="6">
                  No appointments found.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>

      {message && <p className="text-sm text-slate-700">{message}</p>}
    </section>
  )
}

export default AppointmentsPage
