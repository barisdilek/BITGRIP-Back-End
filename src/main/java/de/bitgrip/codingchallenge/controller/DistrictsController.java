package de.bitgrip.codingchallenge.controller;

import de.bitgrip.codingchallenge.entity.District;
import de.bitgrip.codingchallenge.model.DistrictListModel;
import de.bitgrip.codingchallenge.model.DistrictModel;
import de.bitgrip.codingchallenge.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/districts")
public class DistrictsController {

    @Autowired
    DistrictService districtService;

    @GetMapping("/")
    public ResponseEntity<List<DistrictListModel>> get() {
        List<DistrictListModel> allDistrict = districtService.getAll();
        return new ResponseEntity<List<DistrictListModel>>(allDistrict, HttpStatus.OK);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<District>> getSorted(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        List<District> allDistrict = districtService.getSortedList(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<District>>(allDistrict, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DistrictModel> getById(@PathVariable(name = "id") int districtId) {
        DistrictModel district = districtService.getById(districtId);
        return new ResponseEntity<DistrictModel>(district, HttpStatus.OK);
    }

}