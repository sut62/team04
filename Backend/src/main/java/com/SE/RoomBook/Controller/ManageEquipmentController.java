package com.SE.RoomBook.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;

import com.SE.RoomBook.Entity.*;
import com.SE.RoomBook.Repository.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ManageEquipmentController {
  @Autowired
  private final ManageEquipmentRepository ManageEquipmentRepository;
  @Autowired
  private EquipmentTypeRepository EquipmentTypeRepository;
  @Autowired
  private EquipmentNameRepository EquipmentNameRepository;
  @Autowired
  private EmployeeRepository EmployeeRepository;

  ManageEquipmentController(ManageEquipmentRepository ManageEquipmentRepository) {
    this.ManageEquipmentRepository = ManageEquipmentRepository;
  }

  @GetMapping("/manageEquipments")
  public Collection<ManageEquipment> ManageEquipment() {
    return ManageEquipmentRepository.findAll().stream().collect(Collectors.toList());
  }

  @PostMapping("/manageEquipments/{em_id}/{equipmenttype_id}/{equipmentname_id}/{manageEquipment_amount}")
  public ManageEquipment newManageEquipment(ManageEquipment newManageEquipment,
  // @PathVariable Date manageEquipment_date,
  @PathVariable long em_id,
  @PathVariable long equipmenttype_id,
  @PathVariable long equipmentname_id,
  @PathVariable String manageEquipment_amount) {

    Employee Employee = EmployeeRepository.findById(em_id);
    EquipmentType EquipmentType = EquipmentTypeRepository.findById(equipmenttype_id);
    EquipmentName EquipmentName = EquipmentNameRepository.findById(equipmentname_id);

    // newManageEquipment.setManageEquipment_date(manageEquipment_date);
    newManageEquipment.setEmployee(Employee);
    newManageEquipment.setEquipmentType(EquipmentType);
    newManageEquipment.setEquipmentName(EquipmentName);
    newManageEquipment.setManageEquipment_amount(manageEquipment_amount);

    return ManageEquipmentRepository.save(newManageEquipment);

  }
}
