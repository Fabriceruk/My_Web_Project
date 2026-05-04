package com.webstack.backend.services;

import com.webstack.backend.entities.Doctor;
import com.webstack.backend.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Optional<Doctor> updateDoctor(Long id, Doctor payload) {
        return doctorRepository.findById(id).map(existing -> {
            existing.setFullName(payload.getFullName());
            existing.setSpecialization(payload.getSpecialization());
            existing.setDepartment(payload.getDepartment());
            existing.setAvailable(payload.getAvailable());
            return doctorRepository.save(existing);
        });
    }
}
