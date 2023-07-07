package com.smartscheduler.smart.controller;

import com.smartscheduler.smart.model.Appointment;
import com.smartscheduler.smart.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "smartscheduler/api/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;
    @Autowired
    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }
    @GetMapping("/byMonthAndYear")
    public List<Appointment> getAllAppointmentsByMonth(@RequestParam Integer month,@RequestParam(required = false) Integer year) {
        return appointmentService.getAllAppointmentsByMonth(month,year);
    }
    @GetMapping("/byAgentAndDate")
    public List<Appointment> getAllAppointmentsByAgentAndDate(@RequestParam Integer agentId,@RequestParam (required = false)Integer day,@RequestParam(required = false) Integer month,@RequestParam(required = false) Integer year) {
        return appointmentService.getAllAppointmentsByAgentAndDate(agentId,day,month,year);
    }


    @PostMapping
    public void registerNewAppointment(@RequestBody Appointment appointment){
        appointmentService.addNewAppointment(appointment);
    }

    @DeleteMapping(path ="{appointmentId}")
    public void deleteAppointment(@PathVariable ("appointmentId")int appointmentId){
        appointmentService.deleteAppointment(appointmentId);

    }
    @DeleteMapping(path ="/all")
    public void deleteAllAppointment(){
        appointmentService.deleteAllAppointment();

    }
    @PutMapping(path ="{appointmentId}")
    public void updateAppointment(@PathVariable("appointmentId")int appointmentId,@RequestParam(required = false) Integer estimateHrs,@RequestParam (required = false) Integer estimateMin){
        appointmentService.updateAppointment(appointmentId,estimateHrs,estimateMin);
    }


}
