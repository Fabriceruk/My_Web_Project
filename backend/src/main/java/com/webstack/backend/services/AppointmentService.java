package com.webstack.backend.services;

import com.webstack.backend.entities.Appointment;
import com.webstack.backend.entities.Doctor;
import com.webstack.backend.repositories.AppointmentRepository;
import com.webstack.backend.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> createAppointment(Appointment appointment) {
        Optional<Doctor> doctor = doctorRepository.findById(appointment.getDoctorId());
        if (doctor.isEmpty()) {
            return Optional.empty();
        }
        if (!Boolean.TRUE.equals(doctor.get().getAvailable())) {
            return Optional.empty();
        }
        return Optional.of(appointmentRepository.save(appointment));
    }

    public boolean deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            return false;
        }
        appointmentRepository.deleteById(id);
        return true;
    }
}
